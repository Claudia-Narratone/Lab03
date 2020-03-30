package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

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
	private List<String> inputTextList;
	 

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
    	txtResult.clear();
    	txtInsert.clear();
    	lblErrror.setText("Numbers of errors: ");
    }

    @FXML
    void doSpelleCheck(ActionEvent event) {
    	
    	long end;
    	
    	txtResult.clear();
    	
    	inputTextList = new LinkedList<String>();
    	
    	String testo=txtInsert.getText();
    	
    	if(testo.isEmpty()) {
    		txtResult.setText("Inserire un testo da correggere!");
    		return;
    	}
    	
    	List<String> listTesto=new ArrayList<String>();
    	
    	testo=testo.replaceAll("[.,\\/#!$%\\*;:{}=\\-_'()\\[\\]\"]", "");
    	testo = testo.replaceAll("\n", " ");
    	
    	dictionary.loadDictionary(choice.getValue());
    	
    	StringTokenizer st = new StringTokenizer(testo, " ");
		while (st.hasMoreTokens()) {
			inputTextList.add(st.nextToken());
		}
    	
		long start=System.nanoTime();
		
    	List<RichWord> risultato=new ArrayList<RichWord>();
    	risultato.addAll(dictionary.spellCheckText(listTesto));
    	
    	int numErrori=0;
    	StringBuilder richText = new StringBuilder();
    	for(RichWord r:risultato) {
    		if (!r.isCorrect()) {
				numErrori++;
				richText.append(r.getWord() + "\n");
			}
    	}
    	
    	txtResult.setText(richText.toString());
    	lblErrror.setText("The text contains "+numErrori+" errors");
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
    
    public void setModel(Dictionary d) {
    	this.dictionary=d;
    }
}



