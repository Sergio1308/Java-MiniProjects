package com.mycompany.CaesarCipher;

public class CaesarCipher {
	
	private String alphabet;
	private String shiftedAlphabet;
	private int mainKey;
	
	public CaesarCipher(int key) {
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
		mainKey = key;
	}

    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);

        for (int i = 0; i < encrypted.length(); i++) {
            char currentChar = encrypted.charAt(i);
            boolean isLower = Character.isLowerCase(currentChar);
            int currentIndex = isLower ? alphabet.indexOf(
            		Character.toUpperCase(currentChar)) : alphabet.indexOf(currentChar);

            if (currentIndex != -1) {
                char newChar = isLower ? Character.toLowerCase(
                		shiftedAlphabet.charAt(currentIndex)) : shiftedAlphabet.charAt(currentIndex);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input) {
    	CaesarCipher cc = new CaesarCipher(26-mainKey);
    	return cc.encrypt(input);
    }
    
    public void textFingerPrint(String s) {
    	String alpha = "abcdefghijklmopqrstuvwxyz";
    	int[] counters = new int[26];
    	for(int i = 0; i < s.length(); i++ ) {
    		char ch = s.charAt(i);
    		int index = alpha.indexOf(Character.toLowerCase(ch));
    		if (index != -1) {
    			counters[index] += 1;
    		}
    	}
    	for (int i = 0; i < counters.length-1; i++) {
    		System.out.println(alpha.charAt(i) + "\t" + counters[i]);
    	}
    }
}
