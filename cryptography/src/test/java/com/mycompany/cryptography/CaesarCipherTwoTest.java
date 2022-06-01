package com.mycompany.cryptography;

import static org.junit.Assert.*;

import org.junit.Test;

import util.FileResource;

public class CaesarCipherTwoTest {

	@Test
	public void simpleTests() {
		String input1 = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
		String input2 = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
		System.out.println("Called breakCaesarCipher():\t" + breakCaesarCipher(input1));
		System.out.println("Called breakCaesarCipher():\t" + breakCaesarCipher(input2));
		
		assertEquals(breakCaesarCipher(input1), "The name of the Java Mascot is Duke. Woeoeee!");
		assertEquals(breakCaesarCipher(input2), "Eren and Emily have evil eerie green ears");
		
		// Testing breakCaesarCipher() with mysteryTwoKeysPractice.txt
		FileResource fr = new FileResource("data/mysteryTwoKeysPractice.txt");
		String text = fr.asString();
		CaesarCipherTwo cc = new CaesarCipherTwo(17, 4);	
		System.out.println("Called breakCaesarCipher():\n" + breakCaesarCipher(text));
		
		assertEquals(cc.decrypt(text), breakCaesarCipher(text));
	}
	
	private String breakCaesarCipher(String input) {
		String firstString = halfOfString(input, 0);
		String secondString = halfOfString(input, 1);
	
		int dkey1 = getKey(firstString);
		int dkey2 = getKey(secondString);
		
		CaesarCipherTwo cc = new CaesarCipherTwo(26-dkey1, 26-dkey2);
		System.out.println("\nFirst key: " + (dkey1) + "\tSecond key: " + (dkey2));
		return cc.encrypt(input);
	}
	
	private int getKey(String s) {
		int[] freqs = countLetters(s);
		int maxDex = maxIndex(freqs);
		int dkey = maxDex - 4;
		if (maxDex < 4) {
			dkey = 26 - (4 - maxDex);
		}
		return dkey;
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
	
	private String halfOfString(String message, int start) {
		StringBuilder result = new StringBuilder();
		for (int i = start; i < message.length(); i += 2) {
			result.append(message.charAt(i));
		}
		return result.toString();
	}
}
