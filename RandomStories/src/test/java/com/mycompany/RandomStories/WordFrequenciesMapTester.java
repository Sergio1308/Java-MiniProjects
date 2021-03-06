package com.mycompany.RandomStories;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class WordFrequenciesMapTester {
	
	WordFrequenciesMap w;
	HashMap<String, String[]> testsMap;
	HashMap<String, Integer> myWords;
	
	@Before
	public void setUp() {
		testsMap = new HashMap<String, String[]>();
		w = new WordFrequenciesMap();
		myWords = w.getMyWords();
	}

	@Test
	public void wordFrequenciesMapTests() {
		fillTestsMap();
		
		for (String item: testsMap.keySet()) {
			w.findUnique(item);
			Integer uniqueWords = myWords.size();
			String maxValue = w.getValueWithMaxNum();
			
			assertEquals(testsMap.get(item)[0], uniqueWords.toString());
			assertEquals(testsMap.get(item)[1], maxValue);
			assertEquals(testsMap.get(item)[2], myWords.get(maxValue).toString());
		}
	}
	
	private void fillTestsMap() {
		testsMap.put("testwordfreqs.txt", new String[] {"7", "a", "3"});
		testsMap.put("confucius.txt", new String[] {"6558", "the", "2121"});
		testsMap.put("caesar.txt", new String[] {"4443", "and", "617"});
		testsMap.put("errors.txt", new String[] {"3721", "of", "609"});
		testsMap.put("hamlet.txt", new String[] {"7233", "the", "1083"});
		testsMap.put("romeo.txt", new String[] {"5895", "and", "686"});
	}
	
	@SuppressWarnings("unused")
	private void showWordFrequencies(WordFrequenciesMap w, int numOccurrences) {
		System.out.println("Number of unique words: " + myWords.size());
		for (String s: myWords.keySet()) {
			int occurences = myWords.get(s);
			if (occurences > numOccurrences) {
				System.out.println(occurences + "\t" + s);
			}
		}
		String maxValue = w.getValueWithMaxNum();
		System.out.println("Most often occured word: '" + maxValue + "' - " + myWords.get(maxValue) + " times.");
		System.out.println("------------------------------------------");
	}
}
