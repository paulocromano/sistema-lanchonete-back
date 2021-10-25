package br.com.sistemalanchonete.lanche.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemalanchonete.lanche.model.Lanche;

public interface LancheRepository extends JpaRepository<Lanche, Long> {

	Optional<Lanche> findByNomeIgnoreCase(String nomeLanche);

	Optional<Lanche> findByIdNotAndNomeIgnoreCase(Long id, String nomeLanche);

	List<Lanche> findByIdIn(List<Long> idLanches);

}
