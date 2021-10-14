package edu.eci.cvds.test;

import java.text.SimpleDateFormat;
import java.util.List;
import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class ServiciosAlquilerTest {

    /*@Inject
    private SqlSession sqlSession;*/

    @Inject
    private ServiciosAlquiler serviciosAlquiler;

    public ServiciosAlquilerTest() {
        serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
    }

    
    @Test
    public void emptyDB() {
        for(int i = 0; i < 100; i += 10) {
            boolean r = false;
            try {
                serviciosAlquiler.consultarCliente(i);
            } catch(ExcepcionServiciosAlquiler e) {
                r = true;
            } catch(IndexOutOfBoundsException e) {
                r = true;
            }
            // Validate no Client was found;
            Assert.assertTrue(r);
        }
    }
    
    @Test
    public void deberiaRegistrarItem() {
    	TipoItem tipoItem = new TipoItem(3, "Peliculas");
    	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    	try {
    		java.sql.Date sqlfechaLanzamiento = new java.sql.Date(formato.parse("2021-05-12").getTime());
    		Item newItem = new Item (tipoItem, 2162844, "Space Jam","Película netflix del 2021", sqlfechaLanzamiento, 2000, "DVD", "Infantil");
    		serviciosAlquiler.registrarItem(newItem);
    		Assert.assertEquals(serviciosAlquiler.consultarItem(2162844).getId(), 2162844);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void noDeberiaRegistrarItem() {
    	TipoItem tipoItem = new TipoItem(3, "Peliculas");
    	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    	try {
    		java.sql.Date sqlfechaLanzamiento = new java.sql.Date(formato.parse("2021-05-12").getTime());
    		java.sql.Date sqlfechaLanzamiento2 = new java.sql.Date(formato.parse("2019-01-17").getTime());
    		Item newItem = new Item (tipoItem, 2, "Space Jam","Película netflix del 2021", sqlfechaLanzamiento, 2000, "DVD", "Infantil");
    		Item newItem2 = new Item (tipoItem, 2, "Cómo entrenar a tu dragón 3","Película del 2019", sqlfechaLanzamiento2, 1500, "DVD", "Aventura");
    		serviciosAlquiler.registrarItem(newItem);
    		serviciosAlquiler.registrarItem(newItem2);
    	} catch (Exception e) {
    		Assert.assertEquals(e.getMessage(), "Se produjo un error al registrar el item.");
    	}
    }
    
    @Test
    public void deberiaConsultarItem() {
    	TipoItem tipoItem = new TipoItem(3, "Peliculas");
    	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    	try {
    		java.sql.Date sqlfechaLanzamiento = new java.sql.Date(formato.parse("2021-06-21").getTime());
    		Item newItem = new Item (tipoItem, 20, "A todo Gas","Película del 2021", sqlfechaLanzamiento, 2000, "DVD", "Acción");
    		serviciosAlquiler.registrarItem(newItem);
    		Assert.assertEquals(serviciosAlquiler.consultarItem(20).getNombre(), "A todo Gas");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void noDeberiaConsultarItem() {
    	try {
    		serviciosAlquiler.consultarItem(2);
    	} catch (Exception e) {
    		Assert.assertEquals(e.getMessage(), "Se produjo un error al consultar el item con id 22");
    	}
    }
    
    @Test 
    public void deberiaConsultarItems() {
    	TipoItem tipoItem = new TipoItem(3, "Peliculas");
    	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    	try {
    		java.sql.Date sqlfechaLanzamiento = new java.sql.Date(formato.parse("2021-08-21").getTime());
    		java.sql.Date sqlfechaLanzamiento1 = new java.sql.Date(formato.parse("2019-01-17").getTime());
    		Item newItem = new Item (tipoItem, 1, "A todo Gas","Película del 2021", sqlfechaLanzamiento, 2000, "DVD", "Acción");
    		Item newItem2 = new Item (tipoItem, 2, "Cómo entrenar a tu dragón 3","Película del 2019", sqlfechaLanzamiento1, 1500, "DVD", "Aventura");
    		serviciosAlquiler.registrarItem(newItem);
    		serviciosAlquiler.registrarItem(newItem2);
    		List<Item> itemsDisponibles = serviciosAlquiler.consultarItemsDisponibles();
    		//System.out.println(itemsDisponibles);
    		if (itemsDisponibles != null) {
    			Assert.assertEquals(serviciosAlquiler.consultarItemsDisponibles().get(0).getNombre(), "A todo Gas");
    			Assert.assertEquals(serviciosAlquiler.consultarItemsDisponibles().get(1).getNombre(), "Cómo entrenar a tu dragón 3");
    		} else {
    			Assert.assertTrue(false);
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void deberiaActualizarTarifaItem() {
		TipoItem tipoItem = new TipoItem(3, "Peliculas");
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.sql.Date sqlfechaLanzamiento = new java.sql.Date(formato.parse("2021-05-12").getTime());
			Item newItem = new Item (tipoItem, 2162845, "Space Jam","Película netflix del 2021", sqlfechaLanzamiento, 2000, "DVD", "Infantil");
			serviciosAlquiler.registrarItem(newItem);
			serviciosAlquiler.actualizarTarifaItem(2162845, 3000);
			Assert.assertEquals(serviciosAlquiler.consultarItem(2162845).getTarifaxDia(), 3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void deberiaRegistrarCliente() {
    	try {
    		Cliente cliente = new Cliente("Fernando", 16254827, "3232452466", "Cra. 2 - 45", "fernando@mail.com", false, null);
    		serviciosAlquiler.registrarCliente(cliente);
    		//System.out.println(serviciosAlquiler.consultarCliente(16254827));
    		Assert.assertEquals(serviciosAlquiler.consultarCliente(16254827).getDocumento(), 16254827);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void deberiaConsultarCliente() {
    	try {
    		Assert.assertEquals(serviciosAlquiler.consultarCliente(16254827).getDocumento(), 16254827);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /*@Test
    public void deberiaInsertarItemRentadoCliente() {
    	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    	try {
    		serviciosAlquiler.agregarItemRentadoACliente(1, 14, new java.sql.Date(formato.parse("2021-10-21").getTime()), new java.sql.Date(formato.parse("2021-10-23").getTime()));
    		Assert.assertEquals(serviciosAlquiler.consultarCliente(16254827).getDocumento(), 16254827);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }*/
    
    /*@Test
    public void deberiaConsultarItemsCliente() {
    	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    	try {
    		serviciosAlquiler.agregarItemRentadoACliente(2, 20, new java.sql.Date(formato.parse("2021-10-22").getTime()), new java.sql.Date(formato.parse("2021-10-23").getTime()));
    		System.out.println(serviciosAlquiler.consultarItemsRentadosCliente(2));
    		Assert.assertEquals(serviciosAlquiler.consultarItemsRentadosCliente(2).size(), 1);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }*/
    
    
    
}