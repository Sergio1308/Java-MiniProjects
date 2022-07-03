package com.mycompany.WebServerLogs;

import java.util.ArrayList;
import java.util.HashMap;

public class App 
{
    public static void main( String[] args )
    {
    	LogAnalyzer la = new LogAnalyzer();
    	GetWeblogsDataMap weblogs = new GetWeblogsDataMap();
    	
    	System.out.println("Available web logs data: " + weblogs.getAvailableWeblogsList());
    	
    	la.readFile("weblog2_log");
    	
	    int uniqueIPs = la.CountUniqueIPs();
	    HashMap<String, Integer> countVisitsIPsMap = la.countVisitsPerIP();
        HashMap<String, ArrayList<String>> iPsForDaysMap = la.iPsForDays();
        String dayWithMostIPVisits = la.dayWithMostIPVisits(iPsForDaysMap);
	    
	    la.printAllHigherThanNum(400);
		System.out.println("There are " + uniqueIPs + " IPs.");
    	System.out.println("Count unique IPs in range: " + la.countUniqueIPsInRange(200, 299));
    	System.out.println("The largest number of visits by IP: " + la.mostNumberVisitsByIP(countVisitsIPsMap));
        System.out.println("Most visited IPs: " + la.iPsMostVisits(countVisitsIPsMap));
        System.out.println("The most visited day is " + dayWithMostIPVisits);
        System.out.println("IPs with the most visits on the specified day: " + la.iPsWithMostVisitsOnDay(
        																		iPsForDaysMap, dayWithMostIPVisits));
    }
}
