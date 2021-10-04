package br.com.sistemalanchonete.fornecedor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistemalanchonete.fornecedor.repository.FornecedorRepository;

@Service
public class RelatorioFornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;
}
