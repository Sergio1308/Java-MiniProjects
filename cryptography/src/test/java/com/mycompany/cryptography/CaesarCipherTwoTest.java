package com.mycompany.cryptography;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import util.FileResource;

public class CaesarCipherTwoTest {
	
	CaesarCipherTwo cc;
	FileResource fr;
	String currentResult;
	
	@Before
	public void setUp() {
		fr = new FileResource("data/mysteryTwoKeysPractice.txt");  // testing with a file
		cc = new CaesarCipherTwo(17, 4);
		currentResult = fr.asString();
	}
	
	@Test
	public void encryptionTest() {
		cc = new CaesarCipherTwo(21, 8);
		String encrypted = cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?");
		assertEquals("Xii twp duvodvz gqam EDBCWPB bcm qibzzimo VVY xwhxpbzzn dv gjcm kwxszb?", encrypted);
	}

	@Test
	public void decryptionTest() {
		cc = new CaesarCipherTwo(14, 24);
		String decrypted = cc.decrypt("Hfs cpwewloj loks cd Hoto kyg Cyy.");
		assertEquals("The original name of Java was Oak.", decrypted);
	}
	
	@Test
	public void breakCaesarCipherTest() {
		String breakedCipher = breakCaesarCipher(currentResult);
		System.out.println("Called breakCaesarCipher():\n" + breakedCipher);
		assertEquals(breakedCipher, cc.decrypt(currentResult));
		
		String result1 = breakCaesarCipher("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!");
		System.out.println("Called breakCaesarCipher():\t" + result1);
		
		String result2 = breakCaesarCipher("Akag tjw Xibhr awoa aoee xakex znxag xwko");
		System.out.println("Called breakCaesarCipher():\t" + result2);
		
		assertEquals(result1, "The name of the Java Mascot is Duke. Woeoeee!");
		assertEquals(result2, "Eren and Emily have evil eerie green ears");
	}
	
	@Test
	public void getKeyTest() {
		assertEquals(22, getKey("Akag tjw Xibhr awoa aoee xakex znxag xwko"));
		assertEquals(10, getKey("Hfs cpwewloj loks cd Hoto kyg Cyy."));
		assertEquals(7, getKey("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!"));
	}
	
	@Test
	public void countLettersTest() {
		assertArrayEquals(new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
				countLetters("abcdefghijklmnopqrstuvwxyz"));
		assertArrayEquals(new int[] {7, 1, 0, 0, 3, 0, 2, 1, 1, 1, 3, 0, 0, 1, 3, 0, 0, 1, 0, 1, 0, 0, 3, 5, 0, 1}, 
				countLetters("Akag tjw Xibhr awoa aoee xakex znxag xwko"));
	}
	
	@Test
	public void getMaxIndexTest() {
		assertEquals(2, maxIndex(new int[] {1, 2, 3}));
		assertEquals(0, maxIndex(new int[] {1, 1, 1, 1}));
		assertEquals(1, maxIndex(new int[] {-5, 0, -1, -10}));
		assertEquals(9, maxIndex(new int[] {6, 1, 0, 0, 3, 0, 2, 1, 1, 7, 3, 0, 0, 1, 3, 0, 0, 1, 0, 1, 0, 0, 3, 5, 0, 1}));
	}
	
	@Test
	public void getHalfOfStringTest() {
		assertEquals("Ts tig", halfOfString("Test String", 0));
		assertEquals("etSrn", halfOfString("Test String", 1));
		assertEquals("o hi O", halfOfString("Aeouiok Xhuif !Ok", 5));
		assertEquals("Successful output!!", halfOfString("aSbuccdceefsgshfiuglq pozuxtcpvubtn!!!", 1));
		
		String firstHalf = halfOfString("Can you imagine life WITHOUT the internet AND computers in your pocket?", 0);
		String secondHalf = halfOfString("Can you imagine life WITHOUT the internet AND computers in your pocket?", 2);
		assertTrue(firstHalf.contains(secondHalf));
	}	
	
	@Test
	public void simpleCaesarCipherTwoTest() {
		String encrypted = cc.encrypt(currentResult);
		String decrypted = cc.decrypt(encrypted);
		assertEquals(currentResult, decrypted);
		
		System.out.println("Encrypted string:\n" + encrypted + "\nDecrypted string:\n" + decrypted);
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
		int[] charsCounter = new int[alphabet.length()];
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
