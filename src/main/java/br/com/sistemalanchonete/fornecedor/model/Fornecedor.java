package br.com.sistemalanchonete.fornecedor.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.sistemalanchonete.produto.model.Produto;
import br.com.sistemalanchonete.utils.RegexUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fornecedor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@JsonIgnoreProperties(value = { "produtos" })
public class Fornecedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome_empresa")
	@NotEmpty(message = "O campo 'nomeEmpresa' não deve ser nulo/vazio!")
	@Size(max = 60, message = "O campo 'nomeEmpresa' deve ter no máximo {max} caracteres!")
	private String nomeEmpresa;
	
	@NotEmpty(message = "O campo 'cnpj' não deve ser nulo/vazio!")
	@Size(max = 18, message = "O campo 'cnpj' deve ter no máximo {max} caracteres!")
	@Pattern(regexp = RegexUtils.CNPJ, message = "O formato do campo 'cnpj' está inválido!")
	private String cnpj;
	
	@NotEmpty(message = "O campo 'telefone' não deve ser nulo/vazio!")
	@Size(max = 15, message = "O campo 'telefone' deve ter no máximo {max} caracteres!")
	@Pattern(regexp = RegexUtils.TELEFONE, message = "O formato do campo 'telefone' está inválido!")
	private String telefone;
	
	@Column(name = "telefone_alternativo")
	@Size(max = 15, message = "O campo 'telefoneAlternativo' deve ter no máximo {max} caracteres!")
	private String telefoneAlternativo;
	
	@Column(name = "data_cadastro")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "O campo 'dataCadastro' não deve ser nulo!")
	private LocalDate dataCadastro;
	
	@OneToMany(mappedBy = "fornecedor", fetch = FetchType.LAZY)
	private List<Produto> produtos;
}
