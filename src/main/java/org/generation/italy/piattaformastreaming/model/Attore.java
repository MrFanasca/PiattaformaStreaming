package org.generation.italy.piattaformastreaming.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

public class Attore {


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
	
	@Column
	private LocalDate dataNascita;
	
	@ManyToMany
	private List<ElementoMultimediale> elencoElementiMultimediali;

	/***************/
	// COSTRUTTORI //
	/***************/
	public Attore() {
		super();
	}
	
	public Attore(int id, String nome, String cognome, String nazionalita) {
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

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public List<ElementoMultimediale> getElencoElementiMultimediali() {
		return elencoElementiMultimediali;
	}
	
	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	/**********/
	// METODI //
	/**********/
	@Override
	public String toString() {
		return "Attore [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", nazionalita=" + nazionalita
				+ ", dataNascita=" + dataNascita + "]";
	}
}
