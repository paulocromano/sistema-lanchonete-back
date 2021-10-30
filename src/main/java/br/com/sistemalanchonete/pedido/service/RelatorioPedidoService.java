package br.com.sistemalanchonete.pedido.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.sistemalanchonete.exception.custom.EmptyDataResultException;
import br.com.sistemalanchonete.pedido.dto.RelatorioPedidoDTO;
import br.com.sistemalanchonete.pedido.model.Pedido;
import br.com.sistemalanchonete.pedido.repository.PedidoRepository;
import br.com.sistemalanchonete.utils.RelatorioUtils;

@Service
public class RelatorioPedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	public ResponseEntity<byte[]> gerarRelatorioComTodosPedidos() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		
		if (pedidos.isEmpty())
			throw new EmptyDataResultException("Nenhum pedido cadastrado!");
		
		Map<String, Object> parametros = RelatorioPedidoDTO.gerarMapComParametrosDoRelatorio(pedidos);
		parametros.put("titulo", "Relatório de Pedidos");
		
		return RelatorioUtils.gerarRelatorioEmPDF("pedidos", parametros);
	}
}
