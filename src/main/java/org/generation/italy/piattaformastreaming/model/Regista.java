package org.generation.italy.piattaformastreaming.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Regista implements Comparable<Regista>{

	/*************/
	// ATTRIBUTI //
	/*************/
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (nullable = false, length = 15)
	private String nome;
	
	@Column (nullable = false, length = 15)
	private String cognome;
	
	@Column (nullable = false, length = 20)
	private String nazionalita;
	
	@JsonBackReference
	@OneToMany (mappedBy = "regista")
	List<ElementoMultimediale> elencoElementoMultimediale;
	
	/***************/
	// COSTRUTTORI //
	/***************/
	public Regista() {
		super();
	}

	public Regista (int id, String nome, String cognome, String nazionalita) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.nazionalita = nazionalita;
	}
	
	/*******************/
	// GETTER E SETTER //
	/*******************/
	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	
	// se commentato non da più il problema del loop infinito nella ricerca api dell'elemento multimediale
//	public List<ElementoMultimediale> getElencoElementiMultimediali () {
//		
//		return elencoElementoMultimediale;
//	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	/**********/
	// METODI //
	/**********/

	@Override
	public String toString() {
		return "Regista [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", nazionalita=" + nazionalita
				+ "]";
	}

	@Override
	public int compareTo(Regista r) {
		
		if (this.cognome.compareTo(r.getCognome())!=0) {							//se i cognomi non sono uguali
			return this.cognome.compareTo(r.getCognome());
		}
		else {
			return this.nome.compareTo(r.getNome());
		}
		
	}
	
	
}
