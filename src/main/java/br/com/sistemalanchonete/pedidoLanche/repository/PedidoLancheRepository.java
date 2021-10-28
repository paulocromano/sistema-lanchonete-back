package br.com.sistemalanchonete.pedidoLanche.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemalanchonete.pedidoLanche.model.PedidoLanche;

public interface PedidoLancheRepository extends JpaRepository<PedidoLanche, Long> {

	Optional<PedidoLanche> findByIdAndPedido_Id(Long idLanchePedido, Long idPedido);

}
