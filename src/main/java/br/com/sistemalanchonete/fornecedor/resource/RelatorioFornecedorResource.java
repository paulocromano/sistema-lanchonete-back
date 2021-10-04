package br.com.sistemalanchonete.fornecedor.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemalanchonete.fornecedor.service.RelatorioFornecedorService;

@RequestMapping(path = "relatorio-fornecedor")
@RestController
public class RelatorioFornecedorResource {

	@Autowired
	private RelatorioFornecedorService relatorioFornecedorService;
}
