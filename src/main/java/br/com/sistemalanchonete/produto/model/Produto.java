package br.com.sistemalanchonete.produto.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.sistemalanchonete.fornecedor.model.Fornecedor;
import br.com.sistemalanchonete.produto.enums.TipoProduto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@JsonIgnoreProperties(value = { "fornecedor" })
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "O campo 'descricao' não pode ser nulo/vazio!")
	@Size(max = 60, message = "O campo 'descricao' deve ter no máximo {max} caracteres!")
	private String descricao;

	@NotNull(message = "O campo 'preco' não pode ser nulo!")
	@Digits(integer = 6, fraction = 2, message = "O formato do campo 'preco' é inválido")
	private BigDecimal preco;
	
	@Column(name = "tipo")
	@NotNull(message = "O campo 'tipoProduto' não pode ser nulo!")
	private TipoProduto tipoProduto;
	
	@NotNull(message = "O campo 'quantidade' não pode ser nulo!")
	@Min(value = 0, message = "O valor mínimo da 'quantidade' é {value}!")
	private Integer quantidade;
	
	@Column(name = "quantidade_minima_estoque")
	@NotNull(message = "O campo 'quantidadeMinimaEstoque' não pode ser nulo!")
	@Min(value = 0, message = "O valor mínimo da 'quantidadeMinimaEstoque' é {value}!")
	private Integer quantidadeMinimaEstoque;
	
	@Column(name = "descricao_estoque")
	@NotEmpty(message = "O campo 'descricaoEstoque' não pode ser nulo/vazio!")
	@Size(max = 100, message = "O campo 'descricaoEstoque' deve ter no máximo {max} caracteres!")
	private String descricaoEstoque;
	
	@Column(name = "data_cadastro")
	@NotNull(message = "O campo 'dataCadastro' não pode ser nulo!")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;
	
	@Column(name = "data_ultima_atualizacao")
	@NotNull(message = "O campo 'dataUltimaAtualizacao' não pode ser nulo!")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataUltimaAtualizacao;
	
	@JoinColumn(name = "fornecedor_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "O campo 'fornecedor' não pode ser nulo!")
	private Fornecedor fornecedor;
}
