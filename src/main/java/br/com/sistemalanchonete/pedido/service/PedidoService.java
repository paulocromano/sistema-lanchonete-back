package br.com.sistemalanchonete.pedido.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.sistemalanchonete.cliente.service.ClienteService;
import br.com.sistemalanchonete.exception.custom.ObjectNotFoundException;
import br.com.sistemalanchonete.lanche.model.Lanche;
import br.com.sistemalanchonete.lanche.service.LancheService;
import br.com.sistemalanchonete.mesa.service.MesaService;
import br.com.sistemalanchonete.pedido.dto.InformacaoParaPedidoDTO;
import br.com.sistemalanchonete.pedido.dto.PedidoDTO;
import br.com.sistemalanchonete.pedido.form.PedidoFORM;
import br.com.sistemalanchonete.pedido.form.PedidoLancheFORM;
import br.com.sistemalanchonete.pedido.form.PedidoBebidaFORM;
import br.com.sistemalanchonete.pedido.model.Pedido;
import br.com.sistemalanchonete.pedido.repository.PedidoRepository;
import br.com.sistemalanchonete.produto.enums.TipoProduto;
import br.com.sistemalanchonete.produto.model.Produto;
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
	
	public ResponseEntity<Void> adicionarLancheAoPedido(Long idPedido, PedidoLancheFORM pedidoLancheFORM) {
		Pedido pedido = verificarSePedidoExiste(idPedido);
		Lanche lanche = lancheService.verificarSeLancheExiste(pedidoLancheFORM.getIdLanche());
		pedidoLancheFORM.atualizarLanchesDoPedido(pedido, lanche);
		
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Void> adicionarBebidaAoPedido(Long idPedido, PedidoBebidaFORM pedidoProdutoFORM) {
		Pedido pedido = verificarSePedidoExiste(idPedido);
		Produto produto = produtoService.verificarSeProdutoExiste(pedidoProdutoFORM.getIdProduto());
		verificarSeTipoProdutoIgualBebida(produto);
		pedidoProdutoFORM.atualizarBebidasDoPedido(pedido, produto);
		
		return ResponseEntity.ok().build();
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
	
	public ResponseEntity<Void> excluirLancheDoPedido(Long idPedido, Long idLanche) {
		Pedido pedido = verificarSePedidoExiste(idPedido);
		Lanche lanche = verificarSePedidoContemLanche(pedido.getLanches(), idLanche);
		pedido.setLanches(removerLancheDaListaDeLancheDoPedido(pedido.getLanches(), idLanche));
		pedido.setPrecoTotal(pedido.getPrecoTotal().subtract(lanche.getPreco()));
		
		return ResponseEntity.ok().build();
	}
	
	private Lanche verificarSePedidoContemLanche(List<Lanche> lanches, Long idLanche) {
		Optional<Lanche> lancheOptional = lanches.stream().filter(lanche -> lanche.getId().equals(idLanche)).findFirst();
		
		if (lancheOptional.isEmpty())
			throw new ObjectNotFoundException("O Pedido não possui o lanche informado!");
		
		return lancheOptional.get();	
	}
	
	private List<Lanche> removerLancheDaListaDeLancheDoPedido(List<Lanche> lanches, Long idLanche) {
		return lanches.stream().filter(lanche -> !lanche.getId().equals(idLanche)).collect(Collectors.toList());
	}
	
	public ResponseEntity<Void> excluirBebidaDoPedido(Long idPedido, Long idProduto) {
		Pedido pedido = verificarSePedidoExiste(idPedido);
		Produto produto = verificarSePedidoContemBebida(pedido.getBebidas(), idProduto);
		pedido.setBebidas(removerProdutoDaListaDeProdutoDoPedido(pedido.getBebidas(), idProduto));
		pedido.setPrecoTotal(pedido.getPrecoTotal().subtract(produto.getPreco()));;
		
		return ResponseEntity.ok().build();
	}
	
	private Produto verificarSePedidoContemBebida(List<Produto> produtos, Long idProduto) {
		Optional<Produto> produtoOptional = produtos.stream().filter(produto -> produto.getId().equals(idProduto)).findFirst();
		
		if (produtoOptional.isEmpty())
			throw new ObjectNotFoundException("O Pedido não possui o produto informado!");
		
		return produtoOptional.get();
	}
	
	private List<Produto> removerProdutoDaListaDeProdutoDoPedido(List<Produto> produtos, Long idProduto) {
		return produtos.stream().filter(produto -> !produto.getId().equals(idProduto)).collect(Collectors.toList());
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
	
	public void verificarSeTipoProdutoIgualBebida(Produto produto) {
		if (!produto.getTipoProduto().equals(TipoProduto.BEBIDA)) 
			throw new IllegalArgumentException("Tipo de Produto inválido para Pedido!");
	}
}
