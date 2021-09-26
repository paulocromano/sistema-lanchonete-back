package br.com.sistemalanchonete.cliente.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.sistemalanchonete.cliente.dto.ClienteDTO;
import br.com.sistemalanchonete.cliente.form.ClienteFORM;
import br.com.sistemalanchonete.cliente.model.Cliente;
import br.com.sistemalanchonete.cliente.repository.ClienteRepository;
import br.com.sistemalanchonete.exception.custom.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public ResponseEntity<List<ClienteDTO>> listarClientes() {
		List<Cliente> clientes = clienteRepository.findAll();
		
		return ResponseEntity.ok().body(ClienteDTO.converter(clientes));
	}
	
	public ResponseEntity<Void> cadastrarCliente(ClienteFORM cliente) {
		clienteRepository.save(cliente.converterParaCliente());
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	public ResponseEntity<Void> excluirCliente(Long id) {
		Cliente cliente = verificarSeClienteExiste(id);
		clienteRepository.delete(cliente);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	public Cliente verificarSeClienteExiste(Long id) {
		if (Objects.isNull(id))
			throw new NullPointerException("O ID do Cliente está nulo!");
		
		Optional<Cliente> cliente = clienteRepository.findById(id);

		if (cliente.isPresent())
			return cliente.get();
			
		throw new ObjectNotFoundException("Cliente não encontrado!")
;	}
}
