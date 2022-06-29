package com.mycompany.RandomStories;

import java.util.HashMap;

import util.FileResource;

public class WordFrequenciesMap {
	
	private HashMap<String, Integer> myWords;
	
	private static String dataSourceDir = "WordsDataTest/";
	
	public WordFrequenciesMap() {
		myWords = new HashMap<String, Integer>();
	}
	
	public void findUnique(String fileName) {
		myWords.clear();
		FileResource fr = new FileResource(dataSourceDir + fileName);
		for (String s: fr.words()) {
			s = s.toLowerCase();
			if (!myWords.containsKey(s)) {
				myWords.put(s, 1);
			}
			else {
				myWords.put(s, myWords.get(s) + 1);
			}
		}
	}
	
	public HashMap<String, Integer> getMyWords() {
		return myWords;
	}
	
	public String getValueWithMaxNum() {
		int maxNumber = 0;
		String valueWithMaxNum = "";
		for (String s: myWords.keySet()) {
			if (myWords.get(s) > maxNumber) {
				maxNumber = myWords.get(s);
				valueWithMaxNum = s;
			}
		}
		return valueWithMaxNum;
	}
}
