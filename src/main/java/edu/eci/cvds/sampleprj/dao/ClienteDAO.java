package edu.eci.cvds.sampleprj.dao;

import java.sql.Date;
import java.util.List;

import edu.eci.cvds.samples.entities.Cliente;

public interface ClienteDAO {

	public void registrarCliente(Cliente c) throws PersistenceException;

	public Cliente consultarCliente(int id) throws PersistenceException;

	public List<Cliente> consultarClientes() throws PersistenceException;

	public void agregarItemRentadoACliente(int id, int idit, Date fechainicio, Date fechafin) throws PersistenceException;
	   
    public void vetarCliente(long docu, boolean estado) throws PersistenceException;
	   
	   
	
	
	
}
