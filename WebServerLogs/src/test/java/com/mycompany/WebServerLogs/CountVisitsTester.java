package com.mycompany.WebServerLogs;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class CountVisitsTester {
	
	LogAnalyzer la;
	GetWeblogsDataMap weblogs;
	
	HashMap<String, String[]> weblogsTestMap;
	
	@Before
	public void setUp() {
		String[][] expectedOutput = getExpectedOutput();
		
		weblogsTestMap = new HashMap<String, String[]>();
		weblogs = new GetWeblogsDataMap();
		weblogsTestMap = weblogs.getWeblogsTestMap(expectedOutput);
	}

	@Test
	public void testCountVisits() {
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		Integer mostVisits;
		String[] getWeblogsIPsArray;
		ArrayList<String> IPsList;
		
		for (String fileName: weblogsTestMap.keySet()) {
			la = new LogAnalyzer();
			la.readFile(fileName);
			counts = la.countVisitsPerIP();
			mostVisits = la.mostNumberVisitsByIP(counts);
			getWeblogsIPsArray = weblogsTestMap.get(fileName);
//			set index 0 to get access to the expected number of visits from array (as a map value) in weblogsTestMap
			assertEquals(getWeblogsIPsArray[0], mostVisits.toString());
			
			IPsList = la.iPsMostVisits(counts);
			int IPsCounter = 0;
//			start with index array i = 1 to get access to the expected IPs from weblogsTestMap
			for (int i = 1; i < getWeblogsIPsArray.length; i++) {
				assertEquals(IPsList.get(IPsCounter), getWeblogsIPsArray[i]);
				IPsCounter++;
			}
		}
	}
	
	/**
	 * Create and fill multidimensional array with data (expected output for our tests)
	 * @return	arrays, multidimensional array of strings that contains expected output for JUNIT tests.
	 */
	private String[][] getExpectedOutput() {
		String[][] arrays = new String[][] {
			new String[] {"3", "152.3.135.44"}, new String[] {"4", "177.4.40.87"}, 
			new String[] {"133", "84.190.182.222"}, new String[] {"3", "61.15.121.171", "177.4.40.87", "84.133.195.161"}, 
			new String[] {"63", "188.162.84.63"}, new String[] {"3", "61.15.121.171", "84.133.195.161"}
		};
		return arrays;
	}
}
