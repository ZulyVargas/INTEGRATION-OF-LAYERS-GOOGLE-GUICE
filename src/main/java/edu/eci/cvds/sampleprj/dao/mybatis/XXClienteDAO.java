package edu.eci.cvds.sampleprj.dao.mybatis;

import java.sql.Date;
import java.util.List;

import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;


public class XXClienteDAO implements ClienteDAO{


	@Override
	public Cliente consultarCliente(int id) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registrarCliente(Cliente c) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Cliente> consultarClientes() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agregarItemRentadoACliente(int id, int idit, Date fechainicio, Date fechafin)
			throws PersistenceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vetarCliente(long docu, boolean estado) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Item> consultarItemsRentadosCliente(int docu) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

}
