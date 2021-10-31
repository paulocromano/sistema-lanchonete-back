package br.com.sistemalanchonete.seguranca.usuario.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.sistemalanchonete.seguranca.usuario.model.Usuario;
import br.com.sistemalanchonete.utils.RegexUtils;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UsuarioFORM {

	@NotEmpty(message = "O campo Nome não pode ser nulo/vazio!")
	@Size(max = 100, message = "O campo Nome deve ter no máximo {max} caracteres!")
	private String nome;
	
	@NotEmpty(message = "O campo Email não pode ser nulo/vazio!")
	@Size(max = 50, message = "O campo Email deve ter no máximo {max} caracteres!")
	@Pattern(regexp = RegexUtils.EMAIL, message = "O formato de Email é inválido!")
	private String email;
	
	@NotEmpty(message = "O campo Senha não pode ser nulo/vazio!")
	@Size(min = 6, max = 20, message = "O campo Senha deve ter entre {min} a {max} caracteres!")
	@Pattern(regexp = RegexUtils.SENHA, message = "O formato da Senha é inválido! "
			+ "A senha deve conter caracteres alfanuméricos, um caracter maiúsculo e um caracter especial!")
	private String senha;
	
	
	public Usuario converterParaUsuario(BCryptPasswordEncoder bCryptPasswordEncoder) {
		return new Usuario(nome, email, bCryptPasswordEncoder.encode(senha));
	}
}
