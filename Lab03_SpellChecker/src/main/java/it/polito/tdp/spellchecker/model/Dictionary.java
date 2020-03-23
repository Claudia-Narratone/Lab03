package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {
	
	private Set<String> dictionary;
	
	public Dictionary() {
		dictionary=new HashSet<String>();
	}

	public void loadDictionary(String language) {
		if(language.toLowerCase().equals("english")) {
			try {
				FileReader fr= new FileReader("English.txt");
					BufferedReader br=new BufferedReader(fr);
				String word;
				while((word= br.readLine())!=null) {
					dictionary.add(word);
				}
				br.close();
			}catch(IOException e) {
				System.out.println("Errore nella lettura del file");
			}
		}
		if(language.toLowerCase().equals("italian")) {
			try {
				FileReader fr= new FileReader("Italian.txt");
					BufferedReader br=new BufferedReader(fr);
				String word;
				while((word= br.readLine())!=null) {
					dictionary.add(word);
				}
				br.close();
			}catch(IOException e) {
				System.out.println("Errore nella lettura del file");
			}
		}
		
	}

	public Set<String> getDictionary() {
		return dictionary;
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		RichWord word;
		List<RichWord> paroleErrate=new ArrayList<RichWord>();
		for(String s:inputTextList) {
			if(!dictionary.contains(s)) {
				word=new RichWord(s);
				paroleErrate.add(word);
			}	
		}
		return paroleErrate;
	}
}
