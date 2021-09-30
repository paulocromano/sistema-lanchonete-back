package br.com.sistemalanchonete.lanche.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.sistemalanchonete.lanche.model.Lanche;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LancheFORM {

	@NotEmpty(message = "O campo 'nome' não pode ser nulo/vazio!")
	@Size(max = 60, message = "O campo 'nome' deve ter no máximo {max} caracteres!")
	private String nome;
	
	@NotEmpty(message = "O campo 'ingredientes' não pode ser nulo/vazio!")
	@Size(max = 200, message = "O campo 'ingredientes' deve ter no máximo {max} caracteres!")
	private String ingredientes;
	
	@NotNull(message = "O campo 'preco' não pode ser nulo!")
	@Digits(integer = 6, fraction = 2, message = "Formato do campo 'preco' é inválido!")
	@Min(value = 1, message = "O preço mínimo de um lanche deve ser igual ou mais que {value}!")
	private BigDecimal preco;
	
	@NotEmpty(message = "O campo 'imagem' não pode ser nulo/vazio!")
	@Size(max = 8000, message = "O campo 'imagem' deve ter no máximo {max} caracteres!")
	private String imagemBase64;
	
	
	public Lanche converterParaLanche() {
		return Lanche.builder()
				.nome(nome)
				.ingredientes(ingredientes)
				.preco(preco)
				.imagemBase64(imagemBase64)
				.dataCadastro(LocalDate.now())
				.build();
	}
	
	public void atualizarDadosLanche(Lanche lanche) {
		lanche.setNome(nome);
		lanche.setIngredientes(ingredientes);
		lanche.setPreco(preco);
		lanche.setImagemBase64(imagemBase64);
	}
}
