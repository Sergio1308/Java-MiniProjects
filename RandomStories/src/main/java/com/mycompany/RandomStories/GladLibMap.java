package com.mycompany.RandomStories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONObject;

import util.FileResource;
import util.URLResource;

public class GladLibMap {
	
	private HashMap<String, ArrayList<String>> wordsMap;
	private HashMap<String, ArrayList<String>> usedWordsMap;
	private ArrayList<String> addedWords;
	
	private Random myRandom;
	
	private int usedWordsCount;

	private static String dictsSourceDir = "./src/main/resources/dictionaries/";
	private static String templateSourceDir = "templates/";
	
	public GladLibMap() {
		wordsMap = new HashMap<String, ArrayList<String>>();
		usedWordsMap = new HashMap<String, ArrayList<String>>();
		initializeFromSource(dictsSourceDir);
		myRandom = new Random();
	}
	
	public GladLibMap(String source) {
		wordsMap = new HashMap<String, ArrayList<String>>();
		usedWordsMap = new HashMap<String, ArrayList<String>>();
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
		LabelsListParser jp = new LabelsListParser();
		JSONObject jsonObj = jp.readJSON();
		
		for (var item: jsonObj.keySet()) {
        	ArrayList<String> list = readIt(dictsSourceDir + jsonObj.get(item));
        	wordsMap.put(item.toString(), list);
        }
		addedWords = new ArrayList<String>();
		usedWordsCount = 0;
	}
	
	private String randomFrom(ArrayList<String> source) {
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if (label.equals("number")) {
			return "" + myRandom.nextInt(50) + 5;
		}
		return randomFrom(wordsMap.get(label));
	}
	
	private String processWord(String w) {
		int first = w.indexOf("<");
		int last = w.indexOf(">", first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0, first);
		String suffix = w.substring(last + 1);
		String sub = getSubstitute(w.substring(first + 1, last));
		
		if (!addedWords.contains(sub)) {
			addedWords.add(sub);
			ArrayList<String> usedWords = new ArrayList<String>();
			usedWords.add(sub);
			usedWordsMap.put(sub, usedWords);
		} else {
			ArrayList<String> usedWords = usedWordsMap.get(sub);
			while (addedWords.contains(sub)) {
				System.out.print("Repeated word replaced: '" + sub + "' to '");
				sub = getSubstitute(w.substring(first + 1, last));
				System.out.println(sub + "'.");
				usedWordsCount++;
			}
			addedWords.add(sub);
			usedWordsMap.put(sub, usedWords);
		}
		return prefix + sub + suffix;
	}
	
	private void printOut(String s, int lineWidth) {
		int charsWritten = 0;
		System.out.println();
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source) {
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source) {
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public int totalWordsInMap() {
		int totalWords = 0;
		for (String s: wordsMap.keySet()) {
			totalWords += wordsMap.get(s).size();
		}
		return totalWords;
	}
	
	public int totalWordsConsidered() {
		int totalWords = 0;
		for (String s: usedWordsMap.keySet()) {
			totalWords += usedWordsMap.get(s).size();
		}
		return totalWords;
	}
	
	public void makeStory(String fileName) {
		addedWords.clear();
		String story = fromTemplate(templateSourceDir + fileName);
		printOut(story, 60);
		System.out.println("\n\nReplaced words: " + usedWordsCount);
		System.out.println("\nTotal words in map: " + totalWordsInMap());
		System.out.println("\nTotal considered words: " + totalWordsConsidered());
	}
}
