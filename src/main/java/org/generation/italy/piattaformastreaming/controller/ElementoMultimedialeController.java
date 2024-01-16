package org.generation.italy.piattaformastreaming.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.generation.italy.piattaformastreaming.model.ElementoMultimediale;
import org.generation.italy.piattaformastreaming.repository.ElementoMultimedialeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping ("/elementoMultimediale")
public class ElementoMultimedialeController {

	@Autowired
	ElementoMultimedialeRepository elementoMultimedialeRepository;
	
	@GetMapping("/elenco")														//gestisce una richiesta GET all'indirizzo /elementoMultimediale/elenco?categoria=xxx&ordinamento=asc
	@ResponseBody
	public String elencoElementiMultimediali(
			@RequestParam(required = false) String titolo,
			@RequestParam(required = false) String tipologia,
			@RequestParam(required = false) String genere,
			@RequestParam(required = false) String ordinamento,
			@RequestParam(required = false) Integer annoMinimo,
			@RequestParam(required = false) Integer annoMassimo) throws Exception {
		
		
		ArrayList<ElementoMultimediale> elencoElementiMultimediali = null;
		
		if (titolo!=null)														// tutti i contenuti il cui titolo contiene una parola chiave
			elencoElementiMultimediali=(ArrayList<ElementoMultimediale>) elementoMultimedialeRepository.findByTitoloLike("%"+titolo+"%");
		
		if (tipologia!=null)													// tutti i contenuti di un determinata categoria
			elencoElementiMultimediali=(ArrayList<ElementoMultimediale>) elementoMultimedialeRepository.findByTipologia(tipologia);
		
		if (genere!=null)														// tutti i contenuti di un determinato genere
			elencoElementiMultimediali=(ArrayList<ElementoMultimediale>) elementoMultimedialeRepository.findByGenere(genere);
		
		if (titolo == null && genere == null && tipologia == null) 				// tutti i contenuti 
			elencoElementiMultimediali=(ArrayList<ElementoMultimediale>) elementoMultimedialeRepository.findAll();
		
		if (annoMinimo != null && annoMassimo != null)
			elencoElementiMultimediali=(ArrayList<ElementoMultimediale>) elementoMultimedialeRepository.findByAnnoBetween(annoMinimo, annoMassimo);		
		
		if (ordinamento!=null)
		{
			if (ordinamento.equals("asc"))										//ordinamento predefinito (tramite titolo) in maniera crescente
				Collections.sort(elencoElementiMultimediali);
			
			else if (ordinamento.equals("desc"))								//ordinamento predefinito (tramite titolo) in maniera decrescente
				Collections.sort(elencoElementiMultimediali,Collections.reverseOrder());
			
			else
				return "Ordinamento non valido";
		}
		
		StringBuilder elenco=new StringBuilder();
		elenco.append("Numero Elementi Multimediali: "+elencoElementiMultimediali.size());
		elenco.append("<br><br>");
		
		for (ElementoMultimediale p:elencoElementiMultimediali)
			elenco.append(p.toString()+ "<br>");
		return elenco.toString();
	}
	
	@GetMapping ("{id}")
	@ResponseBody
	public String dettaglioProdotto (@PathVariable Integer id) {
		
		Optional<ElementoMultimediale> optElementoMultimediale = elementoMultimedialeRepository.findById(id);
		
		if (optElementoMultimediale.isPresent())
			return optElementoMultimediale.get().toString();
		
		else
			return "Elemento non trovato";
	}
}
