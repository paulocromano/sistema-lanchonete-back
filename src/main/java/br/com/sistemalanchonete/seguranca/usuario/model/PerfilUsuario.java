package br.com.sistemalanchonete.seguranca.usuario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;


@Entity
@Table(name = "perfil")
@Getter
public class PerfilUsuario {

	@Id
	@Column(name = "usuario_id")
	private Integer usuarioId;
	
	private Integer perfil;
}
