package io.brosoft.dao.exceptions;

public class BeanAccessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 740985842890381334L;

	public BeanAccessException() {
		super();
	}
	
	public BeanAccessException(String message) {
		super(message);
	}
	
	public BeanAccessException(Throwable e) {
		super(e);
	}
	
	public BeanAccessException(String message, Throwable e) {
		super(message, e);
	}
}
