package br.com.sistemalanchonete.exception.handle;

import java.time.DateTimeException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.sistemalanchonete.exception.custom.EmptyDataResultException;
import br.com.sistemalanchonete.exception.custom.FileException;
import br.com.sistemalanchonete.exception.custom.ForbiddenException;
import br.com.sistemalanchonete.exception.custom.IntegrityConstraintViolationException;
import br.com.sistemalanchonete.exception.custom.ObjectNotFoundException;
import br.com.sistemalanchonete.exception.model.PadraoErro;
import br.com.sistemalanchonete.exception.model.ValidationError;

@ControllerAdvice
public class ExceptionHandle {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<PadraoErro> illegalArgument(IllegalArgumentException exception, HttpServletRequest request) {
		return erroPadronizado(HttpStatus.BAD_REQUEST, "Valor inválido!", exception, request);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<PadraoErro> nullPointer(NullPointerException exception, HttpServletRequest request) {
		return erroPadronizado(HttpStatus.BAD_REQUEST, "Valor nulo!", exception, request);
	}
	
	
	@ExceptionHandler(DateTimeException.class)
	public ResponseEntity<PadraoErro> dateTime(DateTimeException exception, HttpServletRequest request) {
		return erroPadronizado(HttpStatus.BAD_REQUEST, "Erro ao converter Data e/ou Hora!", exception, request);
	}
	
	@ExceptionHandler(IntegrityConstraintViolationException.class)
	public ResponseEntity<PadraoErro> integrityConstraintViolation(IntegrityConstraintViolationException exception, HttpServletRequest request) {
		return erroPadronizado(HttpStatus.BAD_REQUEST, "Erro de Integridade dos Dados!", exception, request);
	}
	
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<PadraoErro> forbidden(ForbiddenException exception, HttpServletRequest request) {
		return erroPadronizado(HttpStatus.FORBIDDEN, "Acesso negado!", exception, request);
	}
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<PadraoErro> notFound(ObjectNotFoundException exception, HttpServletRequest request) {
		return erroPadronizado(HttpStatus.NOT_FOUND, "Objeto não encontrado!", exception, request);
	}
	
	@ExceptionHandler(FileException.class)
	public ResponseEntity<PadraoErro> file(FileException exception, HttpServletRequest request) {
		return erroPadronizado(HttpStatus.BAD_REQUEST, "Erro em arquivo!", exception, request);
	}
	
	@ExceptionHandler(EmptyDataResultException.class)
	public ResponseEntity<PadraoErro> emptyDataResult(EmptyDataResultException exception, HttpServletRequest request) {
		return erroPadronizado(HttpStatus.NOT_FOUND, "Nenhum resultado retornado!", exception, request);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<PadraoErro> validation(MethodArgumentNotValidException exception, HttpServletRequest request) {
		
		ValidationError validationError = new ValidationError(System.currentTimeMillis(), 
				HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de Validação de Campos!", 
				"Existe(m) campo(m) inválido(s)!", request.getRequestURI());
		
		for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) 
			validationError.addError(fieldError.getField(), fieldError.getDefaultMessage());
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(validationError);
	}


	private ResponseEntity<PadraoErro> erroPadronizado(HttpStatus httpStatus, String erro, Exception exception, HttpServletRequest request) {
		return ResponseEntity.status(httpStatus).body(new PadraoErro(System.currentTimeMillis(), httpStatus.value(), 
				erro, exception.getMessage(), request.getRequestURI()));
	}
}
