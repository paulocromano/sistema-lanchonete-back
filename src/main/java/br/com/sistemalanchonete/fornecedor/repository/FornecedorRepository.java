package br.com.sistemalanchonete.fornecedor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemalanchonete.fornecedor.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

	Optional<Fornecedor> findByNomeEmpresaIgnoreCase(String nomeEmpresa);

}
