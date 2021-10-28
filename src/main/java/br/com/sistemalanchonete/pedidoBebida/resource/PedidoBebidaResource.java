package br.com.sistemalanchonete.pedidoBebida.resource;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemalanchonete.pedidoBebida.form.PedidoBebidaFORM;
import br.com.sistemalanchonete.pedidoBebida.service.PedidoBebidaService;

@RequestMapping(path = "/pedido-bebida")
@RestController
public class PedidoBebidaResource {

	@Autowired
	private PedidoBebidaService pedidoBebidaService;
	
	
	@PutMapping(path = "/adicionar-bebida/{idPedido}")
	@Transactional
	public ResponseEntity<Void> adicionarBebidaAoPedido(@PathVariable Long idPedido, 
			@RequestBody @Valid PedidoBebidaFORM pedidoProdutoFORM) {
		
		return pedidoBebidaService.adicionarBebidaAoPedido(idPedido, pedidoProdutoFORM);
	}
	
	@PutMapping(path = "/excluir-bebida-pedido/{idPedido}/{idPedidoBebida}")
	@Transactional
	public ResponseEntity<Void> excluirBebidaDoPedido(@PathVariable Long idPedido, @PathVariable Long idPedidoBebida) {
		return pedidoBebidaService.excluirBebidaDoPedido(idPedido, idPedidoBebida);
	}
}
