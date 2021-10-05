package br.com.sistemalanchonete.despesa.dto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import br.com.sistemalanchonete.despesa.model.Despesa;
import br.com.sistemalanchonete.utils.ConvertCollection;
import lombok.Getter;

@Getter
public class DespesaDTO {

	private Long id;
	private String descricao;
	private String tipoDespesa;
	private String valor;
	private String situacao;
	private LocalDate dataCadastro;
	private LocalDate dataPagamento;
	private LocalDate dataVencimento;
	
	
	public DespesaDTO(Despesa despesa) {
		id = despesa.getId();
		descricao = despesa.getDescricao();
		tipoDespesa = despesa.getTipoDespesa().getDescricao();
		valor = despesa.getValor().toString();
		
		if (Objects.nonNull(despesa.getDataPagamento())) situacao = "Paga";
		else if (Objects.isNull(despesa.getDataPagamento()) 
				&& despesa.getDataVencimento().isAfter(LocalDate.now())) situacao = "Pendente";
		else situacao = "Atrasada";
		
		dataCadastro = despesa.getDataCadastro();
		dataPagamento = despesa.getDataPagamento();
		dataVencimento = despesa.getDataVencimento();
	}
	
	
	public static List<DespesaDTO> converter(List<Despesa> despesas) {
		Comparator<Despesa> comparator = Comparator.comparing(Despesa::getDataVencimento)
				.thenComparing(Despesa::getValor);
		
		return ConvertCollection.convertToOrdenedList(despesas, DespesaDTO::new, comparator);
	}
}
