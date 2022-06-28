package com.mycompany.RandomStories;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import util.FileResource;

public class CodonCountTester {
	
	static String dataSourceDir = "CodonCountDataTest/";
	HashMap<String, String> testsMap;
	CodonCount cc;
	FileResource fr;
	
	@Before
	public void setUp() {
		testsMap = new HashMap<String, String>();
	}

	@Test
	public void getMostCommonCoddonTester() {
		fillTestsMap();
		
		cc = new CodonCount();
		assertEquals("No codon map built.\n", cc.getMostCommonCoddon());
		
		for (String item: testsMap.keySet()) {
			fr = new FileResource(dataSourceDir + item);
			for (String s: fr.lines()) {
				cc.buildCodonMap(0, s);
				assertEquals(testsMap.get(item), cc.getMostCommonCoddon());
			}
		}
	}
	
	private void fillTestsMap() {
		testsMap.put("smalldna.txt", "TCA");
		testsMap.put("dnaMystery1", "TAT");
		testsMap.put("dnaMystery2", "AAA");
	}
 
}
