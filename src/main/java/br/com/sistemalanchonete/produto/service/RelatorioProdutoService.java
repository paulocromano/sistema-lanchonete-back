package br.com.sistemalanchonete.produto.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.sistemalanchonete.exception.custom.EmptyDataResultException;
import br.com.sistemalanchonete.produto.dto.RelatorioProdutoDTO;
import br.com.sistemalanchonete.produto.model.Produto;
import br.com.sistemalanchonete.produto.repository.ProdutoRepository;
import br.com.sistemalanchonete.utils.RelatorioUtils;

@Service
public class RelatorioProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	public ResponseEntity<byte[]> gerarRelatorioComTodosProdutos() {
		List<Produto> produtos = produtoRepository.findAll();
		
		if (produtos.isEmpty())
			throw new EmptyDataResultException("Nenhum produto cadastrado!");
		
		Map<String, Object> parametros = RelatorioProdutoDTO.gerarMapComParametrosDoRelatorio(produtos);
		parametros.put("titulo", "Relatório de Produtos");
		
		return RelatorioUtils.gerarRelatorioEmPDF("produtos", parametros);
	}
	
	public ResponseEntity<byte[]> gerarRelatorioComProdutosAbaixoDoEstoqueMinimo() {
		List<Produto> produtos = produtoRepository.findAll().stream()
				.filter(produto -> produto.getQuantidade() < produto.getQuantidadeMinimaEstoque()).collect(Collectors.toList());
		
		if (produtos.isEmpty())
			throw new EmptyDataResultException("Nenhum produto abaixo do estoque mínimo encontrado!");
		
		Map<String, Object> parametros = RelatorioProdutoDTO.gerarMapComParametrosDoRelatorio(produtos);
		parametros.put("titulo", "Relatório de Produtos Abaixo do Estoque Mínimo");
		
		return RelatorioUtils.gerarRelatorioEmPDF("produtos", parametros);
	}
}
