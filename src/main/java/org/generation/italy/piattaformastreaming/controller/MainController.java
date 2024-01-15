package org.generation.italy.piattaformastreaming.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@GetMapping			//gestisce una richiesta GET all'indirizzo /
	@ResponseBody
	public String index() {
		return "Benvenuto nella nostra piattaforma di streaming!!!";
	}
}
