package br.com.sistemalanchonete.despesa.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.sistemalanchonete.despesa.dto.DespesaDTO;
import br.com.sistemalanchonete.despesa.form.AlteracaoDespesaFORM;
import br.com.sistemalanchonete.despesa.form.DespesaFORM;
import br.com.sistemalanchonete.despesa.model.Despesa;
import br.com.sistemalanchonete.despesa.repository.DespesaRepository;
import br.com.sistemalanchonete.exception.custom.LanchoneteException;
import br.com.sistemalanchonete.exception.custom.ObjectNotFoundException;

@Service
public class DespesaService {

	@Autowired
	private DespesaRepository despesaRepository;
	
	
	public ResponseEntity<List<DespesaDTO>> listarDespesas() {
		List<Despesa> despesas = despesaRepository.findAll();
		
		return ResponseEntity.ok().body(DespesaDTO.converter(despesas));
	}
	
	public ResponseEntity<Void> cadastrarDespesa(DespesaFORM formularioDespesa) {
		despesaRepository.save(formularioDespesa.converterParaDespesa());
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	public ResponseEntity<Void> alterarDespesa(Long id, AlteracaoDespesaFORM alteracaoDespesaFORM) {
		Despesa despesa = verificarSeDespesaExiste(id);
		alteracaoDespesaFORM.atualizarDespesa(despesa);
		
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Void> confirmarPagamentoDespesa(Long id) {
		Despesa despesa = verificarSeDespesaExiste(id);
		
		if (Objects.nonNull(despesa.getDataPagamento()))
			throw new LanchoneteException("A Despesa já está paga!");
		
		despesa.setDataPagamento(LocalDate.now());
		
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Void> excluirDespesa(Long id) {
		Despesa despesa = verificarSeDespesaExiste(id);
		despesaRepository.delete(despesa);
		
		return ResponseEntity.noContent().build();
	}
	
	public Despesa verificarSeDespesaExiste(Long id) {
		if (Objects.isNull(id))
			throw new NullPointerException("O ID da Despesa não pode ser nulo!");
		
		Optional<Despesa> despesa = despesaRepository.findById(id);
		
		if (despesa.isEmpty())
			throw new ObjectNotFoundException("Despesa não encontrada!");
		
		return despesa.get();
	}
}
