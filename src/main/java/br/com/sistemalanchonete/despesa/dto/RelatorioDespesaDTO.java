package br.com.sistemalanchonete.despesa.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import br.com.sistemalanchonete.despesa.model.Despesa;
import br.com.sistemalanchonete.utils.ConversaoUtils;
import br.com.sistemalanchonete.utils.ConvertCollection;
import lombok.Getter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Getter
public class RelatorioDespesaDTO {

	private String descricao;
	private String tipoDespesa;
	private String valor;
	private String situacao;
	private String dataCadastro;
	private String dataPagamento;
	private String dataVencimento;
	
	
	public RelatorioDespesaDTO(Despesa despesa) {
		descricao = despesa.getDescricao();
		tipoDespesa = despesa.getTipoDespesa().getDescricao();
		valor = despesa.getValor().toString();
		
		if (Objects.nonNull(despesa.getDataPagamento())) situacao = "Paga";
		else if (Objects.isNull(despesa.getDataPagamento()) 
				&& despesa.getDataVencimento().isAfter(LocalDate.now())) situacao = "Pendente";
		else situacao = "Atrasada";
		
		dataCadastro = ConversaoUtils.converterLocalDateParaString(despesa.getDataCadastro());
		dataPagamento = Objects.nonNull(despesa.getDataPagamento()) ? ConversaoUtils.converterLocalDateParaString( despesa.getDataPagamento()) : "---";
		dataVencimento = ConversaoUtils.converterLocalDateParaString(despesa.getDataVencimento());
	}
	
	
	public static Map<String, Object> gerarMapComParametrosDoRelatorio(List<Despesa> despesas) {
		Map<String, Object> parametros = new HashMap<>();
		
		parametros.put("despesas", converter(despesas));
		parametros.put("dataHorarioGeracaoRelatorio", ConversaoUtils.converterLocalDateTimeParaFrontEndEmString(
				LocalDateTime.now()));
		
		return parametros;
	}
	
	public static JRBeanCollectionDataSource converter(List<Despesa> despesas) {
		Comparator<Despesa> comparator = Comparator.comparing(Despesa::getDataVencimento)
				.thenComparing(Despesa::getValor);
		
		List<RelatorioDespesaDTO> despesasParaRelatorio = ConvertCollection.convertToOrdenedList(despesas, 
				RelatorioDespesaDTO::new, comparator);
		
		return new JRBeanCollectionDataSource(despesasParaRelatorio, false);
	}
}
