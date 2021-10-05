package br.com.sistemalanchonete.despesa.model;

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

import br.com.sistemalanchonete.despesa.enums.TipoDespesa;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "despesa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Despesa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "O campo 'descricao' não pode ser nulo/vazio!")
	@Size(max = 60, message = "O campo 'descricao' deve ter no máximo {caracteres}!")
	private String descricao;
	
	@Column(name = "tipo")
	@NotNull(message = "O campo 'tipoDespesa' não pode ser nulo!")
	private TipoDespesa tipoDespesa;
	
	@NotNull(message = "O campo 'valor' não pode ser nulo!")
	@Digits(integer = 8, fraction = 2, message = "Formato inválido do campo 'valor'!")
	private BigDecimal valor;
	
	@Column(name = "data_cadastro")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "O campo 'dataCadastro' não pode ser nulo!")
	private LocalDate dataCadastro;
	
	@Column(name = "data_pagamento")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataPagamento;
	
	@Column(name = "data_vencimento")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "O campo 'dataVencimento' não pode ser nulo!")
	private LocalDate dataVencimento;
}
