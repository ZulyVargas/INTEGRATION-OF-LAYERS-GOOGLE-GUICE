package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.List;

import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Item;


public class XXItemDAO implements ItemDAO{


	@Override
	public Item consultarItem(int itemId) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registrarItem(Item it) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Item> consultarItemsDisponibles() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizarTarifaItem(int id, long tarifa) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}

}
