package org.generation.italy.piattaformastreaming.repository;

import java.util.List;

import org.generation.italy.piattaformastreaming.model.Attore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttoreRepository extends JpaRepository<Attore, Integer>{

	List<Attore> findByNomeLike (String nome);
	List<Attore> findByCogomeLike (String cognome);
	List<Attore> findByNazionalita (String nazionalita);
}
