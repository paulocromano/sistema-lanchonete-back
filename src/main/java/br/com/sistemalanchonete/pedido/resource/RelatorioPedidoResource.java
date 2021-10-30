package br.com.sistemalanchonete.pedido.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemalanchonete.pedido.service.RelatorioPedidoService;

@RequestMapping(path = "/relatorio-pedido")
@RestController
public class RelatorioPedidoResource {

	@Autowired
	private RelatorioPedidoService relatorioPedidoService;
	
	
	@GetMapping(path = "/todos-pedidos")
	public ResponseEntity<byte[]> gerarRelatorioComTodosPedidos() {
		return relatorioPedidoService.gerarRelatorioComTodosPedidos();
	}
}
