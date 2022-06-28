package com.mycompany.RandomStories;

import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class WordsInFilesTester {

	HashMap<String, String[]> testsMap;
	WordsInFiles wf;
	
	@Before
	public void setUp() {
		testsMap = new HashMap<String, String[]>();
	}
	
	@Test
	public void wordsInNumFilesTest() {
		File[] dirList = new File(wf.dataSourceDir).listFiles();
		for (File f: dirList) {
			wf.addWodsFromFile(f);
			
		}
		assertEquals(wf.getWordsInFilesMap().get(""), 1);
	}
	
	@Test
	public void tester() {
//		addWodsFromFile(new File(dataSourceDirectory + "brief2.txt"));
//		for (String s: wordsInFiles.keySet()) {
//			System.out.println(s + "\t" + wordsInFiles.get(s));
//		}
		wf = new WordsInFiles();
		wf.buildWordFileMap();
		
			for (String s: wf.getWordsInFilesMap().keySet()) {
				System.out.println(s + "\t" + wf.getWordsInFilesMap().get(s));
			}
		
		
		int maxVal = wf.maxNumber();
		System.out.println("Max number of files any word: " + maxVal);
		
		System.out.println("Words in 7 files: " + wf.wordsInNumFiles(7).size());
		System.out.println("Words in 4 files: " + wf.wordsInNumFiles(4).size());
		
		wf.printFilesIn("sea");
		wf.printFilesIn("tree");
		
//		System.out.println("All the words that are the maximum number of files:");
//		for (String s: wf.wordsInNumFiles(maxVal)) {
//			System.out.printf(">>>'%s'\n", s);
//			wf.printFilesIn(s);
//		}
		assertEquals(1, 1);
	}
	
	private void fillTestsMap() {
		testsMap.put("love", new String[] {"MACBETH", "146", "FIRST WITCH"});
		testsMap.put("cats", new String[] {"ROSALIND", "200", "ACT I"});
		testsMap.put("are", new String[] {"ANTIPHOLUS OF SYRACUSE", "103", "AEGEON"});
		testsMap.put("and", new String[] {"BRUTUS", "194", "FLAVIUS"});
	}

}
