package br.com.sistemalanchonete.cliente.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemalanchonete.cliente.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByDataCadastroBetween(LocalDate dataInicial,
			LocalDate dataFinal);

}
