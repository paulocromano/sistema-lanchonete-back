package br.com.sistemalanchonete.seguranca.usuario.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.sistemalanchonete.seguranca.usuario.enums.Perfil;
import br.com.sistemalanchonete.utils.RegexUtils;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "usuario")
@NoArgsConstructor
@JsonIgnoreProperties(value = { "email", "senha", "perfis" })
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "O campo Nome não pode ser nulo/vazio!")
	@Size(max = 100, message = "O campo Nome deve ter no máximo {max} caracteres!")
	private String nome;
	
	@Column(unique = true)
	@NotEmpty(message = "O campo Email não pode ser nulo/vazio!")
	@Size(max = 50, message = "O campo Email deve ter no máximo {max} caracteres!")
	@Pattern(regexp = RegexUtils.EMAIL, message = "O formato de Email é inválido!")
	private String email;
	
	@NotEmpty(message = "O campo Senha não pode ser nulo/vazio!")
	@Size(max = 500, message = "O campo Senha deve ter no máximo {max} caracteres!")
	private String senha;
	
	@Column(name = "data_cadastro")
	@NotNull(message = "A Data do Cadastro não pode ser nula!")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;
	
	@CollectionTable(name = "perfil", catalog = "sistema_nutricionista_acesso")
	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name = "perfil")
	@NotNull(message = "O campo de Perfis não pode ser nulo!")
	private Set<Integer> perfis = new HashSet<>();
	
	
	public Usuario(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		dataCadastro = LocalDate.now();
		perfis.add(Perfil.FUNCIONARIO.ordinal());
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(perfil -> Perfil.converterParaEnum(perfil)).collect(Collectors.toSet());
	}
}
