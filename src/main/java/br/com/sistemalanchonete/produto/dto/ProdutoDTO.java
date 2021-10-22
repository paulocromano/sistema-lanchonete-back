package br.com.sistemalanchonete.produto.dto;

import java.util.Comparator;
import java.util.List;

import br.com.sistemalanchonete.fornecedor.dto.FornecedorDTO;
import br.com.sistemalanchonete.produto.model.Produto;
import br.com.sistemalanchonete.utils.ConversaoUtils;
import br.com.sistemalanchonete.utils.ConvertCollection;
import lombok.Getter;

@Getter
public class ProdutoDTO {

	private Long id;
	private String descricao;
	private String preco;
	private String tipoProduto;
	private Integer quantidade;
	private Integer quantidadeMinimaEstoque;
	private String descricaoEstoque;
	private String dataCadastro;
	private String dataUltimaAtualizacao;
	private FornecedorDTO fornecedor;
	
	
	public ProdutoDTO(Produto produto) {
		id = produto.getId();
		descricao = produto.getDescricao();
		preco = produto.getPreco().toString();
		tipoProduto = produto.getTipoProduto().getDescricao();
		quantidade = produto.getQuantidade();
		quantidadeMinimaEstoque = produto.getQuantidadeMinimaEstoque();
		descricaoEstoque = produto.getDescricaoEstoque();
		dataCadastro = ConversaoUtils.converterLocalDateParaString(produto.getDataCadastro());
		dataUltimaAtualizacao = ConversaoUtils.converterLocalDateParaString(produto.getDataUltimaAtualizacao());
		fornecedor = new FornecedorDTO(produto.getFornecedor());
	}
	
	
	public static List<ProdutoDTO> converter(List<Produto> produtos) {
		return ConvertCollection.convertToOrdenedList(produtos, ProdutoDTO::new, Comparator.comparing(Produto::getDescricao));
	}
}
