package br.com.sistemalanchonete.produto.enums;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TipoProdutoConversao implements AttributeConverter<TipoProduto, String> {

	@Override
	public String convertToDatabaseColumn(TipoProduto tipoProduto) {
		return tipoProduto.getCodigo();
	}

	@Override
	public TipoProduto convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData))
			throw new NullPointerException("O código do Tipo do Produto não pode ser nulo!");
		
		Optional<TipoProduto> tipoProduto = Arrays.stream(TipoProduto.values())
			.filter(tipoproduto -> tipoproduto.getCodigo().equals(dbData)).findFirst();
		
		if (tipoProduto.isEmpty())
			throw new IllegalArgumentException("O código do Tipo do Produto é inválido!");
		
		return tipoProduto.get();
	}
}
