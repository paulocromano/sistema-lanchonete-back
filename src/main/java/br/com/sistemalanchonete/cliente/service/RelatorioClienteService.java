package br.com.sistemalanchonete.cliente.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.sistemalanchonete.cliente.dto.RelatorioClienteDTO;
import br.com.sistemalanchonete.cliente.model.Cliente;
import br.com.sistemalanchonete.cliente.repository.ClienteRepository;
import br.com.sistemalanchonete.exception.custom.EmptyDataResultException;
import br.com.sistemalanchonete.utils.ConversaoUtils;
import br.com.sistemalanchonete.utils.RelatorioUtils;

@Service
public class RelatorioClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public ResponseEntity<byte[]> gerarRelatorioComTodosClientes() {
		List<Cliente> clientes = clienteRepository.findAll();
		
		if (clientes.isEmpty())
			throw new EmptyDataResultException("Nenhum cliente cadastrado!");
		
		Map<String, Object> parametros = RelatorioClienteDTO.gerarMapComParametrosDoRelatorio(clientes);
		parametros.put("titulo", "Relatório de Clientes");
		
		return RelatorioUtils.gerarRelatorioEmPDF("clientes", parametros);
	}
	
	public ResponseEntity<byte[]> gerarRelatorioDeClientesCadastradosEntrePeriodos(String dataInicial, String dataFinal) {
		LocalDate dataIniicialLocalDate = ConversaoUtils.converterStringParaLocalDate(dataInicial);
		LocalDate dataFinalLocalDate = ConversaoUtils.converterStringParaLocalDate(dataFinal);
		
		validarDatasRelatorio(dataIniicialLocalDate, dataFinalLocalDate);
		List<Cliente> clientes = clienteRepository.findByDataCadastroBetween(dataIniicialLocalDate, dataFinalLocalDate);
		
		if (clientes.isEmpty())
			throw new EmptyDataResultException("Nenhum cliente cadastrado!");
		
		Map<String, Object> parametros = RelatorioClienteDTO.gerarMapComParametrosDoRelatorio(clientes);

		String titulo = "Relatório de Clientes - " + (dataIniicialLocalDate.isEqual(dataFinalLocalDate) 
				? dataInicial : dataInicial + " à " + dataFinal);
		
		parametros.put("titulo", titulo);
		
		return RelatorioUtils.gerarRelatorioEmPDF("clientes", parametros);
	}
	
	private void validarDatasRelatorio(LocalDate dataInicial, LocalDate dataFinal) {
		if (Objects.isNull(dataInicial) || Objects.isNull(dataFinal))
			throw new NullPointerException("A data inicial e/ou a data final não podem ser nulas!");
		
		if (dataInicial.isAfter(dataFinal))
			throw new IllegalArgumentException("A data inicial não pode ser posterior à data final!");
		
		if (dataInicial.isAfter(LocalDate.now()))
			throw new IllegalArgumentException("A data inicial não pode ser posterior a data atual!");
	}
}
