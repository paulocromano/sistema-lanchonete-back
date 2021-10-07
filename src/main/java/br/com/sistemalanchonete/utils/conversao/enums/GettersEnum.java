package br.com.sistemalanchonete.utils.conversao.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public interface GettersEnum<T extends Enum<T>> {

	@JsonValue
	String getCodigo();
	
	String getDescricao();
}
