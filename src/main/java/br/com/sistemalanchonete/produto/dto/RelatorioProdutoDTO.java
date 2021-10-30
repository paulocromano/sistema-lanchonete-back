package br.com.sistemalanchonete.produto.dto;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sistemalanchonete.produto.model.Produto;
import br.com.sistemalanchonete.utils.ConversaoUtils;
import br.com.sistemalanchonete.utils.ConvertCollection;
import br.com.sistemalanchonete.utils.FormatacaoUtils;
import lombok.Getter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Getter
public class RelatorioProdutoDTO {

	private String descricao;
	private String preco;
	private String tipoProduto;
	private Integer quantidade;
	private Integer quantidadeMinimaEstoque;
	private String descricaoEstoque;
	private String dataCadastro;
	private String dataUltimaAtualizacao;
	private String nomeEmpresa;
	
	
	public RelatorioProdutoDTO(Produto produto) {
		descricao = produto.getDescricao();
		preco = produto.getPreco().toString();
		tipoProduto = produto.getTipoProduto().getDescricao();
		quantidade = produto.getQuantidade();
		quantidadeMinimaEstoque = produto.getQuantidadeMinimaEstoque();
		descricaoEstoque = produto.getDescricaoEstoque();
		dataCadastro = ConversaoUtils.converterLocalDateParaString(produto.getDataCadastro());
		dataUltimaAtualizacao = ConversaoUtils.converterLocalDateParaString(produto.getDataUltimaAtualizacao());
		nomeEmpresa = produto.getFornecedor().getNomeEmpresa();
	}
	
	
	public static Map<String, Object> gerarMapComParametrosDoRelatorio(List<Produto> produtos) {
		Map<String, Object> parametros = new HashMap<>();
		
		parametros.put("produtos", converter(produtos));
		parametros.put("dataHorarioGeracaoRelatorio", ConversaoUtils.converterLocalDateTimeParaFrontEndEmString(
				LocalDateTime.now()));
		
		return parametros;
	}
	
	private static JRBeanCollectionDataSource converter(List<Produto> produtos) {
		Comparator<Produto> comparator = (produto, produto2) -> FormatacaoUtils.COLLATOR
				.compare(produto.getDescricao(), produto2.getDescricao());
		
		List<RelatorioProdutoDTO> produtosParaRelatorio = ConvertCollection.convertToOrdenedList(
				produtos, RelatorioProdutoDTO::new, comparator);
		
		return new JRBeanCollectionDataSource(produtosParaRelatorio, false);
	}
}
