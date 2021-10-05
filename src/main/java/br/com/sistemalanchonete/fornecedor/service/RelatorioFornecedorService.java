package br.com.sistemalanchonete.fornecedor.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.sistemalanchonete.fornecedor.dto.RelatorioFornecedorDTO;
import br.com.sistemalanchonete.fornecedor.model.Fornecedor;
import br.com.sistemalanchonete.fornecedor.repository.FornecedorRepository;
import br.com.sistemalanchonete.utils.RelatorioUtils;

@Service
public class RelatorioFornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	
	public ResponseEntity<byte[]> gerarRelatorioDosFornecedores() {
		List<Fornecedor> fornecedores = fornecedorRepository.findAll();
		
		Map<String, Object> parametros = RelatorioFornecedorDTO.gerarMapComParametrosDoRelatorio(fornecedores);
		parametros.put("titulo", "Relatório de Fornecedores");
		
		return RelatorioUtils.gerarRelatorioEmPDF("fornecedores", parametros);
	}
}
