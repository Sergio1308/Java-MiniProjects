package com.mycompany.VigenereCipher;

import util.FileResource;

public class App {
	
	static String dataSource = "src/test/resources/VigenereTestData/";
	
	public static void main(String[] args) {
        FileResource fr = new FileResource(dataSource + "messages/secretmessage4.txt");  // athens_keyflute.txt
        VigenereBreaker vb = new VigenereBreaker();
		vb.breakVigenere(fr);
    }
}
