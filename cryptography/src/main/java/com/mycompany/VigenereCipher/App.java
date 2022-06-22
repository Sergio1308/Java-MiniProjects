package com.mycompany.VigenereCipher;

import util.FileResource;

public class App {
	public static void main(String[] args)
    {
//		FileResource fr = new FileResource();
//		String text = fr.asString();
		Tester t1 = new Tester();
//		t1.testCaesarCipher();
//		t1.testCaesarCracker();
//		t1.testVigenereCipher();
		t1.testVigenereBreaker();
    }
}
