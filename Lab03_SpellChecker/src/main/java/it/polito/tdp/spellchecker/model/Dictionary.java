package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Dictionary {

	LinkedList<String> diz;
	LinkedList<RichWord> parole;

	public void loadDictionary(String language) {

		this.diz = new LinkedList<String>();

		try {
			FileReader fr = new FileReader(language + ".txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
				diz.add(word.toLowerCase());
			}
			br.close();
		} catch (IOException IOE) {
			System.out.println("Errore nella lettura del file");
		}

	}

	public LinkedList<RichWord> spellCheckText(LinkedList<String> inputTextList) {

		parole = new LinkedList<RichWord>();
		
		try {
			for (String s : inputTextList) {
				s.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
				if (diz.contains(s.toLowerCase()))
					parole.add(new RichWord(s, true));
				else
					parole.add(new RichWord(s, false));

			}

		} catch (Exception E) {
			System.out.println("errore nel controllo ortografico");
		}

		return parole;
	}

}
