package br.com.sistemalanchonete.cliente.form;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.sistemalanchonete.cliente.model.Cliente;
import br.com.sistemalanchonete.endereco.form.EnderecoFORM;
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
	private String telefone;
	
	@Size(max = 15, message = "O campo 'telefoneRecado' deve ter no máximo {max} caracteres!")
	private String telefoneRecado;
	
	@Valid
	@NotNull(message = "As informações do 'endereco' não podem estar nulas!")
	private EnderecoFORM endereco;
	
	
	public Cliente converterParaCliente() {
		return Cliente.builder()
				.nome(nome)
				.telefone(telefone)
				.telefoneRecado(telefoneRecado)
				.endereco(endereco.converterParaEndereco())
				.dataCadastro(LocalDate.now())		
				.build();
	}
	
	public void alterarDadosCliente(Cliente cliente) {
		cliente.setNome(nome);
		cliente.setTelefone(telefone);
		cliente.setTelefoneRecado(telefoneRecado);
		cliente.setEndereco(endereco.converterParaEndereco());
	}
}
