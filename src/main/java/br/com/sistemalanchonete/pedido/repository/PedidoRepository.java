package br.com.sistemalanchonete.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemalanchonete.pedido.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
