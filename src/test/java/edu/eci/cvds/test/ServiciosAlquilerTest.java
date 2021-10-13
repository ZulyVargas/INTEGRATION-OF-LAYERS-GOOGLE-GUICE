package edu.eci.cvds.test;

import java.security.PublicKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.DateFormatter;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import org.apache.ibatis.session.SqlSession;
import org.checkerframework.checker.fenum.qual.AwtColorSpace;
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

    @Before
    public void setUp() {
    }

    @Test
    public void emptyDB() {
        for(int i = 0; i < 100; i += 10) {
            boolean r = false;
            try {
                Cliente cliente = serviciosAlquiler.consultarCliente(i);
            } catch(ExcepcionServiciosAlquiler e) {
                r = true;
            } catch(IndexOutOfBoundsException e) {
                r = true;
            }
            // Validate no Client was found;
            Assert.assertTrue(r);
        };
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
    public void deberiaConsultarItem() {
    	TipoItem tipoItem = new TipoItem(3, "Peliculas");
    	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    	try {
    		java.sql.Date sqlfechaLanzamiento = new java.sql.Date(formato.parse("2021-05-12").getTime());
    		Item newItem = new Item (tipoItem, 20, "A todo Gas","Película del 2021", sqlfechaLanzamiento, 2000, "DVD", "Acción");
    		serviciosAlquiler.registrarItem(newItem);
    		Assert.assertEquals(serviciosAlquiler.consultarItem(20).getNombre(), "A todo Gas");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}