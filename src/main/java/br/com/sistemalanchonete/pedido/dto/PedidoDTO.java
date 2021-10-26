package br.com.sistemalanchonete.pedido.dto;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import br.com.sistemalanchonete.cliente.dto.ClienteDTO;
import br.com.sistemalanchonete.lanche.dto.LancheDTO;
import br.com.sistemalanchonete.mesa.dto.MesaDTO;
import br.com.sistemalanchonete.pedido.model.Pedido;
import br.com.sistemalanchonete.produto.dto.ProdutoDTO;
import br.com.sistemalanchonete.utils.ConversaoUtils;
import br.com.sistemalanchonete.utils.ConvertCollection;
import lombok.Getter;

@Getter
public class PedidoDTO {

	private Long id;
	private String observacoes;
	private String entrega;
	private String dataHoraPedido;
	private String dataHoraEntrega;
	private String precoTotal;
	private MesaDTO mesa;
	private ClienteDTO cliente;
	private List<LancheDTO> lanches;
	private List<ProdutoDTO> bebidas;
	
	
	public PedidoDTO(Pedido pedido) {
		id = pedido.getId();
		observacoes = pedido.getObservacoes();
		entrega = pedido.getEntrega().getDescricao();
		dataHoraPedido = ConversaoUtils.converterLocalDateTimeParaStringDataHoraMinuto(pedido.getDataHoraPedido());
		dataHoraEntrega = Objects.nonNull(pedido.getDataHoraEntrega()) 
				? ConversaoUtils.converterLocalDateTimeParaStringDataHoraMinuto(pedido.getDataHoraEntrega()) : null;
		precoTotal = Objects.nonNull(pedido.getPrecoTotal()) ? pedido.getPrecoTotal().toString() : "00.00";
		mesa = Objects.nonNull(pedido.getMesa()) ? new MesaDTO(pedido.getMesa()) : null;
		cliente = Objects.nonNull(pedido.getCliente()) ? new ClienteDTO(pedido.getCliente()) : null;
		lanches = LancheDTO.converter(pedido.getLanches());
		bebidas = ProdutoDTO.converter(pedido.getBebidas());
	}
	
	
	public static List<PedidoDTO> converter(List<Pedido> pedidos) {
		return ConvertCollection.convertToOrdenedList(pedidos, PedidoDTO::new, Comparator.comparing(Pedido::getDataHoraPedido).reversed());
	}
}
