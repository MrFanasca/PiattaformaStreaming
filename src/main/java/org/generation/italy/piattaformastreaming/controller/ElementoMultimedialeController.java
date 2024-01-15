package org.generation.italy.piattaformastreaming.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.generation.italy.piattaformastreaming.model.ElementoMultimediale;
import org.generation.italy.piattaformastreaming.repository.ElementoMultimedialeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping ("/elementoMultimediale")
public class ElementoMultimedialeController {

	@Autowired
	ElementoMultimedialeRepository elementoMultimedialeRepository;
	
	@GetMapping("/elenco")														//gestisce una richiesta GET all'indirizzo /Prodotti/elenco?categoria=xxx&ordinamento=asc
	@ResponseBody
	public String elencoElementiMultimediali(
			@RequestParam(required = false) String titolo,
			@RequestParam(required = false) String categoria,
			@RequestParam(required = false) String genere,
			@RequestParam(required = false) String ordinamento,
			@RequestParam(required = false) int annoMinimo,
			@RequestParam(required = false) int annoMassimo) throws Exception {
		
		
		ArrayList<ElementoMultimediale> elencoElementiMultimediali = null;
		
		if (titolo!=null)														// tutti i contenuti il cui titolo contiene una parola chiave
			elencoElementiMultimediali=(ArrayList<ElementoMultimediale>) elementoMultimedialeRepository.findByTitoloLike("%"+titolo+"%");
		
		if (categoria!=null)													// tutti i contenuti di un determinata categoria
			elencoElementiMultimediali=(ArrayList<ElementoMultimediale>) elementoMultimedialeRepository.findByCategoria(categoria);
		
		if (genere!=null)														// tutti i contenuti di un determinato genere
			elencoElementiMultimediali=(ArrayList<ElementoMultimediale>) elementoMultimedialeRepository.findByGenere(genere);
		
		if (titolo == null && genere == null && categoria == null) 				// tutti i contenuti 
			elencoElementiMultimediali=(ArrayList<ElementoMultimediale>) elementoMultimedialeRepository.findAll();
		
		if (annoMinimo != 0 && annoMassimo != 0)
			elencoElementiMultimediali=(ArrayList<ElementoMultimediale>) elementoMultimedialeRepository.findByAnnoProduzione(annoMinimo, annoMassimo);		
		
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
}
