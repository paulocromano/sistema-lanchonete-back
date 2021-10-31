package br.com.sistemalanchonete.exception.custom;

public class IntegrityConstraintViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	public IntegrityConstraintViolationException(String message) {
		super(message);
	}
}

