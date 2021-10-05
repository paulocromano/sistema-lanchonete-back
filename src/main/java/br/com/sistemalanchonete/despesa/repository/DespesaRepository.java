package br.com.sistemalanchonete.despesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemalanchonete.despesa.model.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

}
