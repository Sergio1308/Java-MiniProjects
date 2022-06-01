package com.mycompany.cryptography;

public class WordPlay {
	
	public static void main(String[] args) {
		WordPlay w = new WordPlay();
		w.testReplaceVowels();
	}
	
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
	
	public void testIsVowel() {
		System.out.println(isVowel('a'));
		System.out.println(isVowel('y'));
		System.out.println(isVowel('e'));
		System.out.println(isVowel('A'));
	}
	
	public void testReplaceVowels() {
		System.out.println(replaceVowels("Hello World EeAyYOkayOKAY#", '*'));
	}
	
	public void testEmphasize() {
		System.out.println(emphasize("dna ctgaaactga", 'a'));
		System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
	}

}
