package br.com.sistemalanchonete.exception.custom;

public class FileException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	public FileException(String message) {
		super(message);
	}
}
