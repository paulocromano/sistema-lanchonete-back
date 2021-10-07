package br.com.sistemalanchonete.despesa.resource;

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

import br.com.sistemalanchonete.despesa.dto.DespesaDTO;
import br.com.sistemalanchonete.despesa.dto.InformacoesCadastroDespesaDTO;
import br.com.sistemalanchonete.despesa.form.AlteracaoDespesaFORM;
import br.com.sistemalanchonete.despesa.form.DespesaFORM;
import br.com.sistemalanchonete.despesa.service.DespesaService;

@RequestMapping(path = "/despesa")
@RestController
public class DespesaResource {

	@Autowired
	private DespesaService despesaService;
	
	
	@GetMapping
	public ResponseEntity<List<DespesaDTO>> listarDespesas() {
		return despesaService.listarDespesas();
	}
	
	@GetMapping(path = "/informacoes-para-cadastro")
	public ResponseEntity<InformacoesCadastroDespesaDTO> buscarInformacoesParaCadastroDespesa() {
		return despesaService.buscarInformacoesParaCadastroDespesa();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Void> cadastrarDespesa(@RequestBody @Valid DespesaFORM formularioDespesa) {
		return despesaService.cadastrarDespesa(formularioDespesa);
	}
	
	@PutMapping(path = "/alterar/{id}")
	@Transactional
	public ResponseEntity<Void> alterarDespesa(@PathVariable Long id, 
			@RequestBody @Valid AlteracaoDespesaFORM alteracaoDespesaFORM) {
		
		return despesaService.alterarDespesa(id, alteracaoDespesaFORM);
	}
	
	@PutMapping(path = "/confirmar-pagamento/{id}")
	@Transactional
	public ResponseEntity<Void> confirmarPagamentoDespesa(@PathVariable Long id) {
		return despesaService.confirmarPagamentoDespesa(id);
	}
	
	@DeleteMapping(path = "/excluir/{id}")
	@Transactional
	public ResponseEntity<Void> excluirDespesa(@PathVariable Long id) {
		return despesaService.excluirDespesa(id);
	}
}
