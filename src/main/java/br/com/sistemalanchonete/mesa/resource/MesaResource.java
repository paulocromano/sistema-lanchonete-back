package br.com.sistemalanchonete.mesa.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemalanchonete.mesa.service.MesaService;

@RequestMapping(path = "/mesa")
@RestController
public class MesaResource {

	@Autowired
	private MesaService mesaService;
}
