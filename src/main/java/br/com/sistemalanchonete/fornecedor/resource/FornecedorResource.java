package br.com.sistemalanchonete.fornecedor.resource;

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

import br.com.sistemalanchonete.fornecedor.dto.FornecedorDTO;
import br.com.sistemalanchonete.fornecedor.form.FornecedorFORM;
import br.com.sistemalanchonete.fornecedor.service.FornecedorService;

@RequestMapping(path = "/fornecedor")
@RestController
public class FornecedorResource {

	@Autowired
	private FornecedorService fornecedorService;
	
	
	@GetMapping
	public ResponseEntity<List<FornecedorDTO>> listarFornecedores() {
		return fornecedorService.listarFornecedores();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Void> cadastrarFornecedor(@RequestBody @Valid FornecedorFORM fornecedorFORM) {
		return fornecedorService.cadastrarFornecedor(fornecedorFORM);
	}
	
	@PutMapping(path = "/alterar/{id}")
	@Transactional
	public ResponseEntity<Void> alterarDadosFornecedor(@PathVariable Long id, 
			@RequestBody @Valid FornecedorFORM fornecedorFORM) {
		
		return fornecedorService.alterarDadosFornecedor(id, fornecedorFORM);
	}
	
	@DeleteMapping(path = "/excluir/{id}")
	@Transactional
	public ResponseEntity<Void> excluirFornecedor(@PathVariable Long id) {
		return fornecedorService.excluirFornecedor(id);
	}
}
