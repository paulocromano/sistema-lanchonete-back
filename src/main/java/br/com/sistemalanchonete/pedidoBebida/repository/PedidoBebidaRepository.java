package br.com.sistemalanchonete.pedidoBebida.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemalanchonete.pedidoBebida.model.PedidoBebida;

public interface PedidoBebidaRepository extends JpaRepository<PedidoBebida, Long> {

	Optional<PedidoBebida> findByIdAndPedido_id(Long idPedido, Long idPedidoBebida);

}
