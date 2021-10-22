package br.com.sistemalanchonete.mesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemalanchonete.mesa.model.Mesa;

public interface MesaRepository extends JpaRepository<Mesa, Integer> {

}
