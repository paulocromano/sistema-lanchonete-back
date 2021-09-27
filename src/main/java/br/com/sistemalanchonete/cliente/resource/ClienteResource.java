package br.com.sistemalanchonete.cliente.resource;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemalanchonete.cliente.dto.ClienteDTO;
import br.com.sistemalanchonete.cliente.form.ClienteFORM;
import br.com.sistemalanchonete.cliente.service.ClienteService;

@RestController
@RequestMapping(path = "/cliente")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;
	
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> listarClientes() {
		return clienteService.listarClientes();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Void> cadastrarCliente(@RequestBody @Valid ClienteFORM cliente) {
		return clienteService.cadastrarCliente(cliente);
	}
	
	@RequestMapping(path = "/alterar/{id}")
	@PutMapping
	@Transactional
	public ResponseEntity<Void> alterarDadosCliente(@PathVariable Long id, 
			@RequestBody @Valid ClienteFORM clienteFORM) {
		
		return clienteService.alterarDadosCliente(id, clienteFORM);
	}
	
	@RequestMapping(path = "/excluir/{id}")
	@DeleteMapping
	@Transactional
	public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
		return clienteService.excluirCliente(id);
	}
}
