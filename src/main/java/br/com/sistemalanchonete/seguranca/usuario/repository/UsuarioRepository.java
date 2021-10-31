package br.com.sistemalanchonete.seguranca.usuario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemalanchonete.seguranca.usuario.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByEmail(String email);
	
	List<Usuario> findAllByPerfisContains(Integer codigoPerfil);
}
