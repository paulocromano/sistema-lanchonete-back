package br.com.sistemalanchonete.despesa.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemalanchonete.despesa.model.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

	List<Despesa> findAllByDataPagamentoNotNull();

	List<Despesa> findAllByDataPagamentoNullAndDataVencimentoLessThan(LocalDate dataAtual);

}
