package br.com.sistemalanchonete.produto.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.sistemalanchonete.fornecedor.model.Fornecedor;
import br.com.sistemalanchonete.produto.enums.TipoProduto;
import br.com.sistemalanchonete.produto.model.Produto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoFORM {
	
	@NotNull(message = "O ID do Fornecedor não pode ser nulo!")
	private Long idFornecedor;

	@NotEmpty(message = "O campo 'descricao' não pode ser nulo/vazio!")
	@Size(max = 60, message = "O campo 'descricao' deve ter no máximo {max} caracteres!")
	private String descricao;

	@NotNull(message = "O campo 'preco' não pode ser nulo!")
	@Digits(integer = 6, fraction = 2, message = "O formato do campo 'preco' é inválido")
	private BigDecimal preco;

	@NotNull(message = "O campo 'tipoProduto' não pode ser nulo!")
	private TipoProduto tipoProduto;
	
	@NotNull(message = "O campo 'quantidade' não pode ser nulo!")
	@Min(value = 0, message = "O valor mínimo da 'quantidade' é {value}!")
	private Integer quantidade;

	@NotNull(message = "O campo 'quantidadeMinimaEstoque' não pode ser nulo!")
	@Min(value = 0, message = "O valor mínimo da 'quantidadeMinimaEstoque' é {value}!")
	private Integer quantidadeMinimaEstoque;

	@NotEmpty(message = "O campo 'descricaoEstoque' não pode ser nulo/vazio!")
	@Size(max = 100, message = "O campo 'descricaoEstoque' deve ter no máximo {max} caracteres!")
	private String descricaoEstoque;
	
	
	public Produto converterParaProduto(Fornecedor fornecedor) {
		return Produto.builder()
				.descricao(descricao)
				.preco(preco)
				.tipoProduto(tipoProduto)
				.quantidade(quantidade)
				.quantidadeMinimaEstoque(quantidadeMinimaEstoque)
				.descricaoEstoque(descricaoEstoque)
				.dataCadastro(LocalDate.now())
				.dataUltimaAtualizacao(LocalDate.now())
				.fornecedor(fornecedor)
				.build();
	}
}
