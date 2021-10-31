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
public class AlteracaoSenhaFORM {

	@NotEmpty(message = "O campo Senha não pode ser nulo/vazio!")
	@Size(min = 6, max = 20, message = "O campo Senha deve ter entre {min} a {max} caracteres!")
	@Pattern(regexp = RegexUtils.SENHA, message = "O formato da Senha é inválido! "
			+ "A senha deve conter caracteres alfanuméricos, um caracter maiúsculo e um caracter especial!")
	private String senha;
	
	
	public void atualizarSenha(Usuario usuario, BCryptPasswordEncoder bCryptPasswordEncoder) {
		usuario.setSenha(bCryptPasswordEncoder.encode(senha));
	}
}
