package org.generation.italy.piattaformastreaming.repository;

import java.util.List;

import org.generation.italy.piattaformastreaming.model.Attore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttoreRepository extends JpaRepository<Attore, Integer>{

	List<Attore> findByNomeLike (String nome);
	List<Attore> findByCognomeLike (String cognome);
	List<Attore> findByNazionalita (String nazionalita);
}
