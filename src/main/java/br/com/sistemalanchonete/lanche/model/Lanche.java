package br.com.sistemalanchonete.lanche.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lanche")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Lanche {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	@NotEmpty(message = "O campo 'nome' não pode ser nulo/vazio!")
	@Size(max = 60, message = "O campo 'nome' deve ter no máximo {max} caracteres!")
	private String nome;
	
	@NotEmpty(message = "O campo 'ingredientes' não pode ser nulo/vazio!")
	@Size(max = 200, message = "O campo 'ingredientes' deve ter no máximo {max} caracteres!")
	private String ingredientes;
	
	@NotNull(message = "O campo 'preco' não pode ser nulo!")
	@Digits(integer = 6, fraction = 2, message = "Formato do campo 'preco' é inválido!")
	private BigDecimal preco;
	
	@Column(name = "imagem")
	@NotEmpty(message = "O campo 'imagem' não pode ser nulo/vazio!")
	@Size(max = 8000, message = "O campo 'imagem' deve ter no máximo {max} caracteres!")
	private String imagemBase64;
	
	@Column(name = "data_cadastro")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "O campo 'dataCadastro' não deve ser nulo!")
	private LocalDate dataCadastro;
}
