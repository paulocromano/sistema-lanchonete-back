package br.com.sistemalanchonete.produto.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.sistemalanchonete.produto.model.Produto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlteracaoProdutoFORM {

	@NotNull(message = "O campo 'preco' não pode ser nulo!")
	@Digits(integer = 6, fraction = 2, message = "O formato do campo 'preco' é inválido")
	private BigDecimal preco;
	
	@NotNull(message = "O campo 'quantidade' não pode ser nulo!")
	@Min(value = 0, message = "O valor mínimo da 'quantidade' é {value}!")
	private Integer quantidade;

	@NotNull(message = "O campo 'quantidadeMinimaEstoque' não pode ser nulo!")
	@Min(value = 0, message = "O valor mínimo da 'quantidadeMinimaEstoque' é {value}!")
	private Integer quantidadeMinimaEstoque;

	@NotEmpty(message = "O campo 'descricaoEstoque' não pode ser nulo/vazio!")
	@Size(max = 100, message = "O campo 'descricaoEstoque' deve ter no máximo {max} caracteres!")
	private String descricaoEstoque;
	
	
	public void atualizarDadosProduto(Produto produto) {
		produto.setPreco(preco);
		produto.setQuantidade(quantidade);
		produto.setQuantidadeMinimaEstoque(quantidadeMinimaEstoque);
		produto.setDescricaoEstoque(descricaoEstoque);
		produto.setDataUltimaAtualizacao(LocalDate.now());
	}
}
