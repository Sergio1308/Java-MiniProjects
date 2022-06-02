package com.mycompany.cryptography;

import util.FileResource;

/**
 * Main class to run the program
 * 
 */
public class App
{
    public static void main(String[] args)
    {
		FileResource fr = new FileResource();
		String text = fr.asString();
		
		CaesarCipherTwo cc1 = new CaesarCipherTwo(14, 24);
		String encrypted = cc1.encrypt(text);
		String decrypted = cc1.decrypt(encrypted);
		System.out.println("Encrypted string:\n" + encrypted + "Decrypted string:\n" + decrypted);
    }
}
