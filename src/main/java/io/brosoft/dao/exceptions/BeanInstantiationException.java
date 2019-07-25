package io.brosoft.dao.exceptions;

public class BeanInstantiationException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6576874715258478970L;

	public BeanInstantiationException() {
		super();
	}
	
	public BeanInstantiationException(String message) {
		super(message);
	}
	
	public BeanInstantiationException(Throwable e) {
		super(e);
	}
	
	public BeanInstantiationException(String message, Throwable e) {
		super(message, e);
	}

}
