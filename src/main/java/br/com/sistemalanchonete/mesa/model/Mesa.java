package br.com.sistemalanchonete.mesa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.sistemalanchonete.utils.enums.Resposta;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mesa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Mesa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private Integer numero;
	
	@NotNull(message = "O campo 'disponivel' não pode ser nulo!")
	private Resposta disponivel;
	
	@Column(name = "ativa")
	@NotNull(message = "O campo 'mesaAtiva' não pode ser nulo!")
	private Resposta mesaAtiva;
}
