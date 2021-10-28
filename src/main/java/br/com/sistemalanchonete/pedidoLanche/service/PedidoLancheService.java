package br.com.sistemalanchonete.pedidoLanche.service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.sistemalanchonete.exception.custom.ObjectNotFoundException;
import br.com.sistemalanchonete.lanche.model.Lanche;
import br.com.sistemalanchonete.lanche.service.LancheService;
import br.com.sistemalanchonete.pedido.model.Pedido;
import br.com.sistemalanchonete.pedido.service.PedidoService;
import br.com.sistemalanchonete.pedidoLanche.form.PedidoLancheFORM;
import br.com.sistemalanchonete.pedidoLanche.model.PedidoLanche;
import br.com.sistemalanchonete.pedidoLanche.repository.PedidoLancheRepository;

@Service
public class PedidoLancheService {
	
	@Autowired
	private PedidoLancheRepository pedidoLancheRepository;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private LancheService lancheService;

	
	public ResponseEntity<Void> adicionarLancheAoPedido(Long idPedido, PedidoLancheFORM pedidoLancheFORM) {
		Pedido pedido = pedidoService.verificarSePedidoExiste(idPedido);
		Lanche lanche = lancheService.verificarSeLancheExiste(pedidoLancheFORM.getIdLanche());
		pedidoLancheFORM.atualizarLanchesDoPedido(pedido, lanche);
		
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Void> excluirLancheDoPedido(Long idPedido, Long idLanche) {
		Pedido pedido = pedidoService.verificarSePedidoExiste(idPedido);
		PedidoLanche lanchePedido = verificarSeLancheDoPedidoExiste(idPedido, idLanche);
		pedido.setPrecoTotal(pedido.getPrecoTotal()
				.subtract(lanchePedido.getLanche().getPreco()
						.multiply(new BigDecimal(lanchePedido.getQuantidade()))));
		pedidoLancheRepository.delete(lanchePedido);
		
		return ResponseEntity.ok().build();
	}
	
	private PedidoLanche verificarSeLancheDoPedidoExiste(Long idPedido, Long idLanchePedido) {
		if (Objects.isNull(idLanchePedido))
			throw new NullPointerException("O ID do Pedido de Lanche não pode ser nulo!");
		
		Optional<PedidoLanche> pedidoLanche = pedidoLancheRepository.findByIdAndPedido_Id(idLanchePedido, idPedido);
		
		if (pedidoLanche.isEmpty())
			throw new ObjectNotFoundException("Pedido de Lanche não encontrado!");
		
		return pedidoLanche.get();
	}
}
