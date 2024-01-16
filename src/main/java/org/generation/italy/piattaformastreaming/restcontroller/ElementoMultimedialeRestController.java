package org.generation.italy.piattaformastreaming.restcontroller;

import java.util.List;

import org.generation.italy.piattaformastreaming.model.ElementoMultimediale;
import org.generation.italy.piattaformastreaming.repository.ElementoMultimedialeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ElementiMultimediali")
public class ElementoMultimedialeRestController {

	@Autowired
	ElementoMultimedialeRepository elementoMultimedialeRepository;
	
	@GetMapping("/elenco")
	public List<ElementoMultimediale> elencoElementoMultimediale () {
		return elementoMultimedialeRepository.findAll();
	}
}
