package br.com.sistemalanchonete.seguranca.jwt;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.sistemalanchonete.seguranca.user.spring.security.UserSpringSecurity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration; 
	
	
	public String gerarToken(UserSpringSecurity usuario) {
		return Jwts.builder()
				.setSubject(usuario.getUsername())
				.claim("id", usuario.getId())			
				.claim("nome", usuario.getNome())
				.claim("permissoes", usuario.getAuthorities())
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}


	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		
		if (Objects.nonNull(claims)) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (Objects.nonNull(username) && Objects.nonNull(expirationDate) && now.before(expirationDate)) {
				return true;
			}
		}
		
		return false;
	}
	


	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		}
		catch (Exception e) {
			return null;
		}
	}


	public String getUsername(String token) {
		Claims claims = getClaims(token);
		
		return (Objects.nonNull(claims)) ? claims.getSubject() : null;
	}
}
