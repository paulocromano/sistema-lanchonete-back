package br.com.sistemalanchonete.despesa.form;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.sistemalanchonete.despesa.model.Despesa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlteracaoDespesaFORM {

	@NotEmpty(message = "O campo 'descricao' não pode ser nulo/vazio!")
	@Size(max = 60, message = "O campo 'descricao' deve ter no máximo {caracteres}!")
	private String descricao;
	
	@NotNull(message = "O campo 'valor' não pode ser nulo!")
	@Digits(integer = 8, fraction = 2, message = "Formato inválido do campo 'valor'!")
	private BigDecimal valor;
	
	
	public void atualizarDespesa(Despesa despesa) {
		despesa.setDescricao(descricao);
		despesa.setValor(valor);
	}
}
