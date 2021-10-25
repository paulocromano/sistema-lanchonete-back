package br.com.sistemalanchonete.mesa.dto;

import java.util.Comparator;
import java.util.List;

import br.com.sistemalanchonete.mesa.model.Mesa;
import br.com.sistemalanchonete.utils.ConvertCollection;
import lombok.Getter;

@Getter
public class MesaDTO {

	private Integer id;
	private Integer numero;
	private String mesaAtiva;
	
	
	public MesaDTO(Mesa mesa) {
		id = mesa.getId();
		numero = mesa.getNumero();
		mesaAtiva = mesa.getMesaAtiva().getDescricao();
	}
	
	
	public static List<MesaDTO> converter(List<Mesa> mesas) {
		return ConvertCollection.convertToOrdenedList(mesas, MesaDTO::new, Comparator.comparingInt(Mesa::getNumero));
	}
}
