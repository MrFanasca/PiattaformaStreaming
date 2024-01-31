package org.generation.italy.piattaformastreaming.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.generation.italy.piattaformastreaming.model.ElementoMultimediale;
import org.generation.italy.piattaformastreaming.model.Regista;
import org.generation.italy.piattaformastreaming.repository.RegistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Regista")
public class RegistaController {
	
	@Autowired
	RegistaRepository registaRepoitory;
	
	@GetMapping			//gestisce una richiesta GET all'indirizzo /regista
	@ResponseBody
	public String index() {
		return "Benvenuto nella gestione dei registi!";
	}
	
	@GetMapping("/elenco")
	public String elencoRegisti(Model model,
												@RequestParam(required = false) String cognome,
												@RequestParam(required = false) String nome,
												@RequestParam(required = false) String nazionalita,
												@RequestParam(required = false) String ordinamento) throws Exception {

		ArrayList <Regista> elencoRegisti = null;
		
		if (cognome!=null)	{													// tutti i registi che contengono nel cognome una parola chiave
			elencoRegisti = (ArrayList<Regista>) registaRepoitory.findByCognomeLike("%"+cognome+"%");
		}
		
		if (nome!=null)	{														// tutti i registi che contengono nel nome una parola chiave
			elencoRegisti = (ArrayList<Regista>) registaRepoitory.findByNomeLike("%"+nome+"%");
		}
		
		if (nazionalita!=null)	{												// tutti i registi di una determinata nazionalità
			elencoRegisti = (ArrayList<Regista>) registaRepoitory.findByNazionalita(nazionalita);
		}
		
		if (cognome==null && nome==null && nazionalita==null)	{				// tutti i registi
			elencoRegisti = (ArrayList<Regista>) registaRepoitory.findAll();
		}
		
		if (ordinamento!=null)
		{
			if (ordinamento.equals("asc"))										//ordinamento predefinito (tramite cognome) in maniera crescente
				Collections.sort(elencoRegisti);			
			else if (ordinamento.equals("desc"))								//ordinamento predefinito (tramite cognome) in maniera decrescente
				Collections.sort(elencoRegisti,Collections.reverseOrder());			
			else
				return "Ordinamento non valido";
		}
		
		model.addAttribute("elenco", elencoRegisti);
		
		return "registi/elenco";
	}

	@GetMapping ("/dettaglio/{id}")														// dettaglio dell'elemento tramite id 	
	public String dettaglioRegista (Model model, @PathVariable Integer id) {
		
		Optional<Regista> optRegista = registaRepoitory.findById(id);
		
		if (optRegista.isPresent()) {
			
			Regista r = optRegista.get();
			model.addAttribute("regista", r);
			
			return "registi/dettaglio";
		} else {
			
			return "Elemento non trovato";
		}
	}
	
}