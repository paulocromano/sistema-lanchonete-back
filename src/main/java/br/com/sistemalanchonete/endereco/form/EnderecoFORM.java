package br.com.sistemalanchonete.endereco.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.sistemalanchonete.endereco.model.Endereco;
import br.com.sistemalanchonete.utils.RegexUtils;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class EnderecoFORM {

	@NotEmpty(message = "O campo Logradouro não pode estar nulo/vazio!")
	@Size(max = 100, message = "O campo Endereço deve ter no máximo {max} caracteres!")
	private String logradouro;
	
	@NotEmpty(message = "O campo Número não pode estar nulo/vazio!")
	@Size(max = 5, message = "O campo Número deve ter no máximo {max} caracteres!")
	private String numero;
	
	@Size(max = 60, message = "O campo Complemento deve ter no máximo {max} caracteres!")
	private String complemento;
	
	@NotEmpty(message = "O campo Bairro não pode estar nulo/vazio!")
	@Size(max = 60, message = "O campo Bairro deve ter no máximo {max} caracteres!")
	private String bairro;
	
	@NotEmpty(message = "O campo Cidade não pode estar nulo/vazio!")
	@Size(max = 60, message = "O campo Cidade deve ter no máximo {max} caracteres!")
	private String cidade;
	
	@NotEmpty(message = "O campo CEP não pode estar nulo/vazio!")
	@Size(max = 9, message = "O campo CEP deve ter no máximo {max} caracteres!")
	@Pattern(regexp = RegexUtils.CEP, message = "O formato do CEP é inválido!")
	private String cep;
	
	@NotEmpty(message = "O campo UF não pode estar nulo/vazio!")
	@Size(max = 2, message = "O campo UF deve ter no máximo {max} caracteres!")
	private String uf;
	
	
	public Endereco converterParaEndereco() {
		return Endereco.builder()
				.logradouro(logradouro)
				.numero(numero)
				.complemento(complemento)
				.bairro(bairro)
				.cidade(cidade)
				.cep(cep)
				.uf(uf)
				.build();
	}
}
