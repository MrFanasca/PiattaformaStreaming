package org.generation.italy.piattaformastreaming.restcontroller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.piattaformastreaming.model.ElementoMultimediale;
import org.generation.italy.piattaformastreaming.repository.ElementoMultimedialeRepository;
import org.generation.italy.piattaformastreaming.repository.RegistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<List<ElementoMultimediale>> elencoElementoMultimediale (@RequestParam(required=false) String tipologia) {
		
		try {
			
			List<ElementoMultimediale> elencoElementoMultimediale;
			
			if (tipologia == null) {
				
				elencoElementoMultimediale = elementoMultimedialeRepository.findAll();
			} else {
				
				elencoElementoMultimediale = elementoMultimedialeRepository.findByTipologia(tipologia);
			}
			
			if (elencoElementoMultimediale.size()>0) {
				
				return new ResponseEntity<List<ElementoMultimediale>>(elencoElementoMultimediale, HttpStatus.OK);
			} else {
				
				return new ResponseEntity<List<ElementoMultimediale>>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {

			return new ResponseEntity<List<ElementoMultimediale>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ElementoMultimediale> dettaglioElementoMultimediale (@PathVariable("id") int id) {
		
		Optional <ElementoMultimediale> result = elementoMultimedialeRepository.findById(id);
		if (result.isPresent()) {
			
			return new ResponseEntity<ElementoMultimediale>(result.get(), HttpStatus.OK);
		} else {
		
			return new ResponseEntity<ElementoMultimediale>(HttpStatus.NOT_FOUND);
		}			
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
		} else {
			
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