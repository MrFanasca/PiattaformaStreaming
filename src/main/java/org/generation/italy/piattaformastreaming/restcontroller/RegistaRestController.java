package org.generation.italy.piattaformastreaming.restcontroller;

import java.util.List;

import org.generation.italy.piattaformastreaming.model.Regista;
import org.generation.italy.piattaformastreaming.repository.RegistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/regista")
public class RegistaRestController {
	
	@Autowired
	RegistaRepository registaRepository;
	
	@GetMapping("/elenco")
	public List<Regista> elencoRegisti () {
		
		return registaRepository.findAll();
	}

}