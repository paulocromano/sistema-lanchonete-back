package br.com.sistemalanchonete.pedidoLanche.dto;

import java.util.Comparator;
import java.util.List;

import br.com.sistemalanchonete.lanche.dto.LancheDTO;
import br.com.sistemalanchonete.pedidoLanche.model.PedidoLanche;
import br.com.sistemalanchonete.utils.ConvertCollection;
import lombok.Getter;

@Getter
public class PedidoLancheDTO {

	private Long id;
	private String precoUnitario;
	private Integer quantidade;
	private LancheDTO lanche;
	
	
	public PedidoLancheDTO(PedidoLanche pedidoLanche) {
		id = pedidoLanche.getId();
		precoUnitario = pedidoLanche.getPrecoUnitario().toString();
		quantidade = pedidoLanche.getQuantidade();
		lanche = new LancheDTO(pedidoLanche.getLanche());
	}
	
	
	public static List<PedidoLancheDTO> converter(List<PedidoLanche> lanches) {
		Comparator<PedidoLanche> comparator = (pedidoLanche, pedidoLanche2) -> 
			pedidoLanche.getLanche().getNome().compareTo(pedidoLanche2.getLanche().getNome());
			
		return ConvertCollection.convertToOrdenedList(lanches, PedidoLancheDTO::new, comparator);
	}
}
