package com.mycompany.WebServerLogs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import util.FileResource;

public class LogAnalyzer {
	
	private ArrayList<LogEntry> records;
	
	static String dataSourceDir = "src/test/resources/WeblogsDataTest/";
	
	public LogAnalyzer() {
		records = new ArrayList<LogEntry>();
	}
	
	public void readFile(String filename) {
		FileResource fr = new FileResource(dataSourceDir + filename);
		for (String line: fr.lines()) {
			LogEntry le = WebLogParser.parseEntry(line);
			records.add(le);
		}
	}
	
	public int CountUniqueIPs() {
		ArrayList<String> uniqueIPs = new ArrayList<String>();
		for (LogEntry le: records) {
			String ipAddr = le.getIpAddress();
			if (!uniqueIPs.contains(ipAddr)) {
				uniqueIPs.add(ipAddr);
			}
		}
		
		return uniqueIPs.size();
	}
	
	public void printAll() {
		for (LogEntry le: records) {
			System.out.println(le);
		}
	}
	
	public void printAllHigherThanNum(int num) {
		System.out.println("\nAll log entries that have a status code greater than " + num + ":");
		for (LogEntry le: records) {
			int statusCode = le.getStatusCode();
			if (statusCode > num) {
				System.out.println(le.toString());
			}
		}
	}
	
	public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
		ArrayList<String> visitsDayList = new ArrayList<String>();
		
		for (LogEntry le: records) {
			String currentLog = le.getAccessTime().toString();
			String ipAddr = le.getIpAddress();
			if (currentLog.contains(someday) && !visitsDayList.contains(ipAddr)) {
				visitsDayList.add(le.getIpAddress());
			}
		}
		return visitsDayList;
	}
	
	public int countUniqueIPsInRange(int low, int high) {
		ArrayList<String> uniqueIPs = new ArrayList<String>();
		for (LogEntry le: records) {
			String ipAddr = le.getIpAddress();
			int statusCode = le.getStatusCode();
			if (!uniqueIPs.contains(ipAddr) && (statusCode >= low && statusCode <= high)) {
				uniqueIPs.add(ipAddr);
			}
		}
		return uniqueIPs.size();
	}
	
	public HashMap<String, Integer> countVisitsPerIP() {
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		for (LogEntry le: records) {
			String ipAddr = le.getIpAddress();
			if (!counts.containsKey(ipAddr)) {
				counts.put(ipAddr, 1);
			} else {
				counts.put(ipAddr, counts.get(ipAddr) + 1);
			}
		}
		return counts;
	}
	
	public int countUniqueVisitsPerIP() {
		HashMap<String, Integer> counts = countVisitsPerIP();
		return counts.size();
	}
	
	public int mostNumberVisitsByIP(HashMap<String, Integer> ips) {
		return Collections.max(ips.values());
	}
	
	public ArrayList<String> iPsMostVisits(HashMap<String, Integer> ips) {
		ArrayList<String> IPsList = new ArrayList<String>();
		int maxNumber = mostNumberVisitsByIP(ips);
		for (String currIP: ips.keySet()) {
			if (ips.get(currIP) == maxNumber) {
				IPsList.add(currIP);
			}
		}
		return IPsList;
	}
	
	public HashMap<String, ArrayList<String>> iPsForDays() {
		HashMap<String, ArrayList<String>> counts = new HashMap<String, ArrayList<String>>();
		
		for (LogEntry le: records) {
			String currDate = le.getAccessTime().toString();
			String MMMDD = currDate.substring(currDate.indexOf(" ") + 1, 10);
			if (!counts.containsKey(MMMDD)) {
				counts.put(MMMDD, new ArrayList<String>());
			}
			counts.get(MMMDD).add(le.getIpAddress());
		}
		
		return counts;
	}
	
	public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dayIPs) {
		int maxValue = 0;
		String date = "";
		
		for (String value: dayIPs.keySet()) {
			int currentIpSize = dayIPs.get(value).size();
			if (currentIpSize > maxValue) {
				maxValue = currentIpSize;
				date = value;
			}
		}
		return date;
	}
	
	public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dayIPs, String day) {
		ArrayList<String> currentIPs = dayIPs.get(day);
		if (currentIPs == null) {
			return null;
		}
		HashMap<String, Integer> countIPs = new HashMap<String, Integer>();

		for (String elem: currentIPs) {
			if (!countIPs.containsKey(elem)) {
				countIPs.put(elem, 1);
			} else {
				countIPs.put(elem, countIPs.get(elem) + 1);
			}
		}
		return iPsMostVisits(countIPs);
	}
}
