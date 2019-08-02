package io.brosoft.dao.exceptions;

public class ExecutionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5480023238488056032L;

	public ExecutionException() {
		super();
	}
	
	public ExecutionException(String message) {
		super(message);
	}
	
	public ExecutionException(Throwable e) {
		super(e);
	}
	
	public ExecutionException(String message, Throwable e) {
		super(message, e);
	}
}
