package br.com.sistemalanchonete.despesa.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.sistemalanchonete.despesa.dto.RelatorioDespesaDTO;
import br.com.sistemalanchonete.despesa.model.Despesa;
import br.com.sistemalanchonete.despesa.repository.DespesaRepository;
import br.com.sistemalanchonete.exception.custom.EmptyDataResultException;
import br.com.sistemalanchonete.utils.RelatorioUtils;

@Service
public class RelatorioDespesaService {

	@Autowired
	private DespesaRepository despesaRepository;
	
	
	public ResponseEntity<byte[]> gerarRelatorioTodasDespesas() {
		List<Despesa> despesas = despesaRepository.findAll();
		
		if (despesas.isEmpty())
			throw new EmptyDataResultException("Nenhuma Despesa cadastrada!");
		
		Map<String, Object> parametros = RelatorioDespesaDTO.gerarMapComParametrosDoRelatorio(despesas);
		parametros.put("titulo", "Relatório de Despesas");
		
		return RelatorioUtils.gerarRelatorioEmPDF("despesas", parametros);
	}
	
	public ResponseEntity<byte[]> gerarRelatorioDespesasPagas() {
		List<Despesa> despesas = despesaRepository.findAllByDataPagamentoNotNull();
		
		if (despesas.isEmpty())
			throw new EmptyDataResultException("Nenhuma Despesa paga!");
		
		Map<String, Object> parametros = RelatorioDespesaDTO.gerarMapComParametrosDoRelatorio(despesas);
		parametros.put("titulo", "Relatório das Despesas pagas");
		
		return RelatorioUtils.gerarRelatorioEmPDF("despesas", parametros);
	}
	
	public ResponseEntity<byte[]> gerarRelatorioDespesasVencidas() {
		List<Despesa> despesas = despesaRepository.findAllByDataPagamentoNullAndDataVencimentoLessThan(LocalDate.now());
		
		if (despesas.isEmpty())
			throw new EmptyDataResultException("Nenhuma Despesa vencida!");
		
		Map<String, Object> parametros = RelatorioDespesaDTO.gerarMapComParametrosDoRelatorio(despesas);
		parametros.put("titulo", "Relatório das Despesas Vencidas");
		
		return RelatorioUtils.gerarRelatorioEmPDF("despesas", parametros);
	}
}
