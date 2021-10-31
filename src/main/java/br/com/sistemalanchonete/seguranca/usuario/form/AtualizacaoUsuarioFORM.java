package br.com.sistemalanchonete.seguranca.usuario.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.sistemalanchonete.seguranca.usuario.model.Usuario;
import br.com.sistemalanchonete.utils.RegexUtils;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AtualizacaoUsuarioFORM {

	@NotEmpty(message = "O campo Nome não pode ser nulo/vazio!")
	@Size(max = 100, message = "O campo Nome deve ter no máximo {max} caracteres!")
	private String nome;
	
	@NotEmpty(message = "O campo Email não pode ser nulo/vazio!")
	@Size(max = 50, message = "O campo Email deve ter no máximo {max} caracteres!")
	@Pattern(regexp = RegexUtils.EMAIL, message = "O formato de Email é inválido!")
	private String email;
	
	
	public void atualizarInformacoesUsuario(Usuario usuario) {
		usuario.setNome(nome);
		usuario.setEmail(email);
	}
}
