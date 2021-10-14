package edu.eci.cvds.view;


import javax.faces.bean.ManagedBean;

import edu.eci.cvds.samples.entities.Cliente;
import com.google.inject.Inject;

import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;


@ManagedBean(name="AlquilerItemsBean")
@ApplicationScoped
public class AlquilerItemsBean extends BasePageBean{

	@Inject
    private ServiciosAlquiler serviciosAlquiler;	
	
	public List<Cliente> consultarClientes(){
		List<Cliente> clientes = null;
		try {
			clientes = serviciosAlquiler.consultarClientes();
			return  clientes;
		} catch (ExcepcionServiciosAlquiler e) {
			e.printStackTrace();
		}
		return clientes;
	}
	

	
	
	
}
