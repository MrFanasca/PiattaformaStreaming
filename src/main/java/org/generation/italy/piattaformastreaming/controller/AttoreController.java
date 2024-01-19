package org.generation.italy.piattaformastreaming.controller;

import java.util.ArrayList;
import java.util.Collections;

import org.generation.italy.piattaformastreaming.model.Attore;
import org.generation.italy.piattaformastreaming.repository.AttoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping ("/Attore")
public class AttoreController {
	
	@Autowired
	AttoreRepository attoreRepository;
	
	@GetMapping
	@ResponseBody
	public String index() {
		return "Benvenuti nella gesione degli attori!";
	}
	
	@GetMapping ("/elenco")
	@ResponseBody
	public String elencoAttori (
			@RequestParam(required=false) String cognome,
			@RequestParam(required=false) String nome,
			@RequestParam(required=false) String nazionalita,
			@RequestParam(required=false) String ordinamento) throws Exception {
		
		ArrayList<Attore> elencoAttori = null;
		
		if (cognome!=null) {
			elencoAttori = (ArrayList<Attore>) attoreRepository.findByCognomeLike("%"+cognome+"%");
		}
		
		if (nome!=null)	{
			elencoAttori = (ArrayList<Attore>) attoreRepository.findByNomeLike("%"+nome+"%");
		}
		
		if (nazionalita!=null)	{
			elencoAttori = (ArrayList<Attore>) attoreRepository.findByNazionalita(nazionalita);
		}
		
		if (cognome==null && nome==null && nazionalita==null)	{
			elencoAttori = (ArrayList<Attore>) attoreRepository.findAll();
		}
		
		if (ordinamento!=null)	{
			if (ordinamento.equals("asc"))
				Collections.sort(elencoAttori);
			
			else if (ordinamento.equals("desc"))
				Collections.sort(elencoAttori, Collections.reverseOrder());
			
			else 
				return "Ordinamento non valido";
		}
		
		StringBuilder elenco = new StringBuilder();
		elenco.append("Numero attori: " + elencoAttori.size());
		elenco.append("<br><br>");
		
		for (Attore a:elencoAttori)
			elenco.append(a.toString() + "<br>");
			
		return elenco.toString();
	}

}
