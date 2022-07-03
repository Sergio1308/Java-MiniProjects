package com.mycompany.WebServerLogs;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class UniqueIPTester {
	
	HashMap<String, String[]> weblogsTestMap;
	LogAnalyzer la;

	@Test
	public void testUniqIP() {
		String[][] arrays = new String[][] {
			new String[] {"4"}, new String[] {"4"}, 
			new String[] {"75"}, new String[] {"4"}, 
			new String[] {"45"}, new String[] {"4"}
		};
		
		weblogsTestMap = new HashMap<String, String[]>();
		GetWeblogsDataMap weblogs = new GetWeblogsDataMap();
		weblogsTestMap = weblogs.getWeblogsTestMap(arrays);
		
		for (String fileName: weblogsTestMap.keySet()) {
			la = new LogAnalyzer();
			la.readFile(fileName);
			Integer uniqueIPs = la.CountUniqueIPs();
			assertEquals(weblogsTestMap.get(fileName)[0], uniqueIPs.toString());
		}
	}
}
