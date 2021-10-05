package br.com.sistemalanchonete.despesa.enums;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TipoDespesaConversao implements AttributeConverter<TipoDespesa, String> {

	@Override
	public String convertToDatabaseColumn(TipoDespesa tipoDespesa) {
		return tipoDespesa.getCodigo();
	}

	@Override
	public TipoDespesa convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData))
			throw new NullPointerException("O código do Tipo da Despesa não pode ser nulo!");
		
		Optional<TipoDespesa> despesa = Arrays.stream(TipoDespesa.values())
			.filter(tipoDespesa -> tipoDespesa.getCodigo().equals(dbData)).findFirst();
		
		if (despesa.isEmpty())
			throw new IllegalArgumentException("O código do Tipo da Despesa é inválido!");
		
		return despesa.get();
	}
}
