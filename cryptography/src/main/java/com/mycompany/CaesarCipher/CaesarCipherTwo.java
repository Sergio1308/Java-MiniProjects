package com.mycompany.CaesarCipher;

public class CaesarCipherTwo {
	
	private String alphabet;
	private String shiftedAlphabet1;
	private String shiftedAlphabet2;
	private int mainKey1;
	private int mainKey2;
	
	public CaesarCipherTwo(int key1, int key2) {
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
		shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
		mainKey1 = key1;
		mainKey2 = key2;
	}
	
	public String encrypt(String input) {
		StringBuilder encrypted = new StringBuilder(input);
    	
    	for (int i = 0; i < encrypted.length(); i++) {
            char currentChar = encrypted.charAt(i);
            boolean isLower = Character.isLowerCase(currentChar);
            int currentIndex = isLower ? alphabet.indexOf(
            		Character.toUpperCase(currentChar)) : alphabet.indexOf(currentChar);
            
            if (currentIndex != -1) {
            	if (i % 2 == 0) {  // encrypt every other character with key1, starting with the first character
            		char newChar = isLower ? Character.toLowerCase(
            				shiftedAlphabet1.charAt(currentIndex)) : shiftedAlphabet1.charAt(currentIndex);
            		encrypted.setCharAt(i, newChar);
            	} else {  // otherwise, encrypt every second character with key2
            		char newChar = isLower ? Character.toLowerCase(
            				shiftedAlphabet2.charAt(currentIndex)) : shiftedAlphabet2.charAt(currentIndex);
            		encrypted.setCharAt(i, newChar);
            	}
            }
        }
        return encrypted.toString();
	}
	
	public String decrypt(String input) {
		CaesarCipherTwo cc = new CaesarCipherTwo(26-mainKey1, 26-mainKey2);
    	return cc.encrypt(input);
	}
}
