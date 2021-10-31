package br.com.sistemalanchonete.seguranca.usuario.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemalanchonete.seguranca.usuario.dto.UsuarioDTO;
import br.com.sistemalanchonete.seguranca.usuario.form.AlteracaoSenhaFORM;
import br.com.sistemalanchonete.seguranca.usuario.form.AtualizacaoUsuarioFORM;
import br.com.sistemalanchonete.seguranca.usuario.form.UsuarioFORM;
import br.com.sistemalanchonete.seguranca.usuario.service.UsuarioService;


@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listarUsuariosEmOrdemAlfabetica() {
		return usuarioService.listarUsuariosEmOrdemAlfabetica();
	}
	
	
	@GetMapping("/informacoes-usuario-logado")
	public ResponseEntity<UsuarioDTO> buscarInformacoesUsuario(HttpServletRequest request) {
		return usuarioService.buscarInformacoesUsuario(request);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	@Transactional
	public ResponseEntity<Void> cadastrarUsuario(@RequestBody @Valid UsuarioFORM usuario) {
		return usuarioService.cadastrarUsuario(usuario);
	}
	
	
	@PutMapping
	@Transactional
	public ResponseEntity<Void> atualizarUsuario(HttpServletRequest request, @RequestBody @Valid AtualizacaoUsuarioFORM usuarioFORM) {
		return usuarioService.atualizarUsuario(request, usuarioFORM);
	}
	
	
	@PutMapping("/alterar-senha")
	@Transactional
	public ResponseEntity<Void> alterarSenha(HttpServletRequest request, @RequestBody @Valid AlteracaoSenhaFORM usuarioFORM) {
		return usuarioService.alterarSenha(request, usuarioFORM);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> removerUsuario(@PathVariable Integer id) {
		return usuarioService.removerUsuario(id);
	}
}
