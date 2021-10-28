package br.com.sistemalanchonete.pedidoLanche.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.sistemalanchonete.lanche.model.Lanche;
import br.com.sistemalanchonete.pedido.model.Pedido;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pedido_has_lanche")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@JsonIgnoreProperties(value = { "pedido" })
public class PedidoLanche {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "preco_unitario")
	@NotNull(message = "O campo 'precoUnitario' não pode ser nulo!")
	@Digits(integer = 6, fraction = 2, message = "O formato do campo 'precoUnitario' é inválido")
	@Min(value = 1, message = "O campo '' deve ter o valor mínimo de {value}!")
	private BigDecimal precoUnitario;
	
	@NotNull(message = "O campo 'quantidade' não pode ser nulo!")
	@Min(value = 1, message = "O campo 'quantidade' deve ter o valor mínimo de {value}!")
	private Integer quantidade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido_id")
	@NotNull(message = "O campo 'pedido' não pode ser nulo!")
	private Pedido pedido;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lanche_id")
	@NotNull(message = "O campo 'lanche' não pode ser nulo!")
	private Lanche lanche;
}
