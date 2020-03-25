package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Line;

public class FXMLController {

	Dictionary dizionario;
	ObservableList<String> lingue = FXCollections.observableArrayList("English", "Italiano");

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label cdlB;

	@FXML
	private ComboBox<String> sceltaLinguaB;

	@FXML
	private TextArea areaTesto1;

	@FXML
	private Button butt1;

	@FXML
	private Label labelParoleSbagliate;

	@FXML
	private TextArea spazioFinale;

	@FXML
	private Label labelFondoPagina;

	@FXML
	private Button clearAllB;

	@FXML
	void clearAll(ActionEvent event) {
		areaTesto1.setText("");
		if (sceltaLinguaB.getValue().equals("English")) {
			spazioFinale.setText("Welcome!");
			labelFondoPagina.setText("                                         ");

		}
		else {
			spazioFinale.setText("Benvenuto!");
			labelFondoPagina.setText("                                      ");

			
		}

	}

	@FXML
	void confermaFrase(ActionEvent event) {
		int cont = 0;
		spazioFinale.setText("");
		
		String testo = areaTesto1.getText();
		String[] paroleTesto;
		paroleTesto = testo.split(" ");
		
		LinkedList<String> paroleTXT = new LinkedList<String>();
		for (String s : paroleTesto)
			paroleTXT.add(s);

		LinkedList<RichWord> finale = dizionario.spellCheckText(paroleTXT);
		
		for (RichWord r : finale) {
			if (r.isEsiste() == false) {
				if (spazioFinale.getText().equals(""))
					spazioFinale.appendText(r.getParola());
				else
					spazioFinale.appendText("\n" + r.getParola());
			
				cont++;
			}
		}
		
		if(sceltaLinguaB.getValue().equals("Italiano"))
			labelFondoPagina.setText("Il testo contiene " + cont + " errori");
			else labelFondoPagina.setText("The text contains " + cont+ " errors");
			 

	}

	@FXML
	void sceltaLingua(ActionEvent event) {
		if (sceltaLinguaB.getValue().equals("Italiano")) {
			clearAllB.setText("Cancella Tutto");
			labelParoleSbagliate.setText("Parole sbagliate: ");
			butt1.setText("Controllo ortografico");
			cdlB.setText("Scegli la lingua");
			spazioFinale.setText("Benvenuto!");
			dizionario.loadDictionary("src/main/resources/Italian");
		}

		if (sceltaLinguaB.getValue().equals("English")) {
			clearAllB.setText("Clear Text");
			labelParoleSbagliate.setText("Wrong words: ");
			butt1.setText("Spell Check");
			cdlB.setText("Choose the language:");
			spazioFinale.setText("Welcome!");
			dizionario.loadDictionary("src/main/resources/English");
		}
	}

	@FXML
	void initialize() {
		assert cdlB != null : "fx:id=\"cdlB\" was not injected: check your FXML file 'Scene.fxml'.";
		assert sceltaLinguaB != null : "fx:id=\"sceltaLinguaB\" was not injected: check your FXML file 'Scene.fxml'.";
		assert areaTesto1 != null : "fx:id=\"areaTesto1\" was not injected: check your FXML file 'Scene.fxml'.";
		assert butt1 != null : "fx:id=\"butt1\" was not injected: check your FXML file 'Scene.fxml'.";
		assert labelParoleSbagliate != null : "fx:id=\"labelParoleSbagliate\" was not injected: check your FXML file 'Scene.fxml'.";
		assert spazioFinale != null : "fx:id=\"spazioFinale\" was not injected: check your FXML file 'Scene.fxml'.";
		assert labelFondoPagina != null : "fx:id=\"labelFondoPagina\" was not injected: check your FXML file 'Scene.fxml'.";
		assert clearAllB != null : "fx:id=\"clearAllB\" was not injected: check your FXML file 'Scene.fxml'.";

		sceltaLinguaB.setValue("English");
		sceltaLinguaB.setItems(lingue);
		spazioFinale.setText("Welcome!");

	}

	public void setDizionario(Dictionary dizionario) {
		this.dizionario = dizionario;
		dizionario.loadDictionary("src/main/resources/English");
	}
}