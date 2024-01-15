package org.generation.italy.piattaformastreaming.repository;

import java.util.List;

import org.generation.italy.piattaformastreaming.model.ElementoMultimediale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementoMultimedialeRepository extends JpaRepository <ElementoMultimediale, Integer>{

	List<ElementoMultimediale> findByTitoloLike(String titolo);
	List<ElementoMultimediale> findByCategoria(String categoria);
	List<ElementoMultimediale> findByGenere(String genere);
	//JPQL Java Persistence Query Language
	@Query("select em from ElementoMultimediale em where em.anno> ? and em.anno<=? ")
	List<ElementoMultimediale> findByAnnoProduzione(int annoMin, int annoMax);
	
}
