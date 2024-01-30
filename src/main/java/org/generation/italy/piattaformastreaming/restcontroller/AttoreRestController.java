package org.generation.italy.piattaformastreaming.restcontroller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.piattaformastreaming.model.Attore;
import org.generation.italy.piattaformastreaming.repository.AttoreRepository;
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
@RequestMapping("/api/Attore")
public class AttoreRestController {
	
	@Autowired
	AttoreRepository attoreRepository;
	
	/*****************/
	// RICHIESTE GET //
	/*****************/		
	@GetMapping("/elenco")
	public ResponseEntity<List<Attore>> elencoAttori (@RequestParam(required=false) String nazionalita) {
		
		try {
			
			List<Attore> elencoAttori;
			
			if (nazionalita==null) {
				
				elencoAttori = attoreRepository.findAll();
			} else {
				
				elencoAttori = attoreRepository.findByNazionalita(nazionalita);
			}
			
			if (elencoAttori.size()>0) {
				
				return new ResponseEntity<List<Attore>>(elencoAttori, HttpStatus.OK);
			} else {
				
				return new ResponseEntity<List<Attore>>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			
			return new ResponseEntity<List<Attore>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<Attore> dettaglioAttore (@PathVariable ("id") int id) {
		
		Optional<Attore> result = attoreRepository.findById(id);
		
		if (result.isPresent()) {
			
			return new ResponseEntity<Attore>(result.get(), HttpStatus.OK);
		} else {
			
			return new ResponseEntity<Attore>(HttpStatus.NOT_FOUND);
		}
	}
	
	/******************/
	// RICHIESTE POST //
	/******************/
	@PostMapping
	public Attore aggiungiAttore (@RequestBody Attore attore) {
	
		return attoreRepository.save(attore);
	}
	
	/*****************/
	// RICHIESTE PUT //
	/*****************/
	@PutMapping ("{id}")
	public Attore aggiornaAttore (@PathVariable Integer id, @RequestBody Attore attore) {
		
		Optional<Attore> result = attoreRepository.findById(id);
		
		if (result.isPresent()) {
			
			Attore a = result.get();
			
			a.setCognome(attore.getCognome());
			a.setNome(attore.getNome());
			a.setNazionalita(attore.getNazionalita());
			a.setDataNascita(attore.getDataNascita());
			
			return attoreRepository.save(a);
		} else {
			
			return null;
		}
	}
	
	/********************/
	// RICHIESTE DELETE //
	/********************/
	@DeleteMapping ("{id}")
	public void eliminaAttore (@PathVariable Integer id) {
		
		attoreRepository.deleteById(id);
	}
	
}
