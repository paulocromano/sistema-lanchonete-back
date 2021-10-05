package br.com.sistemalanchonete.exception.custom;

public class LanchoneteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	public LanchoneteException(String message) {
		super(message);
	}
}
