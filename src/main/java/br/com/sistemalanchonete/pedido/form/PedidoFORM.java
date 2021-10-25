package br.com.sistemalanchonete.pedido.form;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import br.com.sistemalanchonete.pedido.model.Pedido;
import br.com.sistemalanchonete.utils.enums.Resposta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoFORM {
	
	@NotNull(message = "O campo 'entrega' não pode ser nulo!")
	private Resposta entrega;

	private Integer idMesa;
	private Long idCliente;
	
	
	public Pedido converterParaPedido() {
		validarTipoPedido();
		Pedido.PedidoBuilder pedidoBuilder = Pedido.builder();
		 
		 pedidoBuilder
		 	.entrega(entrega)
		 	.dataHoraPedido(LocalDateTime.now());
		 
		 return pedidoBuilder.build();
	}
	
	private void validarTipoPedido() {
		if (entrega.equals(Resposta.NAO) && Objects.isNull(idMesa))
			throw new NullPointerException("O ID da Mesa não pode ser nulo!");
		
		if (entrega.equals(Resposta.SIM) && Objects.isNull(idCliente))
			throw new NullPointerException("O ID do Cliente não pode ser nulo!");
	}
}
