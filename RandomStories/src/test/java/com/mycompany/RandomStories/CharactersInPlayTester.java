package com.mycompany.RandomStories;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class CharactersInPlayTester {
	
	HashMap<String, String[]> testsMap;
	CharactersInPlay cip;
	ArrayList<String> nameOfChars;
	ArrayList<Integer> countChars;
	
	@Before
	public void setUp() {
		testsMap = new HashMap<String, String[]>();
		cip = new CharactersInPlay();
		nameOfChars = cip.getNameOfChars();
		countChars = cip.getcountChars();
	}

	@Test
	public void charactersInPlayTests() {
		fillTestsMap();
		String secondLineChar = "";
		String mainChar = "";
		Integer speakingPartsChar = 0;
		
		for (String item: testsMap.keySet()) {
			cip.findAllCharacters(item);
			for (int i = 0; i < nameOfChars.size(); i++) {
				if (i == 1) secondLineChar = nameOfChars.get(i);
			}
			int maxIndex = cip.findIndexOfMax();
			mainChar = nameOfChars.get(maxIndex);
			speakingPartsChar = countChars.get(maxIndex);
		
			assertEquals(testsMap.get(item)[0], mainChar);
			assertEquals(testsMap.get(item)[1], speakingPartsChar.toString());
			assertEquals(testsMap.get(item)[2], secondLineChar);
		}
	}
	
	private void fillTestsMap() {
		testsMap.put("macbethSmall.txt", new String[] {"MACBETH", "3", "LADY MACBETH"});
		testsMap.put("macbeth.txt", new String[] {"MACBETH", "146", "FIRST WITCH"});
		testsMap.put("likeit.txt", new String[] {"ROSALIND", "200", "ACT I"});
		testsMap.put("errors.txt", new String[] {"ANTIPHOLUS OF SYRACUSE", "103", "AEGEON"});
		testsMap.put("caesar.txt", new String[] {"BRUTUS", "194", "FLAVIUS"});
	}
}
