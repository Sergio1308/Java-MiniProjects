package com.mycompany.RandomStories;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import util.DirectoryResource;
import util.FileResource;

public class WordsInFiles {
	
	private HashMap<String, ArrayList<String>> wordsInFiles;
	
	protected String dataSourceDir = "./src/test/resources/WordsDataTest/WordsInFilesDataTest";
	
	public WordsInFiles() {
		wordsInFiles = new HashMap<String, ArrayList<String>>();
	}
	
	protected void addWodsFromFile(File f) {
		String fileName = f.getName();
		FileResource fr = new FileResource(f);
		
		for (String word: fr.words()) {
			if (!wordsInFiles.containsKey(word)) {
				ArrayList<String> filesList = new ArrayList<String>();
				filesList.add(fileName);
				wordsInFiles.put(word, filesList);
			} else {
				ArrayList<String> filesList = wordsInFiles.get(word);
				if (!filesList.contains(fileName)) {
					filesList.add(fileName);
					wordsInFiles.put(word, filesList);
				}
			}
		}
	}
	
	public void buildWordFileMap() {
		wordsInFiles.clear();
		DirectoryResource dr = new DirectoryResource();
		for (File f: dr.selectedFiles()) {
			addWodsFromFile(f);
		}
	}
	
	public HashMap<String, ArrayList<String>> getWordsInFilesMap() {
		return wordsInFiles;
	}
	
	public int maxNumber() {
		int maxNumber = 0;
		for (String s: wordsInFiles.keySet()) {
			if (wordsInFiles.get(s).size() > maxNumber) {
				maxNumber = wordsInFiles.get(s).size();
			}
		}
		return maxNumber;
	}	
	
	public ArrayList<String> wordsInNumFiles(int number) {
		ArrayList<String> wordsNum = new ArrayList<String>();
		for (String s: wordsInFiles.keySet()) {
			if (wordsInFiles.get(s).size() == number) {
				wordsNum.add(s);
			}
		}
		return wordsNum;
	}
	
	public ArrayList<String> getFilesIn(String word) {
		return wordsInFiles.get(word);
	}
	
	public void printFilesIn(String word) {
		try {
			System.out.println("Word '" + word + "' appears in: " + String.join(", ", wordsInFiles.get(word)));
		} 
		catch (NullPointerException e) {
			System.out.println("'" + word + "' not found.");
		}
	}
}
