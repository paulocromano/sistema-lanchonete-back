package br.com.sistemalanchonete.fornecedor.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.sistemalanchonete.exception.custom.ObjectNotFoundException;
import br.com.sistemalanchonete.fornecedor.dto.FornecedorDTO;
import br.com.sistemalanchonete.fornecedor.form.FornecedorFORM;
import br.com.sistemalanchonete.fornecedor.model.Fornecedor;
import br.com.sistemalanchonete.fornecedor.repository.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	
	public ResponseEntity<List<FornecedorDTO>> listarFornecedores() {
		List<Fornecedor> fornecedores = fornecedorRepository.findAll();
		
		return ResponseEntity.ok().body(FornecedorDTO.converter(fornecedores));
	}
	
	public ResponseEntity<Void> cadastrarFornecedor(FornecedorFORM fornecedorFORM) {
		verificarSeExisteFornecedorComMesmoNomeEmpresa(fornecedorFORM.getNomeEmpresa());
		
		Fornecedor fornecedor = fornecedorFORM.converterParaFornecedor();
		fornecedorRepository.save(fornecedor);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	public ResponseEntity<Void> alterarDadosFornecedor(Long id, FornecedorFORM fornecedorFORM) {
		Fornecedor fornecedor = verificarSeExisteFornecedorComMesmoNomeEmpresa(id, fornecedorFORM.getNomeEmpresa());
		fornecedorFORM.alterarDadosFornecedor(fornecedor);
		
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Void> excluirFornecedor(Long id) {
		Fornecedor fornecedor = verificarSeFornecedorExiste(id);
		fornecedorRepository.delete(fornecedor);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	public void verificarSeExisteFornecedorComMesmoNomeEmpresa(String nomeEmpresa) {
		Optional<Fornecedor> fornecedor = fornecedorRepository.findByNomeEmpresaIgnoreCase(nomeEmpresa.trim());
		
		if (fornecedor.isPresent())
			throw new IllegalArgumentException("Nome de Empresa já cadastrado!");
	}
	
	public Fornecedor verificarSeExisteFornecedorComMesmoNomeEmpresa(Long id, String nomeEmpresa) {
		if (Objects.isNull(id))
			throw new NullPointerException("O ID do Fornecedor não pode ser nulo!");
		
		Optional<Fornecedor> fornecedor = fornecedorRepository.findByIdNotAndNomeEmpresaIgnoreCase(id, nomeEmpresa.trim());
		
		if (fornecedor.isPresent())
			throw new IllegalArgumentException("Nome de Empresa já cadastrado!");
		
		return fornecedor.get();
	}
	
	public Fornecedor verificarSeFornecedorExiste(Long id) {
		if (Objects.isNull(id))
			throw new NullPointerException("O ID do Fornecedor não pode ser nulo!");
		
		Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
		
		if (fornecedor.isPresent())
			return fornecedor.get();
		
		throw new ObjectNotFoundException("Fornecedor não encontrado!");
	}
}
