package br.com.sistemalanchonete.mesa.form;

import javax.validation.constraints.NotNull;

import br.com.sistemalanchonete.mesa.model.Mesa;
import br.com.sistemalanchonete.utils.enums.Resposta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlteracaoMesaFORM {
	
	@NotNull(message = "O campo 'mesaAtiva' não pode ser nulo!")
	private Resposta mesaAtiva;
	
	
	public void atualizarDadosMesa(Mesa mesa) {
		mesa.setMesaAtiva(mesaAtiva);
	}
}
