package br.com.sistemalanchonete.cliente.resource;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemalanchonete.cliente.service.RelatorioClienteService;

@RequestMapping(path = "/relatorio-cliente")
@RestController
public class RelatorioClienteResource {

	@Autowired
	private RelatorioClienteService relatorioClienteService;
	
	
	@GetMapping(path = "/todos-clientes")
	public ResponseEntity<byte[]> gerarRelatorioComTodosClientes() {
		return relatorioClienteService.gerarRelatorioComTodosClientes();
	}
	
	@GetMapping(path = "/cadastrados-entre-periodo")
	public ResponseEntity<byte[]> gerarRelatorioDeClientesCadastradosEntrePeriodos(
			@RequestParam LocalDate dataInicial, @RequestParam LocalDate dataFinal) {
		
		return relatorioClienteService.gerarRelatorioDeClientesCadastradosEntrePeriodos(dataInicial, dataFinal);
	}
}
