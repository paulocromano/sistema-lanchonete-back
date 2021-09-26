package br.com.sistemalanchonete.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemalanchonete.cliente.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
