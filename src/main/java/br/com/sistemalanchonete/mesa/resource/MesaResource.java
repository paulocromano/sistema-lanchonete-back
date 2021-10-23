package br.com.sistemalanchonete.mesa.resource;

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

import br.com.sistemalanchonete.mesa.dto.MesaDTO;
import br.com.sistemalanchonete.mesa.form.AlteracaoMesaFORM;
import br.com.sistemalanchonete.mesa.form.MesaFORM;
import br.com.sistemalanchonete.mesa.service.MesaService;

@RequestMapping(path = "/mesa")
@RestController
public class MesaResource {

	@Autowired
	private MesaService mesaService;
	
	
	@GetMapping
	public ResponseEntity<List<MesaDTO>> buscarMesas() {
		return mesaService.buscarMesas();
	}
	
	@GetMapping(path = "/disponivel")
	public ResponseEntity<List<MesaDTO>> buscarMesasDisponiveis() {
		return mesaService.buscarMesasDisponiveis();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Void> cadastrarMesa(@RequestBody @Valid MesaFORM mesaFORM) {
		return mesaService.cadastrarMesa(mesaFORM);
	}
	
	@PutMapping(path = "/alterar/{id}")
	@Transactional
	public ResponseEntity<Void> alterarDadosMesa(@PathVariable Integer id, 
			@RequestBody @Valid AlteracaoMesaFORM alteracaoMesaFORM) {
		
		return mesaService.alterarDadosMesa(id, alteracaoMesaFORM);
	}
	
	@DeleteMapping(path = "/excluir/{id}")
	@Transactional
	public ResponseEntity<Void> excluirMesa(@PathVariable Integer id) {
		return mesaService.excluirMesa(id);
	}
}
