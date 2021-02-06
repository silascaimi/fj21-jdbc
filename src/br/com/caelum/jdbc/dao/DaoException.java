package br.com.caelum.jdbc.dao;

public class DaoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DaoException(Exception e){
		super(e);
	}

	
}
