package br.com.sistemalanchonete.cliente.dto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import br.com.sistemalanchonete.cliente.model.Cliente;
import br.com.sistemalanchonete.utils.ConvertCollection;
import br.com.sistemalanchonete.utils.FormatacaoUtils;
import lombok.Getter;


@Getter
public class ClienteDTO {

	private Long id;
	private String nome;
	private String telefone;
	private String telefoneRecado;
	private LocalDate dataCadastro;
	
	
	public ClienteDTO(Cliente cliente) {
		id = cliente.getId();
		nome = cliente.getNome();
		telefone = cliente.getTelefone();
		telefoneRecado = cliente.getTelefoneRecado();
		dataCadastro = cliente.getDataCadastro();
	}
	
	
	public static List<ClienteDTO> converter(List<Cliente> clientes) {
		Comparator<Cliente> comparator = (cliente, cliente2) -> FormatacaoUtils.COLLATOR
				.compare(cliente.getNome(), cliente2.getNome());
		
		return ConvertCollection.convertToOrdenedList(clientes, ClienteDTO::new, comparator);
	}
}
