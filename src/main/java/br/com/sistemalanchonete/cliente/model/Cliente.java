package br.com.sistemalanchonete.cliente.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.sistemalanchonete.endereco.model.Endereco;
import br.com.sistemalanchonete.pedido.model.Pedido;
import br.com.sistemalanchonete.utils.RegexUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "O campo 'nome' não pode ser nulo/vazio!")
	@Size(max = 100, message = "O campo 'nome' deve ter no máximo {max} caracteres!")
	private String nome;
	
	@NotEmpty(message = "O campo 'telefone' não deve ser nulo/vazio!")
	@Size(max = 15, message = "O campo 'telefone' deve ter no máximo {max} caracteres!")
	@Pattern(regexp = RegexUtils.TELEFONE, message = "O formato do campo 'telefone' está inválido!")
	private String telefone;
	
	@Column(name = "telefone_recado")
	@Size(max = 15, message = "O campo 'telefoneRecado' deve ter no máximo {max} caracteres!")
	private String telefoneRecado;
	
	@Column(name = "data_cadastro")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "O campo 'dataCadastro' não deve ser nulo!")
	private LocalDate dataCadastro;
	
	@JoinColumn(name = "endereco_id")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@NotNull(message = "O campo 'endereco' não pode ser nulo!")
	private Endereco endereco;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Pedido> pedidos;
}
