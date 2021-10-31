package br.com.sistemalanchonete.seguranca.user.spring.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.sistemalanchonete.seguranca.usuario.model.Usuario;
import br.com.sistemalanchonete.seguranca.usuario.repository.UsuarioRepository;


@Service
public class UserDetailsServiceImplementacao implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
		
		if (usuarioOptional.isEmpty()) {
			throw new UsernameNotFoundException(email);
		}
		
		Usuario usuario = usuarioOptional.get();
		
		return new UserSpringSecurity(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getPerfis());
	}
}
