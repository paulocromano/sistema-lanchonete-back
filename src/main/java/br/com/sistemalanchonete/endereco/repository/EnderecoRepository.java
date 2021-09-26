package br.com.sistemalanchonete.endereco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemalanchonete.endereco.model.Endereco;


public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
