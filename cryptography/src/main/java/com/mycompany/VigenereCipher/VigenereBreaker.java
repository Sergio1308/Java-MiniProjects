package com.mycompany.VigenereCipher;

import java.io.File;
import java.util.*;
import util.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
    	StringBuilder result = new StringBuilder();
    	for (int i = whichSlice; i < message.length(); i += totalSlices) {
    		result.append(message.charAt(whichSlice));
    		whichSlice += totalSlices;
    	}
        return result.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < key.length; i++) {
        	String sliced = sliceString(encrypted, i, klength);
        	key[i] = cc.getKey(sliced);
        }
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
    	HashSet<String> words = new HashSet<String>();
    	for (String word: fr.lines()) {
    		word = word.toLowerCase();
    		words.add(word);
    	}
    	return words;
    }
    
    public int countWords(String message, HashSet<String> dict) {
    	int count = 0;
    	for (String word: message.split("\\W")) {
    		if (dict.contains(word.toLowerCase())) {
    			count++;
    		}
    	}
    	return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dict) {
    	int maxCount = 0;
    	String result = "";
    	int[] foundKey = null;
    	char c = mostCommonCharIn(dict);
    	System.out.println("Found most Common char: " + c);
    	
    	for (int i = 1; i <= 100; i++) {
    		int[] key = tryKeyLength(encrypted, i, c);
    		VigenereCipher cc = new VigenereCipher(key);
    		String decrypted = cc.decrypt(encrypted);
    		int count = countWords(decrypted, dict);
    		if (count > maxCount) {
    			maxCount = count;
    			result = decrypted;
    			foundKey = key;
    		}
    	}
    	System.out.println(Arrays.toString(foundKey));
    	String alphabet = "abcdefghijklmnopqrstuvwxyz";
    	for (int elem: foundKey) {
    		System.out.print(alphabet.charAt(elem));
    	}
    	System.out.println();
    	return result;
    }
    
    public Character mostCommonCharIn(HashSet<String> dict) {
    	String alphabet = "abcdefghijklmnopqrstuvwxyz";
    	HashMap<Character, Integer> charsCounts = new HashMap<Character, Integer>();
    	Character mostCommonChar = null;
    	
    	for (String word: dict) {
    		for (Character c: word.toCharArray()) {
    			if (alphabet.indexOf(c) == -1) {
    				continue;
    			}
    			if (!charsCounts.containsKey(c)) {
    				charsCounts.put(c, 1);
    			} else {
    				charsCounts.put(c, charsCounts.get(c) + 1);
    			}
    		}
    	}
    	int maxCount = Collections.max(charsCounts.values());
    	for (Character c: charsCounts.keySet()) {
    		if (charsCounts.get(c).equals(maxCount)) {
    			mostCommonChar = c;
    			break;
    		}
    	}
    	return mostCommonChar;
    }
    
    public void breakForAllLangs(String encrypted, HashMap <String, HashSet<String>> languages) {
    	int maxCount = 0;
    	String result = "";
    	String currLang = "";
    	for (String lang: languages.keySet()) {
    		HashSet<String> dict = languages.get(lang);
    		String decrypted = breakForLanguage(encrypted, dict);
    		int currentCount = countWords(decrypted, dict);
    		if (currentCount > maxCount) {
    			maxCount = currentCount;
    			currLang = lang;
    			result = decrypted;
    		}
    		System.out.println("Current lang: " + lang + ". Current count: " + currentCount + "\n");
    	}
    	System.out.println("Message in "+ currLang +":");
    	System.out.println(result + "\n" + maxCount + "\t" + currLang);
    }
    
    public String[] fillDictArray() {
    	File[] listOfFiles = new File("./src/main/resources/dictionaries/").listFiles();
    	String[] dictFiles = new String[listOfFiles.length];
    	for (int i = 0; i < listOfFiles.length; i++) {
    		dictFiles[i] = listOfFiles[i].getName();
    	}
    	return dictFiles;
    }

    public void breakVigenere () {
    	// VigenereTestData/athens_keyflute.txt
        FileResource fr = new FileResource("./src/test/resources/VigenereTestData/messages/secretmessage4.txt");
        String output = fr.asString();
    	HashMap<String, HashSet<String>> dicts = new HashMap<String, HashSet<String>>();
    	String[] dictsNames = fillDictArray();
    	
    	for (String lang: dictsNames) {
    		dicts.put(lang, readDictionary(new FileResource("dictionaries/" + lang)));
    	}
    	breakForAllLangs(output, dicts);
    }
    
}
