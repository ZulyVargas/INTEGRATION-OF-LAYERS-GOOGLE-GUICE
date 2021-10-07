package edu.eci.cvds.sampleprj.dao;

public class PersistenceException extends Exception{
	
	public static final String EXCEPCION = "Ha ocurrido una excepción"; 
	
    public PersistenceException(String M) 
    {
        super(M);
    }

	public PersistenceException(String string, org.apache.ibatis.exceptions.PersistenceException e) {
		// TODO Auto-generated constructor stub
	}

}
