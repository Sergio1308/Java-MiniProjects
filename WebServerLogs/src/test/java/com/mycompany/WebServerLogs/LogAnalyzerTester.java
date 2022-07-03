package com.mycompany.WebServerLogs;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class LogAnalyzerTester {
	
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
	public void testLogAnalyzer() {
		HashMap<String, Integer> countVisitsIPsMap = new HashMap<String, Integer>();
        HashMap<String, ArrayList<String>> iPsForDaysMap = new HashMap<String, ArrayList<String>>();
        String dayWithMostIPVisits = "";
        
		for (String fileName: weblogsTestMap.keySet()) {
			la = new LogAnalyzer();
	        la.readFile(fileName);
	        
	        countVisitsIPsMap = la.countVisitsPerIP();
	        iPsForDaysMap = la.iPsForDays();
	        dayWithMostIPVisits = la.dayWithMostIPVisits(iPsForDaysMap);
	        
	        assertEquals(Integer.parseInt(weblogsTestMap.get(fileName)[0]), la.countUniqueIPsInRange(100, 199));
	        assertEquals(Integer.parseInt(weblogsTestMap.get(fileName)[1]), la.countUniqueIPsInRange(200, 299));
	        assertEquals(Integer.parseInt(weblogsTestMap.get(fileName)[2]), la.countUniqueIPsInRange(400, 500));
	        assertEquals(Integer.parseInt(weblogsTestMap.get(fileName)[3]), la.mostNumberVisitsByIP(countVisitsIPsMap));
	        
	        assertEquals(weblogsTestMap.get(fileName)[4], la.iPsMostVisits(countVisitsIPsMap).get(0));
	        assertEquals(weblogsTestMap.get(fileName)[5], dayWithMostIPVisits.toString());
	        assertEquals(weblogsTestMap.get(fileName)[6], la.iPsWithMostVisitsOnDay(iPsForDaysMap, dayWithMostIPVisits).get(0));
		}
    }
	
	/**
	 * Create and fill multidimensional array with data (expected output for our tests)
	 * @return	arrays, multidimensional array of strings that contains expected output for JUNIT tests.
	 */
	private String[][] getExpectedOutput() {
		String[][] arrays = new String[][] {
			new String[] {"0", "4", "0", "3", "152.3.135.44", "Sep 30", "152.3.135.44"}, 
			new String[] {"0", "3", "1", "4", "177.4.40.87", "Sep 21", "84.133.195.161"}, 
			new String[] {"0", "65", "36", "133", "84.190.182.222", "Mar 24", "88.248.192.76"}, 
			new String[] {"0", "3", "1", "3", "61.15.121.171", "Sep 21", "84.133.195.161"},
			new String[] {"0", "40", "23", "63", "188.162.84.63", "Sep 24", "103.57.41.178"}, 
			new String[] {"0", "3", "1", "3", "61.15.121.171", "Sep 30", "61.15.121.171"}
		};
		return arrays;
	}
}
