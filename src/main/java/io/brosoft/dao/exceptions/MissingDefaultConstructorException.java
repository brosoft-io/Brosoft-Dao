package io.brosoft.dao.exceptions;

public class MissingDefaultConstructorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2622579233487942659L;
	
	public MissingDefaultConstructorException() {
		super();
	}
	
	public MissingDefaultConstructorException(String message) {
		super(message);
	}
	
	public MissingDefaultConstructorException(Throwable e) {
		super(e);
	}
	
	public MissingDefaultConstructorException(String message, Throwable e) {
		super(message, e);
	}

}
