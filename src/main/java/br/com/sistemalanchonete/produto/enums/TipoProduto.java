package br.com.sistemalanchonete.produto.enums;

import br.com.sistemalanchonete.utils.conversao.enums.GettersEnum;
import lombok.Getter;


@Getter
public enum TipoProduto implements GettersEnum<TipoProduto> {

	BEBIDA("1", "Bebida"),
	ALIMENTICIO("2", "Alimentício"),
	NAO_ALIMENTICIO("3", "Não alimentício");
	
	
	private String codigo;
	private String descricao;
	
	
	private TipoProduto(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
}
