package br.com.sistemalanchonete.pedido.resource;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sistemalanchonete.pedido.dto.InformacaoParaPedidoDTO;
import br.com.sistemalanchonete.pedido.dto.PedidoDTO;
import br.com.sistemalanchonete.pedido.form.PedidoFORM;
import br.com.sistemalanchonete.pedido.form.PedidoLancheFORM;
import br.com.sistemalanchonete.pedido.form.PedidoBebidaFORM;
import br.com.sistemalanchonete.pedido.service.PedidoService;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/pedido")
@RestController
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;
	
	
	@GetMapping
	public ResponseEntity<List<PedidoDTO>> buscarPedidos() {
		return pedidoService.buscarPedidos();
	}
	
	@GetMapping(path = "/informacao-para-pedido")
	public ResponseEntity<InformacaoParaPedidoDTO> buscarInformacaoParaPedido() {
		return pedidoService.buscarInformacaoParaPedido();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Void> cadastrarPedido(@RequestBody @Valid PedidoFORM pedidoFORM) {
		return pedidoService.cadastrarPedido(pedidoFORM);
	}
	
	@PutMapping(path = "/adicionar-lanche/{idPedido}")
	@Transactional
	public ResponseEntity<Void> adicionarLancheAoPedido(@PathVariable Long idPedido, 
			@RequestBody @Valid PedidoLancheFORM pedidoLancheFORM) {
		
		return pedidoService.adicionarLancheAoPedido(idPedido, pedidoLancheFORM);
	}
	
	@PutMapping(path = "/adicionar-bebida/{idPedido}")
	@Transactional
	public ResponseEntity<Void> adicionarBebidaAoPedido(@PathVariable Long idPedido, 
			@RequestBody @Valid PedidoBebidaFORM pedidoProdutoFORM) {
		
		return pedidoService.adicionarBebidaAoPedido(idPedido, pedidoProdutoFORM);
	}
	
	@PutMapping(path = "/definir-pedigo-como-entregue/{idPedido}")
	@Transactional
	public ResponseEntity<Void> definirPedidoComoEntregue(@PathVariable Long idPedido) {
		return pedidoService.definirPedidoComoEntregue(idPedido);
	}
	
	@PutMapping(path = "/excluir-lanche-pedido/{idPedido}/{idLanche}")
	@Transactional
	public ResponseEntity<Void> excluirLancheDoPedido(@PathVariable Long idPedido, @PathVariable Long idLanche) {
		return pedidoService.excluirLancheDoPedido(idPedido, idLanche);
	}
	
	@PutMapping(path = "/excluir-bebida-pedido/{idPedido}/{idProduto}")
	@Transactional
	public ResponseEntity<Void> excluirBebidaDoPedido(@PathVariable Long idPedido, @PathVariable Long idProduto) {
		return pedidoService.excluirBebidaDoPedido(idPedido, idProduto);
	}
	
	@DeleteMapping(path = "/excluir/{id}")
	@Transactional
	public ResponseEntity<Void> excluirPedido(@PathVariable Long id) {
		return pedidoService.excluirPedido(id);
	}
}
