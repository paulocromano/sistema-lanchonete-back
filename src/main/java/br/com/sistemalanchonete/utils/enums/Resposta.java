package br.com.sistemalanchonete.utils.enums;

import br.com.sistemalanchonete.utils.conversao.enums.GettersEnum;
import lombok.Getter;

@Getter
public enum Resposta implements GettersEnum<Resposta> {

	SIM("S", "Sim"),
	NAO("N", "Não");
	
	
	private String codigo;
	private String descricao;
	
	
	private Resposta(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
}
