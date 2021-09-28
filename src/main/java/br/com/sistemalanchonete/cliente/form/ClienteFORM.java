package br.com.sistemalanchonete.cliente.form;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.sistemalanchonete.cliente.model.Cliente;
import br.com.sistemalanchonete.endereco.form.EnderecoFORM;
import br.com.sistemalanchonete.utils.RegexUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteFORM {

	@NotEmpty(message = "O campo 'nome' não pode ser nulo/vazio!")
	@Size(max = 100, message = "O campo 'nome' deve ter no máximo {max} caracteres!")
	private String nome;
	
	@NotEmpty(message = "O campo 'telefone' não deve ser nulo/vazio!")
	@Size(max = 15, message = "O campo 'telefone' deve ter no máximo {max} caracteres!")
	@Pattern(regexp = RegexUtils.TELEFONE, message = "O formato do campo 'telefone' está inválido!")
	private String telefone;
	
	@Size(max = 15, message = "O campo 'telefoneRecado' deve ter no máximo {max} caracteres!")
	private String telefoneRecado;
	
	@Valid
	@NotNull(message = "As informações do 'endereco' não podem estar nulas!")
	private EnderecoFORM endereco;
	
	
	public Cliente converterParaCliente() {
		validarTelefoneRecado();
		
		return Cliente.builder()
				.nome(nome)
				.telefone(telefone)
				.telefoneRecado(telefoneRecado)
				.endereco(endereco.converterParaEndereco())
				.dataCadastro(LocalDate.now())		
				.build();
	}
	
	public void alterarDadosCliente(Cliente cliente) {
		validarTelefoneRecado();
		
		cliente.setNome(nome);
		cliente.setTelefone(telefone);
		cliente.setTelefoneRecado(telefoneRecado);
		cliente.setEndereco(endereco.converterParaEndereco());
	}
	
	private void validarTelefoneRecado() {
		if (Objects.nonNull(telefoneRecado)) {
			if (!java.util.regex.Pattern.matches(RegexUtils.TELEFONE, telefoneRecado))
				throw new IllegalArgumentException("O formato do campo 'telefoneRecado' está inválido!");
		}
	}
}
