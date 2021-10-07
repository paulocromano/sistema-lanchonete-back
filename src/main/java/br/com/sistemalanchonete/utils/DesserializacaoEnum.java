package br.com.sistemalanchonete.utils;

import java.io.IOException;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

import br.com.sistemalanchonete.utils.conversao.enums.GettersEnum;


@SuppressWarnings("unchecked")
public class DesserializacaoEnum<E extends GettersEnum<?>> extends JsonDeserializer<E> implements ContextualDeserializer {

	private E[] constantesEnum;

	
	@Override
	public E deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		
		String codigoEnumFormulario = p.getText();
		
		if (Objects.nonNull(codigoEnumFormulario)) {
			for (GettersEnum<?> valorEnum : constantesEnum) {
				if (valorEnum.getCodigo().equals(codigoEnumFormulario))
					return (E) valorEnum;
			}
		}
		else {
			return null;
		}

		throw new IllegalArgumentException("O campo do(a) " + constantesEnum.getClass().getSimpleName().split("\\[]")[0] 
				+ " contém valor inválido!");
	}

	@Override
	public JsonDeserializer<E> createContextual(DeserializationContext ctxt, BeanProperty property)
			throws JsonMappingException {
		
		Class<E> tipoEnum = (Class<E>) ctxt.getContextualType().getRawClass();
		constantesEnum = tipoEnum.getEnumConstants();
        
		return this;
	}
}
