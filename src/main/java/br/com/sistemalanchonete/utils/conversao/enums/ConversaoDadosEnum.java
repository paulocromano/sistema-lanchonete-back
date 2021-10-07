package br.com.sistemalanchonete.utils.conversao.enums;

import java.util.ArrayList;
import java.util.List;

public final class ConversaoDadosEnum {

	public static final <T extends Enum<T> & GettersEnum<T>> List<DadosEnum> converterDadosEnum(T[] valoresEnum) {
		List<DadosEnum> dados = new ArrayList<>();
		
		for (T valorEnum : valoresEnum) {
			dados.add(new DadosEnum(valorEnum.getCodigo(), valorEnum.getDescricao()));
		}
		
		return dados;
	}
}
