package org.generation.italy.piattaformastreaming.controller;

import java.util.ArrayList;
import java.util.Collections;

import org.generation.italy.piattaformastreaming.model.Regista;
import org.generation.italy.piattaformastreaming.repository.RegistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/regista")
public class RegistaController {
	
	@Autowired
	RegistaRepository registaRepoitory;
	
	@GetMapping("/elenco")
	@ResponseBody
	public String elencoElementiMultimediali(
			@RequestParam(required = false) String cognome,
			@RequestParam(required = false) String nome,
			@RequestParam(required = false) String nazionalita,
			@RequestParam(required = false) String ordinamento) throws Exception {

		ArrayList <Regista> elencoRegisti = null;
		
		if (cognome!=null)	{													// tutti i registi che contengono nel cognome una parola chiave
			elencoRegisti = (ArrayList<Regista>) registaRepoitory.findByCognomeLike(cognome);
		}
		
		if (nome!=null)	{														// tutti i registi che contengono nel nome una parola chiave
			elencoRegisti = (ArrayList<Regista>) registaRepoitory.findByNomeLike(nome);
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
		
		StringBuilder elenco=new StringBuilder();
		elenco.append("Numero Registi: "+elencoRegisti.size());
		elenco.append("<br><br>");
		
		for (Regista r:elencoRegisti)
			elenco.append(r.toString()+ "<br>");
		
		return elenco.toString();
	}

}