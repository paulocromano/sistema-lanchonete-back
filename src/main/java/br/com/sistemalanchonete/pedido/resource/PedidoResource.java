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
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemalanchonete.pedido.dto.InformacaoParaPedidoDTO;
import br.com.sistemalanchonete.pedido.dto.PedidoDTO;
import br.com.sistemalanchonete.pedido.form.PedidoFORM;
import br.com.sistemalanchonete.pedido.service.PedidoService;

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

	@PutMapping(path = "/definir-pedigo-como-entregue/{idPedido}")
	@Transactional
	public ResponseEntity<Void> definirPedidoComoEntregue(@PathVariable Long idPedido) {
		return pedidoService.definirPedidoComoEntregue(idPedido);
	}
	
	@DeleteMapping(path = "/excluir/{id}")
	@Transactional
	public ResponseEntity<Void> excluirPedido(@PathVariable Long id) {
		return pedidoService.excluirPedido(id);
	}
}
