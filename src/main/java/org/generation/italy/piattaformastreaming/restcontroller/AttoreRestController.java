package org.generation.italy.piattaformastreaming.restcontroller;

import java.util.List;

import org.generation.italy.piattaformastreaming.model.Attore;
import org.generation.italy.piattaformastreaming.repository.AttoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Attore")
public class AttoreRestController {
	
	@Autowired
	AttoreRepository attoreRepository;
	
	@GetMapping("/elenco")
	public List<Attore> elencoAttori () {
		
		return attoreRepository.findAll();
	}

}
