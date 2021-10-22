package br.com.sistemalanchonete.mesa.dto;

import br.com.sistemalanchonete.mesa.model.Mesa;
import lombok.Getter;

@Getter
public class MesaDTO {

	private Integer id;
	private Integer numero;
	private String disponivel;
	private String mesaAtiva;
	
	
	public MesaDTO(Mesa mesa) {
		id = mesa.getId();
		numero = mesa.getNumero();
		disponivel = mesa.getDisponivel().getDescricao();
		mesaAtiva = mesa.getMesaAtiva().getDescricao();
	}
}
