package com.mycompany.cryptography;

public class WordPlay {
	
	public boolean isVowel(char ch) {
		String vowels = "aeiouAEIOU";
		return vowels.indexOf(ch) != -1;
	}
	
	public String replaceVowels(String phrase, char ch) {
		StringBuilder replacedPhrase = new StringBuilder(phrase);
		for (int i = 0; i < replacedPhrase.length(); i++) {
			char currentChar = replacedPhrase.charAt(i);
			if (isVowel(currentChar)) {
				replacedPhrase.setCharAt(i, ch);
			}
		}
		return replacedPhrase.toString();
	}
	
	public String emphasize(String phrase, char ch) {
		StringBuilder emphasizedPhrase = new StringBuilder(phrase);
		int startIndex = 0;
		
		for (int i = 0; i < emphasizedPhrase.length(); i++) {
			int currentIndex = emphasizedPhrase.toString().indexOf(ch, startIndex);
			
			if (currentIndex % 2 == 0) {
				emphasizedPhrase.setCharAt(currentIndex, '*');
			} else if (currentIndex != -1) {
				emphasizedPhrase.setCharAt(currentIndex, '+');
			}
			startIndex = emphasizedPhrase.toString().indexOf(ch, startIndex) + 1;
		}
		return emphasizedPhrase.toString();
	}
}
