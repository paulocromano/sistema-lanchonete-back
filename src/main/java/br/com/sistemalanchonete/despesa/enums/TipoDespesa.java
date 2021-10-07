package br.com.sistemalanchonete.despesa.enums;

import br.com.sistemalanchonete.utils.conversao.enums.GettersEnum;
import lombok.Getter;

@Getter
public enum TipoDespesa implements GettersEnum<TipoDespesa> {

	AGUA("1", "Água"),
	LUZ("2", "Luz"),
	INTERNET("3", "Internet"),
	ALUGUEL("4", "Aluguel"),
	BOLETO("5", "Boleto"),
	OUTROS("6", "Outros");
	
	
	private String codigo;
	private String descricao;
	
	
	private TipoDespesa(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
}
