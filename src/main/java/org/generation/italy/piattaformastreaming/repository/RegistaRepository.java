package org.generation.italy.piattaformastreaming.repository;

import java.util.List;


import org.generation.italy.piattaformastreaming.model.Regista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistaRepository extends JpaRepository <Regista, Integer> {

	List<Regista> findByCognomeLike(String cognome);
	List<Regista> findByNomeLike(String nome);
	List<Regista> findByNazionalita(String nazionalita);
}
