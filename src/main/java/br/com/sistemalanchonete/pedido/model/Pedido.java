package br.com.sistemalanchonete.pedido.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.sistemalanchonete.cliente.model.Cliente;
import br.com.sistemalanchonete.mesa.model.Mesa;
import br.com.sistemalanchonete.pedidoBebida.model.PedidoBebida;
import br.com.sistemalanchonete.pedidoLanche.model.PedidoLanche;
import br.com.sistemalanchonete.utils.enums.Resposta;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@JsonIgnoreProperties(value = { "cliente" })
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 200, message = "O campo 'observacoes' deve ter no máximo {max} caracteres!")
	private String observacoes;
	
	@NotNull(message = "O campo 'entrega' não pode ser nulo!")
	private Resposta entrega;
	
	@Column(name = "data_hora_pedido")
	@NotNull(message = "O campo 'dataHoraPedido' não pode ser nulo!")
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	private LocalDateTime dataHoraPedido;
	
	@Column(name = "data_hora_entrega")
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	private LocalDateTime dataHoraEntrega;
	
	@Column(name = "preco_total")
	@Digits(integer = 6, fraction = 2, message = "O formato do campo 'precoTotal' é inválido")
	private BigDecimal precoTotal;
	
	@Column(name = "pedido_finalizado")
	@NotNull(message = "O campo 'pedidoFinalizado' não pode ser nulo!")
	private Resposta pedidoFinalizado;
	
	@JoinColumn(name = "mesa_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Mesa mesa;
	
	@JoinColumn(name = "cliente_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PedidoLanche> lanches;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PedidoBebida> bebidas;
}
