package br.com.sistemalanchonete.pedidoBebida.service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.sistemalanchonete.exception.custom.ObjectNotFoundException;
import br.com.sistemalanchonete.pedido.model.Pedido;
import br.com.sistemalanchonete.pedido.service.PedidoService;
import br.com.sistemalanchonete.pedidoBebida.form.PedidoBebidaFORM;
import br.com.sistemalanchonete.pedidoBebida.model.PedidoBebida;
import br.com.sistemalanchonete.pedidoBebida.repository.PedidoBebidaRepository;
import br.com.sistemalanchonete.produto.enums.TipoProduto;
import br.com.sistemalanchonete.produto.model.Produto;
import br.com.sistemalanchonete.produto.service.ProdutoService;

@Service
public class PedidoBebidaService {
	
	@Autowired
	private PedidoBebidaRepository pedidoBebidaRepository;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ProdutoService produtoService;

	
	public ResponseEntity<Void> adicionarBebidaAoPedido(Long idPedido, PedidoBebidaFORM pedidoProdutoFORM) {
		Pedido pedido = pedidoService.verificarSePedidoExiste(idPedido);
		Produto bebida = produtoService.verificarSeProdutoExiste(pedidoProdutoFORM.getIdProduto());
		verificarSeTipoProdutoIgualBebida(bebida);
		pedidoProdutoFORM.atualizarBebidasDoPedido(pedido, bebida);
		
		return ResponseEntity.ok().build();
	}
	
	public void verificarSeTipoProdutoIgualBebida(Produto produto) {
		if (!produto.getTipoProduto().equals(TipoProduto.BEBIDA)) 
			throw new IllegalArgumentException("Tipo de Produto inválido para Pedido!");
	}
	
	public ResponseEntity<Void> excluirBebidaDoPedido(Long idPedido, Long idPedidoBebida) {
		Pedido pedido = pedidoService.verificarSePedidoExiste(idPedido);
		PedidoBebida bebidaPedido = verificarSeBebidaDoPedidoExiste(idPedido, idPedidoBebida);
		pedido.setPrecoTotal(pedido.getPrecoTotal()
				.subtract(bebidaPedido.getBebida().getPreco()
						.multiply(new BigDecimal(bebidaPedido.getQuantidade()))));
		pedidoBebidaRepository.delete(bebidaPedido);
		
		return ResponseEntity.ok().build();
	}
	
	private PedidoBebida verificarSeBebidaDoPedidoExiste(Long idPedido, Long idPedidoBebida) {
		if (Objects.isNull(idPedidoBebida))
			throw new NullPointerException("O ID do Pedido de Bebida não pode ser nulo!");
		
		Optional<PedidoBebida> pedidoBebida = pedidoBebidaRepository.findByIdAndPedido_id(idPedidoBebida, idPedido);
		
		if (pedidoBebida.isEmpty())
			throw new ObjectNotFoundException("Pedido de Bebida não encontrado!");
		
		return pedidoBebida.get();
	}
}
