package br.com.sistemalanchonete.fornecedor.dto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import br.com.sistemalanchonete.fornecedor.model.Fornecedor;
import br.com.sistemalanchonete.utils.ConvertCollection;
import br.com.sistemalanchonete.utils.FormatacaoUtils;
import lombok.Getter;

@Getter
public class FornecedorDTO {

	private Long id;
	private String nomeEmpresa;
	private String cnpj;
	private String telefone;
	private String telefoneAlternativo;
	private LocalDate dataCadastro;
	
	
	public FornecedorDTO(Fornecedor fornecedor) {
		id = fornecedor.getId();
		nomeEmpresa = fornecedor.getNomeEmpresa();
		cnpj = fornecedor.getCnpj();
		telefone = fornecedor.getTelefone();
		telefoneAlternativo = fornecedor.getTelefoneAlternativo();
		dataCadastro = fornecedor.getDataCadastro();
	}
	
	public static List<FornecedorDTO> converter(List<Fornecedor> fornecedores) {
		Comparator<Fornecedor> comparator = (fornecedor, fornecedor2) -> FormatacaoUtils.COLLATOR
				.compare(fornecedor.getNomeEmpresa(), fornecedor2.getNomeEmpresa());
		
		return ConvertCollection.convertToOrdenedList(fornecedores, FornecedorDTO::new, comparator);
	}
}
