package br.com.sistemalanchonete.lanche.resource;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.sistemalanchonete.lanche.dto.LancheDTO;
import br.com.sistemalanchonete.lanche.form.LancheFORM;
import br.com.sistemalanchonete.lanche.service.LancheService;

@RequestMapping(path = "/lanche")
@RestController
public class LancheResource {

	@Autowired
	private LancheService lancheService;
	
	
	@GetMapping
	public ResponseEntity<List<LancheDTO>> listarLanches() {
		return lancheService.listarLanches();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Void> cadastrarLanche(@RequestBody @Valid LancheFORM lancheFORM) {
		return lancheService.cadastrarLanche(lancheFORM);
	}
	
	@PutMapping(path = "/alterar/{id}")
	@Transactional
	public ResponseEntity<Void> alterarDadosLanche(@PathVariable Long id, 
			@RequestBody @Valid LancheFORM lancheFORM) {
		
		return lancheService.alterarDadosLanche(id, lancheFORM);
	}
	
	@PostMapping(path = "/encode-imagem-lanche")
	public ResponseEntity<String> encodeImagemLancheParaBase64(@RequestParam MultipartFile imagem) {
		return lancheService.encodeImagemLancheParaBase64(imagem);
	}
	
	@DeleteMapping(path = "/excluir/{id}")
	@Transactional
	public ResponseEntity<Void> excluirLanche(@PathVariable Long id) {
		return lancheService.excluirLanche(id);
	}
}
