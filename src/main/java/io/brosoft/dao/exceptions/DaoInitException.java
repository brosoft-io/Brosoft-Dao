package io.brosoft.dao.exceptions;

public class DaoInitException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3676449361977604552L;

	public DaoInitException() {
		super();
	}
	
	public DaoInitException(String message) {
		super(message);
	}
	
	public DaoInitException(Throwable e) {
		super(e);
	}
	
	public DaoInitException(String message, Throwable e) {
		super(message, e);
	}
}
