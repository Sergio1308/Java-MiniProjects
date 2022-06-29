package com.mycompany.RandomStories;

import java.util.ArrayList;

import util.FileResource;

public class CharactersInPlay {
	
	private ArrayList<String> nameOfChars;
	private ArrayList<Integer> countChars;
	
	private static String dataSourceDir = "./src/test/resources/WordsDataTest/";
	
	public CharactersInPlay() {
		nameOfChars = new ArrayList<String>();
		countChars = new ArrayList<Integer>();
	}
	
	public ArrayList<String> getNameOfChars() {
		return nameOfChars;
	}
	
	public ArrayList<Integer> getcountChars() {
		return countChars;
	}
	
	protected void update(String person) {
		person = person.trim();
		int index = nameOfChars.indexOf(person);
		if (index == -1) {
			nameOfChars.add(person);
			countChars.add(1);
		}
		else {
			int value = countChars.get(index);
			countChars.set(index, value + 1);
		}
	}
	
	protected void findAllCharacters(String fileName) {
		nameOfChars.clear();
		countChars.clear();
		FileResource fr = new FileResource(dataSourceDir + fileName);
		
		for (String line: fr.lines()) {
			if (line.indexOf('.') == -1) {
				continue;
			}
			String firstPeriod = line.substring(0, line.indexOf('.'));
			if (firstPeriod.equals(firstPeriod.toUpperCase())) {
				update(firstPeriod);
			}
		}
	}
	
	protected void charactersWithNumParts(int num1, int num2) {
		System.out.println("Names of all characters that have exactly number speaking parts:");
		for (int i = 0; i < nameOfChars.size(); i++) {
			int currentValue = countChars.get(i);
			if (currentValue >= num1 && currentValue <= num2) {
				System.out.println(nameOfChars.get(i));
			}
		}
	}
	
	protected int findIndexOfMax() {
		int maxNumber = 0;
		int maxIndex = 0;
		for (int i = 0; i < countChars.size(); i++) {
			if (countChars.get(i) > maxNumber) {
				maxNumber = countChars.get(i);
				maxIndex = i;
			}
		}
		return maxIndex;
	}
}
