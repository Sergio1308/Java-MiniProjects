package com.mycompany.VigenereCipher;

import util.FileResource;

public class App {
	
	static String dataSource = "src/test/resources/VigenereTestData/";
	
	public static void main(String[] args) {
		// dataSource + "messages/secretmessage4.txt" or "athens_keyflute.txt" like test example
		FileResource fr = new FileResource();
        VigenereBreaker vb = new VigenereBreaker();
		vb.breakVigenere(fr);
    }
}
