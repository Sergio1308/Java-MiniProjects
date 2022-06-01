package com.mycompany.cryptography;

import static org.junit.Assert.*;

import org.junit.Test;

import util.FileResource;

public class CaesarCipherTest {

	@Test
	public void simpleTests() {
		FileResource fr = new FileResource("data/wordsLotsOfEs.txt");
		String text = fr.asString();
		
		CaesarCipher cc1 = new CaesarCipher(18);
		String encrypted = cc1.encrypt(text);
		String decrypted = cc1.decrypt(encrypted);
		System.out.println("Encrypted string:\n" + encrypted + "\nDecrypted string:\n" + decrypted);
		assertEquals(text, decrypted);
		
		String breakCipher = breakCaesarCipher(encrypted);
		assertEquals(text, breakCipher);
		System.out.println("Called breakCaesarCipher():\n" + breakCipher);
	}
	
	private String breakCaesarCipher(String input) {
		int[] freqs = countLetters(input);
		int maxDex = maxIndex(freqs);
		
		int dkey = maxDex - 4;
		if (dkey < 0) {
			dkey = 26 - (4 - maxDex);
		}
		CaesarCipher cc = new CaesarCipher(dkey);
		return cc.decrypt(input);
	}
	
	private int[] countLetters(String input) {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int[] charsCounter = new int[26];
		for (int i = 0; i < input.length(); i++) {
			char currChar = Character.toLowerCase(input.charAt(i));
			int currIndex = alphabet.toLowerCase().indexOf(currChar);
			if (currIndex != -1) {
				charsCounter[currIndex] += 1;
				}
		}
		return charsCounter;
	}
	
	private int maxIndex(int[] values) {
		int maxDex = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] > values[maxDex]) {
				maxDex = i;
			}
		}
		return maxDex;
	}
}
