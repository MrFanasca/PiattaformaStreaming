package org.generation.italy.piattaformastreaming.restcontroller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.piattaformastreaming.model.ElementoMultimediale;
import org.generation.italy.piattaformastreaming.repository.ElementoMultimedialeRepository;
import org.generation.italy.piattaformastreaming.repository.RegistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ElementoMultimediale")
public class ElementoMultimedialeRestController {

	@Autowired
	ElementoMultimedialeRepository elementoMultimedialeRepository;
	
	@Autowired
	RegistaRepository registaRepository;
	
	/*****************/
	// RICHIESTE GET //
	/*****************/
	@GetMapping
	public String benvenuto() {
		return "benvenuto nella gestione dei contenuti";
	}
	
	@GetMapping("/elenco")
	public List<ElementoMultimediale> elencoElementoMultimediale () {
		return elementoMultimedialeRepository.findAll();
	}
	
	@GetMapping("{id}")
	public ElementoMultimediale dettaglioElementoMultimediale (@PathVariable("id") int id) {
		
		Optional <ElementoMultimediale> result = elementoMultimedialeRepository.findById(id);
		if(result.isPresent())
			return result.get();
		else
			return null;
	}
	
	/******************/
	// RICHIESTE POST //
	/******************/	
	@PostMapping
	public ElementoMultimediale aggiungiElementoMultimediale (@RequestBody ElementoMultimediale elementoMultimediale) {
		
		registaRepository.save(elementoMultimediale.getRegista());		
		return elementoMultimedialeRepository.save(elementoMultimediale);
	}
	
	/*****************/
	// RICHIESTE PUT //
	/*****************/
	@PutMapping ("{id}")
	public ElementoMultimediale aggiornaElementoMultimediale (@PathVariable Integer id, @RequestBody ElementoMultimediale elementoMultimediale) {
		
		Optional <ElementoMultimediale> result = elementoMultimedialeRepository.findById(id);
		
		if (result.isPresent()) {
			
			ElementoMultimediale em = result.get();
			
			em.setTitolo(elementoMultimediale.getTitolo());
			em.setGenere(elementoMultimediale.getGenere());
			em.setTipologia(elementoMultimediale.getTipologia());
			em.setAnno(elementoMultimediale.getAnno());
			em.setDurata(elementoMultimediale.getDurata());
			em.setRegista(elementoMultimediale.getRegista());
			
			return elementoMultimedialeRepository.save(em);
		}
		else {
			
			return null;
		}
	}
	
	/********************/
	// RICHIESTE DELETE //
	/********************/
	@DeleteMapping ("{id}")
	public void eliminaElementoMultimediale (@PathVariable Integer id) {
		
		elementoMultimedialeRepository.deleteById(id);
	}
	
}