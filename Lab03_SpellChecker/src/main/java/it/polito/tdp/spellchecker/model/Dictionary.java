package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {
	
	private List<String> dictionary;
	private String language;
	
	public Dictionary() {
		
	}

	public boolean loadDictionary(String language) {
		
		if(dictionary!=null && this.language.equals(language))
			return true;
		

		try {
			FileReader fr= new FileReader("src/main/resources/"+language+".txt");
			BufferedReader br=new BufferedReader(fr);
			String word;
			while((word= br.readLine())!=null) {
				dictionary.add(word.toLowerCase());
			}
			
			Collections.sort(dictionary);
			
			br.close();
			
			return true;
			
		}catch(IOException e) {
			System.out.println("Errore nella lettura del file");
			return false;
		}
		
	}

	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		RichWord word;
		List<RichWord> parole=new ArrayList<RichWord>();
		for(String s:inputTextList) {
			word=new RichWord(s);
			if(dictionary.contains(s.toLowerCase())) {
				word.setCorrect(true);
			}else {
				word.setCorrect(false);
			}	
			parole.add(word);
		}
		return parole;
	}
}
