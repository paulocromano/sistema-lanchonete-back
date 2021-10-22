package br.com.sistemalanchonete.mesa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistemalanchonete.mesa.repository.MesaRepository;

@Service
public class MesaService {

	@Autowired
	private MesaRepository mesaRepository;
}
