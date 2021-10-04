package br.com.sistemalanchonete.lanche.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.sistemalanchonete.exception.custom.ObjectNotFoundException;
import br.com.sistemalanchonete.lanche.dto.ImagemLancheBase64DTO;
import br.com.sistemalanchonete.lanche.dto.LancheDTO;
import br.com.sistemalanchonete.lanche.form.LancheFORM;
import br.com.sistemalanchonete.lanche.model.Lanche;
import br.com.sistemalanchonete.lanche.repository.LancheRepository;

@Service
public class LancheService {

	@Autowired
	private LancheRepository lancheRepository;
	
	@Autowired
	private ImagemLancheService imagemLancheService;
	
	
	public ResponseEntity<List<LancheDTO>> listarLanches() {
		List<Lanche> lanches = lancheRepository.findAll();
		
		return ResponseEntity.ok().body(LancheDTO.converter(lanches));
	}
	
	public ResponseEntity<Void> cadastrarLanche(LancheFORM lancheFORM) {
		verificarSeExisteLancheComMesmoNome(lancheFORM.getNome());
		Lanche lanche = lancheFORM.converterParaLanche();
		lancheRepository.save(lanche);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	public ResponseEntity<ImagemLancheBase64DTO> encodeImagemLancheParaBase64(MultipartFile imagem) {
		String imagemBase64 = imagemLancheService.encodeBase64(imagem);
		
		return ResponseEntity.ok().body(new ImagemLancheBase64DTO(imagemBase64));
	}

	public ResponseEntity<Void> alterarDadosLanche(Long id, LancheFORM lancheFORM) {
		verificarSeExisteLancheComMesmoNome(id, lancheFORM.getNome());
		Lanche lanche = verificarSeLancheExiste(id);
		lancheFORM.atualizarDadosLanche(lanche);
		
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Void> excluirLanche(Long id) {		
		Lanche lanche = verificarSeLancheExiste(id);
		lancheRepository.delete(lanche);
		
		return ResponseEntity.noContent().build();
	}
	
	public void verificarSeExisteLancheComMesmoNome(String nomeLanche) {
		Optional<Lanche> lanche = lancheRepository.findByNomeIgnoreCase(nomeLanche.trim());
		
		if (lanche.isPresent())
			throw new IllegalArgumentException("Já existe um lanche com o mesmo nome cadastrado!");
	}
	
	public void verificarSeExisteLancheComMesmoNome(Long id, String nomeLanche) {
		if (Objects.isNull(id))
			throw new NullPointerException("O ID do lanche não pode ser nulo!");
		
		Optional<Lanche> lanche = lancheRepository.findByIdNotAndNomeIgnoreCase(id, nomeLanche.trim());
		
		if (lanche.isPresent())
			throw new IllegalArgumentException("Já existe um lanche com o mesmo nome cadastrado!");
	}
	
	public Lanche verificarSeLancheExiste(Long id) {
		if (Objects.isNull(id))
			throw new NullPointerException("O ID do lanche não pode ser nulo!");
		
		Optional<Lanche> lanche = lancheRepository.findById(id);
		
		if (lanche.isEmpty())
			throw new ObjectNotFoundException("Lanche não encontrado!");
		
		return lanche.get();
	}
}
