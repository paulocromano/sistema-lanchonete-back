package br.com.sistemalanchonete.pedido.dto;

import java.util.List;

import br.com.sistemalanchonete.cliente.dto.ClienteDTO;
import br.com.sistemalanchonete.lanche.dto.LancheDTO;
import br.com.sistemalanchonete.mesa.dto.MesaDTO;
import br.com.sistemalanchonete.produto.dto.ProdutoDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class InformacaoParaPedidoDTO {

	private List<MesaDTO> mesas;
	private List<ClienteDTO> clientes;
	private List<LancheDTO> lanches;
	private List<ProdutoDTO> bebidas;
}
