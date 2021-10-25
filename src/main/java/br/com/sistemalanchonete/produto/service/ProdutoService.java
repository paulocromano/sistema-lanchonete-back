package br.com.sistemalanchonete.produto.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.sistemalanchonete.exception.custom.ObjectNotFoundException;
import br.com.sistemalanchonete.fornecedor.model.Fornecedor;
import br.com.sistemalanchonete.fornecedor.service.FornecedorService;
import br.com.sistemalanchonete.produto.dto.ProdutoDTO;
import br.com.sistemalanchonete.produto.enums.TipoProduto;
import br.com.sistemalanchonete.produto.form.AlteracaoProdutoFORM;
import br.com.sistemalanchonete.produto.form.ProdutoFORM;
import br.com.sistemalanchonete.produto.model.Produto;
import br.com.sistemalanchonete.produto.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FornecedorService fornecedorService;
	
	
	public ResponseEntity<List<ProdutoDTO>> buscarProdutos() {
		List<Produto> produtos = produtoRepository.findAll();
		return ResponseEntity.ok().body(ProdutoDTO.converter(produtos));
	}
	
	public ResponseEntity<List<ProdutoDTO>> buscarBebidas() {
		List<Produto> bebidas = produtoRepository.findByTipoProduto(TipoProduto.BEBIDA);
		return ResponseEntity.ok().body(ProdutoDTO.converter(bebidas));
	}
	
	public ResponseEntity<Void> cadastrarProduto(ProdutoFORM produtoFORM) {
		Fornecedor fornecedor = fornecedorService.verificarSeFornecedorExiste(produtoFORM.getIdFornecedor());
		produtoRepository.save(produtoFORM.converterParaProduto(fornecedor));
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	public ResponseEntity<Void> alterarDadosProduto(Long id, AlteracaoProdutoFORM alteracaoProdutoFORM) {
		Produto produto = verificarSeProdutoExiste(id);
		alteracaoProdutoFORM.atualizarDadosProduto(produto);
		
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Void> excluirProduto(Long id) {
		Produto produto = verificarSeProdutoExiste(id);
		produtoRepository.delete(produto);
		
		return ResponseEntity.noContent().build();
	}
	
	public Produto verificarSeProdutoExiste(Long id) {
		if (Objects.isNull(id))
			throw new NullPointerException("O ID do Produto não pode ser nulo!");
		
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if (produto.isEmpty())
			throw new ObjectNotFoundException("PRoduto não encontrado!");
		
		return produto.get();
	}
}
