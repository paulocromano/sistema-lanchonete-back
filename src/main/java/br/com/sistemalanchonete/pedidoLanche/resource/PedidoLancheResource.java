package br.com.sistemalanchonete.pedidoLanche.resource;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemalanchonete.pedidoLanche.form.PedidoLancheFORM;
import br.com.sistemalanchonete.pedidoLanche.service.PedidoLancheService;

@RequestMapping(path = "/pedido-lanche")
@RestController
public class PedidoLancheResource {

	@Autowired
	private PedidoLancheService pedidoLancheService;
	
	
	@PutMapping(path = "/adicionar-lanche/{idPedido}")
	@Transactional
	public ResponseEntity<Void> adicionarLancheAoPedido(@PathVariable Long idPedido, 
			@RequestBody @Valid PedidoLancheFORM pedidoLancheFORM) {
		
		return pedidoLancheService.adicionarLancheAoPedido(idPedido, pedidoLancheFORM);
	}
	
	@PutMapping(path = "/excluir-lanche-pedido/{idPedido}/{idLanche}")
	@Transactional
	public ResponseEntity<Void> excluirLancheDoPedido(@PathVariable Long idPedido, @PathVariable Long idLanche) {
		return pedidoLancheService.excluirLancheDoPedido(idPedido, idLanche);
	}
}
