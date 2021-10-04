package br.com.sistemalanchonete.cliente.dto;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import br.com.sistemalanchonete.cliente.model.Cliente;
import br.com.sistemalanchonete.utils.ConversaoUtils;
import br.com.sistemalanchonete.utils.ConvertCollection;
import br.com.sistemalanchonete.utils.FormatacaoUtils;
import lombok.Getter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Getter
public class RelatorioClienteDTO {

	private String nome;
	private String telefone;
	private String telefoneRecado;
	private String dataCadastro;
	
	
	public RelatorioClienteDTO(Cliente cliente) {
		nome = cliente.getNome();
		telefone = cliente.getTelefone();
		telefoneRecado = Objects.nonNull(cliente.getTelefoneRecado()) ? cliente.getTelefoneRecado() : "---";
		dataCadastro = ConversaoUtils.converterLocalDateParaString(cliente.getDataCadastro());
	}
	
	public static Map<String, Object> gerarMapComParametrosDoRelatorio(List<Cliente> clientes) {
		Map<String, Object> parametros = new HashMap<>();
		
		parametros.put("parametrosLista", converter(clientes));
		parametros.put("dataHorarioGeracaoRelatorio", ConversaoUtils.converterLocalDateTimeParaFrontEndEmString(
				LocalDateTime.now()));
		
		return parametros;
	}
	
	
	private static JRBeanCollectionDataSource converter(List<Cliente> clientes) {
		Comparator<Cliente> comparator = (cliente, cliente2) -> FormatacaoUtils.COLLATOR
				.compare(cliente.getNome(), cliente2.getNome());
		
		List<RelatorioClienteDTO> clientesParaRelatorio = ConvertCollection.convertToOrdenedList(
				clientes, RelatorioClienteDTO::new, comparator);
		
		return new JRBeanCollectionDataSource(clientesParaRelatorio, false);
	}
}
