package com.mycompany.VigenereCipher;

import java.util.Arrays;
import java.util.HashSet;

import util.FileResource;

public class Tester {
	String dataSource = "VigenereTestData/";
	
	public void testCaesarCipher() {
		CaesarCipher cc = new CaesarCipher(1);
		FileResource fr = new FileResource(dataSource + "titus-small.txt");
		String output = fr.asString();
		String encrypted = cc.encrypt(output);
		System.out.println(encrypted);
		System.out.println(cc.decrypt(encrypted));
		
		System.out.println(cc.encryptLetter(output.charAt(0)));
		System.out.println(cc.decryptLetter(encrypted.charAt(0)));
	}
	
	public void testCaesarCracker() {
		CaesarCracker cc = new CaesarCracker();
		FileResource fr = new FileResource(dataSource + "titus-small_key5.txt");
		String output = fr.asString();
		String decrypted = cc.decrypt(output);
		System.out.println(decrypted);
		
		cc = new CaesarCracker('a');
		fr = new FileResource(dataSource + "oslusiadas_key17.txt");
		output = fr.asString();
		decrypted = cc.decrypt(output);
		System.out.println(decrypted);
	}
	
	public void testVigenereCipher() {
		VigenereCipher vc = new VigenereCipher(new int[] {17, 14, 12, 4});
		FileResource fr = new FileResource(dataSource + "titus-small.txt");
		String output = fr.asString();
		String encrypted = vc.encrypt(output);
		System.out.println(encrypted);
		System.out.println(vc.decrypt(encrypted));
	}
	
	public void testVigenereBreaker() {
		VigenereBreaker vb = new VigenereBreaker();

		vb.breakVigenere();
	}
}
