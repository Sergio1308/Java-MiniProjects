package com.mycompany.cryptography;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mycompany.CaesarCipher.CaesarCipher;

import util.FileResource;

public class CaesarCipherTest {
	
	CaesarCipher cc;
	FileResource fr;
	String currentResult;
	
	@Before
	public void setUp() {
		fr = new FileResource("data/wordsLotsOfEs.txt");  // testing with a file
		cc = new CaesarCipher(18);
		currentResult = fr.asString();
	}
	
	@Test
	public void encryptionTest() {
		String encrypted = cc.encrypt(currentResult);
		assertTrue(encrypted.contains("Bmkl s lwkl kljafy oalz dglk gx wwwwwwwwwwwwwwwwwk"));
		cc = new CaesarCipher(15);
		encrypted = cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?");
		assertTrue(encrypted.contains("Rpc ndj xbpvxct axut LXIWDJI iwt xcitgcti PCS rdbejitgh xc ndjg edrzti?"));
	}

	@Test
	public void decryptionTest() {
		String decrypted = cc.decrypt("Bmkl s lwkl kljafy oalz dglk gx wwwwwwwwwwwwwwwwwk");
		assertTrue(decrypted.contains("Just a test string with lots of eeeeeeeeeeeeeeeees"));
		cc = new CaesarCipher(15);
		decrypted = cc.decrypt("Rpc ndj xbpvxct axut LXIWDJI iwt xcitgcti PCS rdbejitgh xc ndjg edrzti?");
		assertTrue(decrypted.contains("Can you imagine life WITHOUT the internet AND computers in your pocket?"));
	}
	
	@Test
	public void breakCaesarCipherTest() {
		String breakCipher = breakCaesarCipher(cc.encrypt(currentResult));
		assertEquals(currentResult, breakCipher);
		
		System.out.println("Called breakCaesarCipher():\n" + breakCipher);
	}
	
	@Test
	public void simpleCaesarCipherTest() {
		String encrypted = cc.encrypt(currentResult);
		String decrypted = cc.decrypt(encrypted);
		assertEquals(currentResult, decrypted);
		
		System.out.println("Encrypted string:\n" + encrypted + "\nDecrypted string:\n" + decrypted);
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
