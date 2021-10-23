package br.com.sistemalanchonete.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemalanchonete.produto.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
