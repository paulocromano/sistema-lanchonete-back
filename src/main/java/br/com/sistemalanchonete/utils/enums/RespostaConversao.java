package br.com.sistemalanchonete.utils.enums;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RespostaConversao implements AttributeConverter<Resposta, String> {

	@Override
	public String convertToDatabaseColumn(Resposta resposta) {
		return resposta.getCodigo();
	}

	@Override
	public Resposta convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData))
			throw new NullPointerException("O código da Respsota não pode ser nulo!");
		
		Optional<Resposta> respostaOptional = Arrays.stream(Resposta.values())
			.filter(resposta -> resposta.getCodigo().equals(dbData)).findFirst();
		
		if (respostaOptional.isEmpty())
			throw new IllegalArgumentException("O código da Respsota é inválido!");
		
		return respostaOptional.get();
	}
}
