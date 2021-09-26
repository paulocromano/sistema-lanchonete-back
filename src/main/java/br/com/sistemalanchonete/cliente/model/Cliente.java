package br.com.sistemalanchonete.cliente.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.sistemalanchonete.endereco.model.Endereco;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@Getter
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
}
