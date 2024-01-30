package org.generation.italy.piattaformastreaming.restcontroller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.piattaformastreaming.model.Regista;
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
@RequestMapping("/api/Regista")
public class RegistaRestController {
	
	@Autowired
	RegistaRepository registaRepository;
	
	/*****************/
	// RICHIESTE GET //
	/*****************/	
	@GetMapping("/elenco")
	public ResponseEntity<List<Regista>> elencoRegisti (@RequestParam(required=false) String nazionalita) {
		
		try {
			
			List<Regista> elencoRegisti;
			
			if (nazionalita==null) {
				
				elencoRegisti = registaRepository.findAll();
			} else {
				
				elencoRegisti = registaRepository.findByNazionalita(nazionalita);
			}
			
			if (elencoRegisti.size()>0) {
				
				return new ResponseEntity<List<Regista>>(elencoRegisti, HttpStatus.OK);
			} else {
				
				return new ResponseEntity<List<Regista>>(HttpStatus.NO_CONTENT);
			}			
		} catch (Exception e) {
			
			return new ResponseEntity<List<Regista>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Regista> dettaglioRegista (@PathVariable ("id") int id) {
		
		Optional<Regista> result = registaRepository.findById(id);
		
		if (result.isPresent()) {
			
			return new ResponseEntity<Regista>(result.get(), HttpStatus.OK);
		} else {
			
			return new ResponseEntity<Regista>(HttpStatus.NOT_FOUND);
		}
	}

	/******************/
	// RICHIESTE POST //
	/******************/
	@PostMapping
	public Regista aggiungiRegista (@RequestBody Regista regista) {
		
		return registaRepository.save(regista);
	}
	
	/*****************/
	// RICHIESTE PUT //
	/*****************/
	@PutMapping ("{id}")
	public Regista aggiornaRegista (@PathVariable Integer id, @RequestBody Regista regista) {
		
		Optional<Regista> result = registaRepository.findById(id);
		
		if (result.isPresent()) {
			
			Regista r = result.get();
			
			r.setCognome(regista.getCognome());
			r.setNome(regista.getNome());
			r.setNazionalita(regista.getNazionalita());
			
			return registaRepository.save(r);
		} else {
			
			return null;
		}
	}
	
	/********************/
	// RICHIESTE DELETE //
	/********************/
	@DeleteMapping ("{id}")
	public void eliminaRegista (@PathVariable Integer id) {
		
		registaRepository.deleteById(id);
	}
	
}
