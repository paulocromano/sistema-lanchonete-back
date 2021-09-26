package br.com.sistemalanchonete.utils;

import com.fasterxml.jackson.annotation.JsonValue;

public interface CodigoEnum<T extends Enum<T>> {

	@JsonValue
	String getCodigo();
}
