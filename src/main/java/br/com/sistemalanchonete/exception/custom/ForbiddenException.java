package br.com.sistemalanchonete.exception.custom;


public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	public ForbiddenException(String message) {
		super(message);
	}
}
