package br.com.sistemalanchonete.pedido.form;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import br.com.sistemalanchonete.cliente.model.Cliente;
import br.com.sistemalanchonete.cliente.service.ClienteService;
import br.com.sistemalanchonete.mesa.model.Mesa;
import br.com.sistemalanchonete.mesa.service.MesaService;
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
	
	
	public Pedido converterParaPedido(MesaService mesaService, ClienteService clienteService) {
		validarTipoPedido();
		Mesa mesa = buscarMesa(mesaService);
		Cliente cliente = buscarCliente(clienteService);
		
		return Pedido.builder()
		 	.entrega(entrega)
		 	.mesa(mesa)
		 	.cliente(cliente)
		 	.dataHoraPedido(LocalDateTime.now())
		 	.build();

	}
	
	private void validarTipoPedido() {
		if (entrega.equals(Resposta.NAO) && Objects.isNull(idMesa))
			throw new NullPointerException("O ID da Mesa não pode ser nulo!");
		
		if (entrega.equals(Resposta.SIM) && Objects.isNull(idCliente))
			throw new NullPointerException("O ID do Cliente não pode ser nulo!");
	}
	
	private Mesa buscarMesa(MesaService mesaService) {
		if (Objects.nonNull(idMesa))
			return mesaService.verificarSeMesaExiste(idMesa);
		
		return null;
	}
	
	private Cliente buscarCliente(ClienteService clienteService) {
		if (Objects.nonNull(idCliente))
			return clienteService.verificarSeClienteExiste(idCliente);
		
		return null;
	}
}
