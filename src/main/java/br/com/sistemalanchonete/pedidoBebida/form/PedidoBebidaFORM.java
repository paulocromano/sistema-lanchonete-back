package br.com.sistemalanchonete.pedidoBebida.form;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.sistemalanchonete.pedido.model.Pedido;
import br.com.sistemalanchonete.pedidoBebida.model.PedidoBebida;
import br.com.sistemalanchonete.produto.model.Produto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoBebidaFORM {

	@NotNull(message = "O campo 'idProduto' não pode ser nulo!")
	private Long idProduto;
	
	@NotNull(message = "O campo 'quantidade' não pode ser nulo!")
	@Min(value = 1, message = "A quantidade mínima para escolha do lanche é {value}!")
	private Integer quantidade;
	
	@Size(max = 200, message = "O campo 'observacoes' deve ter no máximo {max} caracteres!")
	private String observacoes;
	
	
	public void atualizarBebidasDoPedido(Pedido pedido, Produto bebida) {
		List<PedidoBebida> bebidasDoPedido = pedido.getBebidas();
		BigDecimal precoTotalPedido = Objects.nonNull(pedido.getPrecoTotal()) ? pedido.getPrecoTotal() : new BigDecimal("0");
		
		bebidasDoPedido.add(converterParaPedidoBebida(pedido, bebida));
		precoTotalPedido = precoTotalPedido.add(bebida.getPreco().multiply(new BigDecimal(quantidade)));
		
		pedido.setBebidas(bebidasDoPedido);
		pedido.setPrecoTotal(precoTotalPedido);
		pedido.setObservacoes(observacoes);
	}
	
	private PedidoBebida converterParaPedidoBebida(Pedido pedido, Produto bebida) {
		return PedidoBebida.builder()
				.precoUnitario(bebida.getPreco())
				.quantidade(quantidade)
				.pedido(pedido)
				.bebida(bebida)
				.build();			
	}
}
