package org.generation.italy.piattaformastreaming.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ElementoMultimediale implements Comparable<ElementoMultimediale>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (nullable = false, length = 30)
	private String titolo;
	
	@Column (nullable = false, length = 25)
	private String tipologia;
	
	@Column (nullable = false, length = 15)
	private String genere;
	
	@Column (nullable = false)
	private int anno;
	
	@Column (nullable = false)
	private int durata;

	public ElementoMultimediale() {
		super();
	}

	public ElementoMultimediale(int id, String titolo, String tipologia, String genere, int anno, int durata) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.tipologia = tipologia;
		this.genere = genere;
		this.anno = anno;
		this.durata = durata;
	}

	public int getId() {
		return id;
	}

	public String getTitolo() {
		return titolo;
	}

	public String getTipologia() {
		return tipologia;
	}

	public String getGenere() {
		return genere;
	}

	public int getAnno() {
		return anno;
	}

	public int getDurata() {
		return durata;
	}

	@Override
	public String toString() {
		return "ElementoMultimediale [id=" + id + ", titolo=" + titolo + ", tipologia=" + tipologia + ", genere="
				+ genere + ", anno=" + anno + ", durata=" + durata + "]";
	}
	
	@Override
	public int compareTo(ElementoMultimediale em) {								//confronto per nome
		
		if (this.titolo.compareTo(em.getTitolo())!=0)							//se i nomi non sono uguali
			return this.titolo.compareTo(em.getTitolo());
		
		else {																	//se i nomi sono uguali ordino per prezzo
			
		if (this.anno>em.anno)
			return 1;
		
		else if	(this.anno<em.anno)
			return -1;
		
		else
			return 0;
		}
	}
	
}