package br.com.sistemalanchonete.despesa.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.sistemalanchonete.despesa.enums.TipoDespesa;
import br.com.sistemalanchonete.despesa.model.Despesa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DespesaFORM {

	@NotEmpty(message = "O campo 'descricao' não pode ser nulo/vazio!")
	@Size(max = 60, message = "O campo 'descricao' deve ter no máximo {caracteres}!")
	private String descricao;
	
	@NotNull(message = "O campo 'tipoDespesa' não pode ser nulo!")
	private TipoDespesa tipoDespesa;
	
	@NotNull(message = "O campo 'valor' não pode ser nulo!")
	@Digits(integer = 8, fraction = 2, message = "Formato inválido do campo 'valor'!")
	private BigDecimal valor;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataPagamento;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "O campo 'dataVencimento' não pode ser nulo!")
	private LocalDate dataVencimento;
	
	
	public Despesa converterParaDespesa() {
		return Despesa.builder()
				.descricao(descricao)
				.tipoDespesa(tipoDespesa)
				.valor(valor)
				.dataCadastro(LocalDate.now())
				.dataPagamento(dataPagamento)
				.dataVencimento(dataVencimento)
				.build();
	}
}
