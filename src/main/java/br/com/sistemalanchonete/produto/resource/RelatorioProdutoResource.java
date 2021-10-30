package br.com.sistemalanchonete.produto.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemalanchonete.produto.service.RelatorioProdutoService;

@RequestMapping(path = "relatorio-produto")
@RestController
public class RelatorioProdutoResource {

	@Autowired
	private RelatorioProdutoService relatorioProdutoService;
	
	
	@GetMapping(path = "/todos-produtos")
	public ResponseEntity<byte[]> gerarRelatorioComTodosProdutos() {
		return relatorioProdutoService.gerarRelatorioComTodosProdutos();
	}
	
	@GetMapping(path = "/produtos-abaixo-estoque-minimo")
	public ResponseEntity<byte[]> gerarRelatorioComProdutosAbaixoDoEstoqueMinimo() {
		return relatorioProdutoService.gerarRelatorioComProdutosAbaixoDoEstoqueMinimo();
	}
}
