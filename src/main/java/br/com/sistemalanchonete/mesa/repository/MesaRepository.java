package br.com.sistemalanchonete.mesa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemalanchonete.mesa.model.Mesa;
import br.com.sistemalanchonete.utils.enums.Resposta;

public interface MesaRepository extends JpaRepository<Mesa, Integer> {

	List<Mesa> findByMesaAtiva(Resposta sim);

	Optional<Mesa> findByNumero(Integer numero);
}
