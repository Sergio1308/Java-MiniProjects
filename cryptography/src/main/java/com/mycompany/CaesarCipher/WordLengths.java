package com.mycompany.CaesarCipher;

import util.*;

public class WordLengths {
	
	public static void main(String[] args) {
		WordLengths wl = new WordLengths();
		wl.testCountWordLengths();
	}
	
	public void countWordLengths(FileResource res, int[] counts) {
		int lastIndex = counts.length - 1;
		for(String s : res.words()) {
			int currentLength = s.length();
			
			if (!Character.isLetter(s.charAt(0)) && Character.isLetter(s.charAt(s.length() - 1))) {
				currentLength -= 1;
			} 
			
			if (currentLength > lastIndex) {
				counts[lastIndex]++;
			} else if (currentLength > 0) {
				counts[currentLength]++;
			}
		}
		System.out.println("Index position of the largest elem: " + indexOfMax(counts));
	}
	
	public int indexOfMax(int[] values) {
		// return index position (if index = 2, then index position = 3)
		int max = 0;
        int position = 0;
        for (int i = 0; i < values.length;i++) {
        	if (values[i] > max) {
        		max = values[i];
                position = i;
        	}
        }
       return position;
	}
	
	public void testCountWordLengths() {
		FileResource fr = new FileResource();
		countWordLengths(fr, new int[31]);
	}
}
