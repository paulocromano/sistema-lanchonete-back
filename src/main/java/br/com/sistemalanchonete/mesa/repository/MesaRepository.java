package br.com.sistemalanchonete.mesa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemalanchonete.mesa.model.Mesa;
import br.com.sistemalanchonete.utils.enums.Resposta;

public interface MesaRepository extends JpaRepository<Mesa, Integer> {

	List<Mesa> findByDisponivelAndMesaAtiva(Resposta disponivel, Resposta mesaAtiva);
}
