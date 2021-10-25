package br.com.sistemalanchonete.produto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemalanchonete.produto.enums.TipoProduto;
import br.com.sistemalanchonete.produto.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	List<Produto> findByIdIn(List<Long> idProdutos);

	List<Produto> findByTipoProduto(TipoProduto bebida);

}
