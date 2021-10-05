package br.com.sistemalanchonete.fornecedor.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemalanchonete.fornecedor.service.RelatorioFornecedorService;

@RequestMapping(path = "relatorio-fornecedor")
@RestController
public class RelatorioFornecedorResource {

	@Autowired
	private RelatorioFornecedorService relatorioFornecedorService;
	
	
	@GetMapping(path = "/todos-fornecedores")
	public ResponseEntity<byte[]> gerarRelatorioDosFornecedores() {
		return relatorioFornecedorService.gerarRelatorioDosFornecedores();
	}
}
