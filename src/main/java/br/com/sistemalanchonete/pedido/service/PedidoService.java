package br.com.sistemalanchonete.pedido.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.sistemalanchonete.cliente.service.ClienteService;
import br.com.sistemalanchonete.exception.custom.ObjectNotFoundException;
import br.com.sistemalanchonete.lanche.service.LancheService;
import br.com.sistemalanchonete.mesa.service.MesaService;
import br.com.sistemalanchonete.pedido.dto.InformacaoParaPedidoDTO;
import br.com.sistemalanchonete.pedido.dto.PedidoDTO;
import br.com.sistemalanchonete.pedido.form.PedidoFORM;
import br.com.sistemalanchonete.pedido.model.Pedido;
import br.com.sistemalanchonete.pedido.repository.PedidoRepository;
import br.com.sistemalanchonete.produto.service.ProdutoService;
import br.com.sistemalanchonete.utils.enums.Resposta;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private LancheService lancheService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private MesaService mesaService;
	
	@Autowired
	private ClienteService clienteService;
	
	
	public ResponseEntity<List<PedidoDTO>> buscarPedidos() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		return ResponseEntity.ok().body(PedidoDTO.converter(pedidos));		
	}
	
	public ResponseEntity<InformacaoParaPedidoDTO> buscarInformacaoParaPedido() {
		InformacaoParaPedidoDTO informacaoParaPedidoDTO = InformacaoParaPedidoDTO.builder()
			.mesas(mesaService.buscarMesasAtivas().getBody())
			.clientes(clienteService.listarClientes().getBody())
			.lanches(lancheService.listarLanches().getBody())
			.bebidas(produtoService.buscarBebidas().getBody())
			.build();
		
		return ResponseEntity.ok().body(informacaoParaPedidoDTO);
	}
	
	public ResponseEntity<Void> cadastrarPedido(PedidoFORM pedidoFORM) {
		Pedido pedido = pedidoFORM.converterParaPedido(mesaService, clienteService);
		pedidoRepository.save(pedido);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	public ResponseEntity<Void> definirPedidoComoEntregue(Long idPedido) {
		Pedido pedido = verificarSePedidoExiste(idPedido);
		verificarSePedidoFoiDefinidoParaEntrega(pedido);
		pedido.setDataHoraEntrega(LocalDateTime.now());
		
		return ResponseEntity.ok().build();
	}
	
	private void verificarSePedidoFoiDefinidoParaEntrega(Pedido pedido) {
		if (pedido.getEntrega().equals(Resposta.NAO))
			throw new IllegalArgumentException("O Pedido não foi definido para ser entregue!");
	}
	
	public ResponseEntity<Void> excluirPedido(Long id) {
		Pedido pedido = verificarSePedidoExiste(id);
		pedidoRepository.delete(pedido);
		
		return ResponseEntity.noContent().build();
	}
	
	public Pedido verificarSePedidoExiste(Long id) {
		if (Objects.isNull(id))
			throw new NullPointerException("O ID do Pedido não pode ser nulo!");
		
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		
		if (pedido.isEmpty())
			throw new ObjectNotFoundException("Pedido não encontrado!");
		
		return pedido.get();
	}
}
