package br.com.sistemalanchonete.mesa.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.sistemalanchonete.exception.custom.ObjectNotFoundException;
import br.com.sistemalanchonete.mesa.dto.MesaDTO;
import br.com.sistemalanchonete.mesa.form.AlteracaoMesaFORM;
import br.com.sistemalanchonete.mesa.form.MesaFORM;
import br.com.sistemalanchonete.mesa.model.Mesa;
import br.com.sistemalanchonete.mesa.repository.MesaRepository;
import br.com.sistemalanchonete.pedido.model.Pedido;
import br.com.sistemalanchonete.pedido.repository.PedidoRepository;
import br.com.sistemalanchonete.utils.enums.Resposta;

@Service
public class MesaService {

	@Autowired
	private MesaRepository mesaRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	public ResponseEntity<List<MesaDTO>> buscarMesas() {
		List<Mesa> mesas = mesaRepository.findAll();
		return ResponseEntity.ok().body(MesaDTO.converter(mesas));
	}
	
	public ResponseEntity<List<MesaDTO>> buscarMesasDisponiveis() {
		List<Mesa> mesas = mesaRepository.findByDisponivelAndMesaAtiva(Resposta.SIM, Resposta.SIM);
		return ResponseEntity.ok().body(MesaDTO.converter(mesas));
	}
	
	public ResponseEntity<Void> cadastrarMesa(MesaFORM mesaFORM) {
		mesaRepository.save(mesaFORM.converterParaMesa());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	public ResponseEntity<Void> alterarDadosMesa(Integer id, AlteracaoMesaFORM alteracaoMesaFORM) {
		Mesa mesa = verificarSeMesaExiste(id);
		alteracaoMesaFORM.atualizarDadosMesa(mesa);
		
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Void> excluirMesa(Integer id) {
		Mesa mesa = verificarSeMesaExiste(id);
		
		if (mesa.getDisponivel().equals(Resposta.NAO))
			throw new IllegalArgumentException("Não é possível excluir uma mesa "
					+ "que esteja em uso!");
		
		verificarSeExisteAlumPedidoComMesaIgual(mesa);
		mesaRepository.delete(mesa);
		
		return ResponseEntity.noContent().build();
	}
	
	private void verificarSeExisteAlumPedidoComMesaIgual(Mesa mesa) {
		Optional<Pedido> pedido = pedidoRepository.findFirstByMesa(mesa);
		
		if(pedido.isPresent())
			throw new IllegalArgumentException("Não é possível excluir "
					+ "uma mesa que esteja em um pedido!");
	}
	
	public Mesa verificarSeMesaExiste(Integer id) {
		if (Objects.isNull(id))
			throw new NullPointerException("O ID da Mesa não pode ser nulo!");
		
		Optional<Mesa> mesa = mesaRepository.findById(id);
		
		if (mesa.isEmpty())
			throw new ObjectNotFoundException("Mesa não encontrada!");
		
		return mesa.get();
	}
}
