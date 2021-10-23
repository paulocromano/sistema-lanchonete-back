package br.com.sistemalanchonete.pedido.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemalanchonete.mesa.model.Mesa;
import br.com.sistemalanchonete.pedido.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	Optional<Pedido> findFirstByMesa(Mesa mesa);

}
