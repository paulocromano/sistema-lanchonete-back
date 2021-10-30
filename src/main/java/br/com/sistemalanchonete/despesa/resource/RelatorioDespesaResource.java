package br.com.sistemalanchonete.despesa.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemalanchonete.despesa.service.RelatorioDespesaService;

@RequestMapping(path = "/relatorio-despesa")
@RestController
public class RelatorioDespesaResource {

	@Autowired
	private RelatorioDespesaService relatorioDespesaService;
	
	
	@GetMapping(path = "/todas-despesas")
	public ResponseEntity<byte[]> gerarRelatorioTodasDespesas() {
		return relatorioDespesaService.gerarRelatorioTodasDespesas();
	}
	
	@GetMapping(path = "/despesas-pagas")
	public ResponseEntity<byte[]> gerarRelatorioDespesasPagas() {
		return relatorioDespesaService.gerarRelatorioDespesasPagas();
	}
	
	@GetMapping(path = "/despesas-vencidas")
	public ResponseEntity<byte[]> gerarRelatorioDespesasVencidas() {
		return relatorioDespesaService.gerarRelatorioDespesasVencidas();
	}
}
