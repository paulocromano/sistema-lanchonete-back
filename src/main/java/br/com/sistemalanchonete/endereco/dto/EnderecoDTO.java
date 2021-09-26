package br.com.sistemalanchonete.endereco.dto;

import br.com.sistemalanchonete.endereco.model.Endereco;
import br.com.sistemalanchonete.endereco.model.EnderecoAPIViaCEP;
import lombok.Getter;


@Getter
public class EnderecoDTO {

	private Long id;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String cep;
	private String uf;
	
	
	public EnderecoDTO(Endereco endereco) {
		id = endereco.getId();
		logradouro = endereco.getLogradouro();
		numero = endereco.getNumero();
		complemento = endereco.getComplemento();
		bairro = endereco.getBairro();
		cidade = endereco.getCidade();
		cep = endereco.getCep();
		uf = endereco.getUf();
	}
	
	
	public EnderecoDTO(EnderecoAPIViaCEP endereco) {
		logradouro = endereco.getLogradouro();
		complemento = endereco.getComplemento();
		bairro = endereco.getBairro();
		cidade = endereco.getLocalidade();
		cep = endereco.getCep();
		uf = endereco.getUf();
	}
}
