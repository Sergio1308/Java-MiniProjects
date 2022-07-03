package com.mycompany.WebServerLogs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class UniqueIPVisitsOnDayTester {
	
	LogAnalyzer la;
	GetWeblogsDataMap weblogs;
	
	HashMap<String, String[]> weblogsTestMap;
	
	@Before
	public void setUp() {
		String[][] expectedOutput = new String[][] {
			new String[] {"Sep 30", "4"}, new String[] {"Sep 14", "2"}, 
			new String[] {"Mar 24", "35"}, new String[] {"Sep 21", "4"}, 
			new String[] {"Sep 27", "8"}, new String[] {"Sep 30", "3"}
		};
		
		weblogsTestMap = new HashMap<String, String[]>();
		weblogs = new GetWeblogsDataMap();
		weblogsTestMap = weblogs.getWeblogsTestMap(expectedOutput);
	}
	
	@Test
	public void testuniqueIPVisitsOnDay() {	
		ArrayList<String> getUniqueIPsList = new ArrayList<String>();
		Integer uniqueIPsSize;
		
		for (String fileName: weblogsTestMap.keySet()) {
			la = new LogAnalyzer();
			la.readFile(fileName);
			getUniqueIPsList = la.uniqueIPVisitsOnDay(weblogsTestMap.get(fileName)[0]);
			uniqueIPsSize = getUniqueIPsList.size();
//			use index 1 to get the expected number of unique IPs from array (as a map value) in weblogsTestMap
			assertEquals(weblogsTestMap.get(fileName)[1], uniqueIPsSize.toString());
		}
	}
}
