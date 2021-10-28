package br.com.sistemalanchonete.pedidoLanche.form;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.sistemalanchonete.lanche.model.Lanche;
import br.com.sistemalanchonete.pedido.model.Pedido;
import br.com.sistemalanchonete.pedidoLanche.model.PedidoLanche;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoLancheFORM {

	@NotNull(message = "O campo 'idLanche' não pode ser nulo!")
	private Long idLanche;
	
	@NotNull(message = "O campo 'quantidade' não pode ser nulo!")
	@Min(value = 1, message = "A quantidade mínima para escolha do lanche é {value}!")
	private Integer quantidade;
	
	@Size(max = 200, message = "O campo 'observacoes' deve ter no máximo {max} caracteres!")
	private String observacoes;
	
	
	public void atualizarLanchesDoPedido(Pedido pedido, Lanche lanche) {
		List<PedidoLanche> lanchesDoPedido = pedido.getLanches();
		BigDecimal precoTotalPedido = Objects.nonNull(pedido.getPrecoTotal()) ? pedido.getPrecoTotal() : new BigDecimal("0");
		
		lanchesDoPedido.add(converterParaPedidoLanche(pedido, lanche));
		precoTotalPedido = precoTotalPedido.add(lanche.getPreco().multiply(new BigDecimal(quantidade)));
		
		pedido.setLanches(lanchesDoPedido);
		pedido.setPrecoTotal(precoTotalPedido);
		pedido.setObservacoes(observacoes);
	}
	
	private PedidoLanche converterParaPedidoLanche(Pedido pedido, Lanche lanche) {
		return PedidoLanche.builder()
				.precoUnitario(lanche.getPreco())
				.quantidade(quantidade)
				.pedido(pedido)
				.lanche(lanche)
				.build();			
	}
}
