package br.com.sistemalanchonete.fornecedor.dto;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sistemalanchonete.fornecedor.model.Fornecedor;
import br.com.sistemalanchonete.utils.ConversaoUtils;
import br.com.sistemalanchonete.utils.ConvertCollection;
import br.com.sistemalanchonete.utils.FormatacaoUtils;
import lombok.Getter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Getter
public class RelatorioFornecedorDTO {

	private String nomeEmpresa;
	private String cnpj;
	private String telefone;
	private String telefoneAlternativo;
	private String dataCadastro;
	
	
	public RelatorioFornecedorDTO(Fornecedor fornecedor) {
		nomeEmpresa = fornecedor.getNomeEmpresa();
		cnpj = fornecedor.getCnpj();
		telefone = fornecedor.getTelefone();
		telefoneAlternativo = fornecedor.getTelefoneAlternativo();
		dataCadastro = ConversaoUtils.converterLocalDateParaString(fornecedor.getDataCadastro());
	}
	
	public static Map<String, Object> gerarMapComParametrosDoRelatorio(List<Fornecedor> fornecedores) {
		Map<String, Object> parametros = new HashMap<>();
		
		parametros.put("parametrosLista", converter(fornecedores));
		parametros.put("dataHorarioGeracaoRelatorio", ConversaoUtils.converterLocalDateTimeParaFrontEndEmString(
				LocalDateTime.now()));
		
		return parametros;
	}
	
	
	private static JRBeanCollectionDataSource converter(List<Fornecedor> fornecedores) {
		Comparator<Fornecedor> comparator = (fornecedor, fornecedor2) -> FormatacaoUtils.COLLATOR
				.compare(fornecedor.getNomeEmpresa(), fornecedor2.getNomeEmpresa());
		
		List<RelatorioFornecedorDTO> fornecedoresParaRelatorio = ConvertCollection.convertToOrdenedList(
				fornecedores, RelatorioFornecedorDTO::new, comparator);
		
		return new JRBeanCollectionDataSource(fornecedoresParaRelatorio, false);
	}
}
