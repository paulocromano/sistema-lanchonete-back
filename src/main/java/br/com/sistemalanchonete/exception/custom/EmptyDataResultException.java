package br.com.sistemalanchonete.exception.custom;

public class EmptyDataResultException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	public EmptyDataResultException(String message) {
		super(message);
	}
}