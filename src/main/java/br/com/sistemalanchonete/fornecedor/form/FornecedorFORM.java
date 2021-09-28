package br.com.sistemalanchonete.fornecedor.form;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.sistemalanchonete.fornecedor.model.Fornecedor;
import br.com.sistemalanchonete.utils.RegexUtils;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FornecedorFORM {

	@NotEmpty(message = "O campo 'nomeEmpresa' não deve ser nulo/vazio!")
	@Size(max = 60, message = "O campo 'nomeEmpresa' deve ter no máximo {max} caracteres!")
	private String nomeEmpresa;
	
	@NotEmpty(message = "O campo 'cnpj' não deve ser nulo/vazio!")
	@Size(max = 18, message = "O campo 'cnpj' deve ter no máximo {max} caracteres!")
	@Pattern(regexp = RegexUtils.CNPJ, message = "O formato do campo 'cnpj' está inválido!")
	private String cnpj;
	
	@NotEmpty(message = "O campo 'telefone' não deve ser nulo/vazio!")
	@Size(max = 15, message = "O campo 'telefone' deve ter no máximo {max} caracteres!")
	@Pattern(regexp = RegexUtils.TELEFONE, message = "O formato do campo 'telefone' está inválido!")
	private String telefone;

	@Size(max = 15, message = "O campo 'telefoneAlternativo' deve ter no máximo {max} caracteres!")
	private String telefoneAlternativo;
	
	
	public Fornecedor converterParaFornecedor() {
		validarTelefoneRecado();
		
		return Fornecedor.builder()
				.nomeEmpresa(nomeEmpresa)
				.cnpj(cnpj)
				.telefone(telefone)
				.telefoneAlternativo(telefoneAlternativo)
				.dataCadastro(LocalDate.now())
				.build();
	}
	
	public void alterarDadosFornecedor(Fornecedor fornecedor) {
		validarTelefoneRecado();
		
		fornecedor.setNomeEmpresa(nomeEmpresa);
		fornecedor.setCnpj(cnpj);
		fornecedor.setTelefone(telefone);
		fornecedor.setTelefoneAlternativo(telefoneAlternativo);
	}
	
	private void validarTelefoneRecado() {
		if (Objects.nonNull(telefoneAlternativo)) {
			if (!java.util.regex.Pattern.matches(RegexUtils.TELEFONE, telefoneAlternativo))
				throw new IllegalArgumentException("O formato do campo 'telefoneRecado' está inválido!");
		}
	}
}
