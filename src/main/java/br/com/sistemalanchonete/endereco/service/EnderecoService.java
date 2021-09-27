package br.com.sistemalanchonete.endereco.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.sistemalanchonete.endereco.dto.EnderecoDTO;
import br.com.sistemalanchonete.endereco.model.EnderecoAPIViaCEP;
import br.com.sistemalanchonete.exception.custom.ObjectNotFoundException;


@Service
public class EnderecoService {
	
	@Autowired
	private CEPService cepService;
	
	
	public ResponseEntity<EnderecoDTO> buscarEnderecoPeloCEP(String cep) {
		validarCEP(cep);
		EnderecoAPIViaCEP endereco = cepService.buscarEnderecoPorCEP(cep);

		if (Objects.nonNull(endereco.getErro()))
			throw new ObjectNotFoundException("CEP não encontrado!");
		
		return ResponseEntity.ok().body(new EnderecoDTO(endereco));
	}
	
	
	private void validarCEP(String cep) {
		if (Objects.isNull(cep))
			throw new NullPointerException("O CEP não pode ser nulo!");
		
		if (cep.length() != 8)
			throw new IllegalArgumentException("O formato do CEP é inválido!");
		
		try {
			Integer.parseInt(cep);
		}
		catch (NumberFormatException e) {
			throw new NumberFormatException("O CEP deve conter apenas números!");
		}
	}
}
