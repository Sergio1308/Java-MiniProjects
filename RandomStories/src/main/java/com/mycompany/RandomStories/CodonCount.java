package com.mycompany.RandomStories;

import java.util.HashMap;

public class CodonCount {
	
	private HashMap<String, Integer> myCodons;
	String currentCommonCodon;
	
	public CodonCount() {
		myCodons = new HashMap<String, Integer>();
	}
	
	public void buildCodonMap(int start, String dna) {
		myCodons.clear();
		dna = dna.trim(); // remove possible whitespace, received from reading FileResource
		while (start + 3 <= dna.length()) {
			String currentCodon = dna.substring(start, start+3);
			if (!myCodons.containsKey(currentCodon)) {
				myCodons.put(currentCodon, 1);
			} else {
				myCodons.put(currentCodon, myCodons.get(currentCodon) + 1);
			}
			start += 3;
		}
		
		show();
		currentCommonCodon = getMostCommonCoddon();
		System.out.printf("The most common codon is %s with count %d.\n\n", currentCommonCodon, myCodons.get(currentCommonCodon));
	}
	
	public String getMostCommonCoddon() {
		if (myCodons.isEmpty()) {
			return "No codon map built.\n";
		}
		int maxNumber = 0;
		String valueWithMaxNum = "";
		for (String s: myCodons.keySet()) {
			if (myCodons.get(s) > maxNumber) {
				maxNumber = myCodons.get(s);
				valueWithMaxNum = s;
			}
		}
		return valueWithMaxNum;
	}
	
	public void show() {
		for (String s: myCodons.keySet()) {
			System.out.println(s + "\t" + myCodons.get(s));
		}
	}
	
	public void printCodonCounts(int start, int end) {
		System.out.printf("All the codons with count between %s and %s:\n", start, end);
		for (String s: myCodons.keySet()) {
			int currentValue = myCodons.get(s);
			if (currentValue >= start && currentValue <= end) {
				System.out.println(s + "\t" + myCodons.get(s));
			}
		}
	}
}
