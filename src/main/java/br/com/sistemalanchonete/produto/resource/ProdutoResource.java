package br.com.sistemalanchonete.produto.resource;

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

import br.com.sistemalanchonete.produto.dto.ProdutoDTO;
import br.com.sistemalanchonete.produto.form.AlteracaoProdutoFORM;
import br.com.sistemalanchonete.produto.form.ProdutoFORM;
import br.com.sistemalanchonete.produto.service.ProdutoService;

@RequestMapping(path = "/produto")
@RestController
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;
	
	
	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> buscarProdutos() {
		return produtoService.buscarProdutos();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Void> cadastrarProduto(@RequestBody @Valid ProdutoFORM produtoFORM) {
		return produtoService.cadastrarProduto(produtoFORM);
	}
	
	@PutMapping(path = "/alterar/{id}")
	@Transactional
	public ResponseEntity<Void> alterarDadosProduto(@PathVariable Long id,
			@RequestBody @Valid AlteracaoProdutoFORM alteracaoProdutoFORM) {
		
		return produtoService.alterarDadosProduto(id, alteracaoProdutoFORM);
	}
	
	@DeleteMapping(path = "/excluir/{id}")
	@Transactional
	public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
		return produtoService.excluirProduto(id);
	}
}
