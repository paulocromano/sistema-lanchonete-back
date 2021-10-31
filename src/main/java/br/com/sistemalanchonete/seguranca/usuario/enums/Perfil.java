package br.com.sistemalanchonete.seguranca.usuario.enums;

import java.util.Objects;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Perfil {

	ADMIN("ROLE_ADMIN"),
	FUNCIONARIO("ROLE_FUNCIONARIO");
	
	
	private String descricao;
	
	
	public static Perfil converterParaEnum(Integer codigo) {
		if (Objects.isNull(codigo))
			throw new NullPointerException("O Código do Perfil não pode ser nulo!");
		
		for (Perfil perfil : Perfil.values()) 
			if (codigo.equals(perfil.ordinal()))
				return perfil;
		
		throw new IllegalArgumentException("O Código do Perfil é inválido!");
	}
}
