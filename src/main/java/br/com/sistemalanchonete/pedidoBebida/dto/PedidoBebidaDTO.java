package br.com.sistemalanchonete.pedidoBebida.dto;

import java.util.Comparator;
import java.util.List;

import br.com.sistemalanchonete.pedidoBebida.model.PedidoBebida;
import br.com.sistemalanchonete.produto.dto.ProdutoDTO;
import br.com.sistemalanchonete.utils.ConvertCollection;
import lombok.Getter;

@Getter
public class PedidoBebidaDTO {

	private Long id;
	private String precoUnitario;
	private Integer quantidade;
	private ProdutoDTO bebida;
	
	
	public PedidoBebidaDTO(PedidoBebida pedidoBebida) {
		id = pedidoBebida.getId();
		precoUnitario = pedidoBebida.getPrecoUnitario().toString();
		quantidade = pedidoBebida.getQuantidade();
		bebida = new ProdutoDTO(pedidoBebida.getBebida());
	}
	
	
	public static List<PedidoBebidaDTO> converter(List<PedidoBebida> bebidas) {
		Comparator<PedidoBebida> comparator = (pedidoBebida, pedidoBebida2) -> 
		pedidoBebida.getBebida().getDescricao().compareTo(pedidoBebida2.getBebida().getDescricao());
			
		return ConvertCollection.convertToOrdenedList(bebidas, PedidoBebidaDTO::new, comparator);
	}
}
