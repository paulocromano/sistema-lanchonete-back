package br.com.sistemalanchonete.lanche.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import br.com.sistemalanchonete.lanche.model.Lanche;
import br.com.sistemalanchonete.utils.ConvertCollection;
import lombok.Getter;

@Getter
public class LancheDTO {

	private Long id;
	private String nome;
	private String ingredientes;
	private BigDecimal preco;
	private String imagemBase64;
	private LocalDate dataCadastro;
	
	
	public LancheDTO(Lanche lanche) {
		id = lanche.getId();
		nome = lanche.getNome();
		ingredientes = lanche.getIngredientes();
		preco = lanche.getPreco();
		imagemBase64 = "data:image/png;base64," + lanche.getImagemBase64();
		dataCadastro = lanche.getDataCadastro();
	}
	
	
	public static List<LancheDTO> converter(List<Lanche> lanches) {
		Comparator<Lanche> comparator = (lanche, lanche2) -> lanche.getPreco().compareTo(lanche2.getPreco());
		
		return ConvertCollection.convertToOrdenedList(lanches, LancheDTO::new, comparator);
	}
}
