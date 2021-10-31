package br.com.sistemalanchonete.seguranca.usuario.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import br.com.sistemalanchonete.exception.custom.ForbiddenException;
import br.com.sistemalanchonete.seguranca.usuario.enums.Perfil;
import br.com.sistemalanchonete.seguranca.usuario.model.UsuarioToken;


@Service
public class UsuarioTokenService {

	
	public void usuarioTemPermissaoDeAdmin(HttpServletRequest request) {
		UsuarioToken usuarioToken = new UsuarioToken(request);
		
		if (!usuarioToken.temPermissao(Perfil.ADMIN))
			throw new ForbiddenException("Usuário não tem permissão para fazer a requisição!");
	}
}
