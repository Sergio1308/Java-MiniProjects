package com.mycompany.RandomStories;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class WordsInFilesTester {

	HashMap<String, String[]> testsMap;
	WordsInFiles wf;
	int maxVal;
	
	@Before
	public void setUp() {
		testsMap = new HashMap<String, String[]>();
		wf = new WordsInFiles();
		File[] dirList = new File(wf.dataSourceDir).listFiles();
		for (File f: dirList) {
			wf.addWodsFromFile(f);
		}
		maxVal = wf.maxNumber();
	}
	
	@Test
	public void wordsInFilesTest() {
		ArrayList<String> res1 = new ArrayList<>(Arrays.asList("love", "are", "dogs"));
		ArrayList<String> res2 = new ArrayList<>(Arrays.asList("cats", "and"));
		ArrayList<String> res3 = new ArrayList<>(Arrays.asList("silly", "animals", "birds", "cute", "funny"));
		
		assertEquals(3, maxVal);
		
		assertEquals(2, wf.wordsInNumFiles(3).size());
		assertEquals(3, wf.wordsInNumFiles(2).size());
		assertEquals(5, wf.wordsInNumFiles(1).size());

		assertEquals(res2, wf.wordsInNumFiles(3));
		assertEquals(res1, wf.wordsInNumFiles(2));
		assertEquals(res3, wf.wordsInNumFiles(1));
	}
	
	@Test
	public void getFilesInTest() {
		ArrayList<String> res1 = new ArrayList<>(Arrays.asList("brief1.txt", "brief3.txt", "brief4.txt"));
		ArrayList<String> res2 = new ArrayList<>(Arrays.asList("brief1.txt"));
		ArrayList<String> res3 = new ArrayList<>(Arrays.asList("brief3.txt", "brief4.txt"));

		assertEquals(res1, wf.getFilesIn("and"));
		assertEquals(res2, wf.getFilesIn("cute"));
		assertEquals(null, wf.getFilesIn("test"));
		assertEquals(res3, wf.getFilesIn("love"));
	}
	
	@SuppressWarnings("unused")
	private void showResults() {
		HashMap<String, ArrayList<String>> wordsInFiles = wf.getWordsInFilesMap();
		
		for (String s: wordsInFiles.keySet()) {
			System.out.println(s + "\t" + wordsInFiles.get(s));
		}
		System.out.println("Max number of files any word: " + maxVal);
		
		System.out.println("Words in 2 files: " + wf.wordsInNumFiles(2));
		System.out.println("Words in 3 files: " + wf.wordsInNumFiles(3));
		System.out.println("Words in 1 files: " + wf.wordsInNumFiles(1));
		
		wf.printFilesIn("and");
		wf.printFilesIn("cute");
		wf.printFilesIn("test");
		wf.printFilesIn("love");
		
		System.out.println("All the words that are the maximum number of files:");
		for (String s: wf.wordsInNumFiles(maxVal)) {
			System.out.printf(">>>'%s'\n", s);
			wf.printFilesIn(s);
		}
	}
}
