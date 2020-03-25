package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private ObservableList<String> list=FXCollections.observableArrayList();
	private Dictionary dictionary;
	 

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> choice;
   

    @FXML
    private TextArea txtInsert;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtResult;

    @FXML
    private Label lblErrror;

    @FXML
    private Button btnClear;

    @FXML
    private Label lblTime;

    @FXML
    void doClearText(ActionEvent event) {
    	txtResult.setText("");
    	txtInsert.setText("");
    }

    @FXML
    void doSpelleCheck(ActionEvent event) {
    	long start=System.nanoTime();
    	long end;
    	String testo=txtInsert.getText();
    	List<String> listTesto=new ArrayList<String>();
    	if(testo==null)
    		return;
    	testo.replaceAll("[.,\\/#!$%\\*;:{}=\\-_'()\\[\\]\"]", "");
    	dictionary.loadDictionary(choice.getValue());
    	String[] arrayTesto=testo.split(" ");
    	for(String s:arrayTesto) {
    		listTesto.add(s);
    	}
    	List<RichWord> risultato=new ArrayList<RichWord>();
    	risultato.addAll(dictionary.spellCheckText(listTesto));
    	for(RichWord r:risultato) {
    		txtResult.appendText(r+"\n");
    	}
    	lblErrror.setText("The text contains "+risultato.size()+" errors");
    	end=System.nanoTime();
    	lblTime.setText("Spellcheck completed in "+(end-start)+" seconds");
    }

    @FXML
    void initialize() {
        assert choice != null : "fx:id=\"choice\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInsert != null : "fx:id=\"txtInsert\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblErrror != null : "fx:id=\"lblErrror\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'Scene.fxml'.";
        list.addAll("English", "Italian");
        choice.setItems(list);
		choice.setValue("Italian");

    }
    
    public void setDictionary(Dictionary d) {
    	this.dictionary=d;
    }
}



