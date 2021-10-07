package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Cliente;

public interface ClienteDAO {

	   public void save(Cliente c) throws PersistenceException;

	   public Cliente consultarCliente(int id) throws PersistenceException;
	
	
	
}
