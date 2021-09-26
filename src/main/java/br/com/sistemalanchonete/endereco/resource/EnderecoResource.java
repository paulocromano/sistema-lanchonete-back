package br.com.sistemalanchonete.endereco.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemalanchonete.endereco.dto.EnderecoDTO;
import br.com.sistemalanchonete.endereco.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoResource {

	@Autowired
	private EnderecoService enderecoService;
	
	
	@GetMapping("/{idPaciente}")
	public ResponseEntity<EnderecoDTO> buscarEnderecoPaciente(@PathVariable Long idPaciente) {
		return enderecoService.buscarEnderecoPaciente(idPaciente);
	}
	
	
	@GetMapping("/api-via-cep/{cep}")
	public ResponseEntity<EnderecoDTO> buscarEnderecoPeloCEP(@PathVariable String cep) {
		return enderecoService.buscarEnderecoPeloCEP(cep);
	}
}
