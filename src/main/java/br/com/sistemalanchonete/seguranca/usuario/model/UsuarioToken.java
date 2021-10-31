package br.com.sistemalanchonete.seguranca.usuario.model;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sistemalanchonete.seguranca.usuario.enums.Perfil;
import lombok.Getter;


@Getter
public class UsuarioToken {
	
	private Integer id;
	private String nome;
	private String email;
	private List<String> permissoes;


	@SuppressWarnings("unchecked")
	public UsuarioToken(HttpServletRequest request) {
		try {
			String token = request.getHeader("Authorization");
			token = token.split(" ")[1];
			
			Decoder decoder = Base64.getUrlDecoder();
			String[] tokenCodificado = token.split("\\.");
			
			String payloadJson = new String(decoder.decode(tokenCodificado[1]));

			HashMap<String, Object> payload = new ObjectMapper().readValue(payloadJson, HashMap.class);

			id = (Integer) payload.get("id");
			nome = (String) payload.get("nome");
			email = (String) payload.get("email");
			permissoes = (List<String>) payload.get("permissoes");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean temPermissao(Perfil perfil) {
		return permissoes.contains(perfil.getDescricao());
	}
}
