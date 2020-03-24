package it.polito.tdp.spellchecker.model;

public class RichWord {
	String parola;
	boolean esiste;

	public RichWord(String parola, boolean esiste) {
		super();
		this.parola = parola;
		this.esiste = esiste;
	}

	public String getParola() {
		return parola;
	}

	public boolean isEsiste() {
		return esiste;
	}
	
	

}
