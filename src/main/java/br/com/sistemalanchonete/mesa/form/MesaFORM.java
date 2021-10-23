package br.com.sistemalanchonete.mesa.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.sistemalanchonete.mesa.model.Mesa;
import br.com.sistemalanchonete.utils.enums.Resposta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MesaFORM {

	@NotNull(message = "O campo 'numero' não pode ser nulo!")
	@Min(value = 1, message = "O campo 'numero' deve ter o valor mínimo de {value}!")
	private Integer numero;
	
	
	public Mesa converterParaMesa() {
		return Mesa.builder()
				.numero(numero)
				.disponivel(Resposta.SIM)
				.mesaAtiva(Resposta.SIM)
				.build();
	}
}
