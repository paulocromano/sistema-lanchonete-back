package br.com.sistemalanchonete.pedido.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.sistemalanchonete.pedido.model.Pedido;
import br.com.sistemalanchonete.pedidoBebida.model.PedidoBebida;
import br.com.sistemalanchonete.pedidoLanche.model.PedidoLanche;
import br.com.sistemalanchonete.utils.ConversaoUtils;
import br.com.sistemalanchonete.utils.ConvertCollection;
import lombok.Getter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Getter
public class RelatorioPedidoDTO {

	private Long id;
	private String observacoes;
	private String entrega;
	private String dataHoraPedido;
	private String precoTotal;
	private String pedidoFinalizado;
	private String nomeCliente;
	private String descricaoPedidoLanches;
	private String descricaoPedidoBebidas;
	
	
	public RelatorioPedidoDTO(Pedido pedido) {
		id = pedido.getId();
		observacoes = Objects.nonNull(pedido.getObservacoes()) ? pedido.getObservacoes() : "---";
		entrega = pedido.getEntrega().getDescricao();
		dataHoraPedido = ConversaoUtils.converterLocalDateTimeParaStringDataHoraMinuto(pedido.getDataHoraPedido());
		precoTotal = Objects.nonNull(pedido.getPrecoTotal()) ? pedido.getPrecoTotal().toString() : "00.00";
		pedidoFinalizado = pedido.getPedidoFinalizado().getDescricao();
		nomeCliente = Objects.nonNull(pedido.getCliente()) ? pedido.getCliente().getNome() : "---";
		gerarDescricaoLanches(pedido.getLanches());
		gerarDescricaoBebidas(pedido.getBebidas());
	}
	
	
	private void gerarDescricaoLanches(List<PedidoLanche> pedidoLanches) {
		descricaoPedidoLanches = (Objects.isNull(pedidoLanches) || pedidoLanches.isEmpty()) ? "---"
				: pedidoLanches.stream().map(lanche -> lanche.getQuantidade() + " - " + lanche.getLanche().getNome())
					.collect(Collectors.joining(", "));
	}
	
	private void gerarDescricaoBebidas(List<PedidoBebida> pedidoBebidas) {
		descricaoPedidoBebidas = (Objects.isNull(pedidoBebidas) || pedidoBebidas.isEmpty()) ? "---"
				: pedidoBebidas.stream().map(bebida -> bebida.getQuantidade() + " - " + bebida.getBebida().getDescricao())
					.collect(Collectors.joining(", "));
	}
	
	public static Map<String, Object> gerarMapComParametrosDoRelatorio(List<Pedido> pedidos) {
		Map<String, Object> parametros = new HashMap<>();
		BigDecimal precoTotalPedidos = pedidos.stream().map(Pedido::getPrecoTotal).reduce(new BigDecimal("0"), BigDecimal::add);
		
		parametros.put("pedidos", converter(pedidos));
		parametros.put("precoTotalPedidos", precoTotalPedidos.toString());
		parametros.put("dataHorarioGeracaoRelatorio", ConversaoUtils.converterLocalDateTimeParaFrontEndEmString(
				LocalDateTime.now()));
		
		return parametros;
	}
	
	private static JRBeanCollectionDataSource converter(List<Pedido> pedidos) {
		Comparator<Pedido> comparator = Comparator.comparing(Pedido::getDataHoraPedido).reversed();
		
		List<RelatorioPedidoDTO> pedidosParaRelatorio = ConvertCollection.convertToOrdenedList(
				pedidos, RelatorioPedidoDTO::new, comparator);
		
		return new JRBeanCollectionDataSource(pedidosParaRelatorio, false);
	}
}
