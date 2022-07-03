package com.mycompany.WebServerLogs;

import java.io.File;
import java.util.HashMap;

/***
 * This class is used to initialize a map
 * that contains the name of the files and expected output for JUNIT tests.
 * Its constructor initializes an array of string - names of test weblog files
 * and an empty HashMap of strings and array of strings. 
 */
public class GetWeblogsDataMap {
	
	private HashMap<String, String[]> weblogsTestMap;
	private String[] weblogsFiles;
	
	public GetWeblogsDataMap() {
		weblogsTestMap = new HashMap<String, String[]>();
		weblogsFiles = new File(LogAnalyzer.dataSourceDir).list();
	}
	
	/**
	 * Fill HashMap with data and return it.
	 * 
	 * @param	arrays, multidimensional array of strings that contains
	 * 		    expected output for JUNIT tests.
	 * @return	HashMap, where keys are string filenames, and values are array of arrays 
	 * 			that contains expected output for tests (passed @param arrays).
	 */
	protected HashMap<String, String[]> getWeblogsTestMap(String[][] arrays) {
		for (int i = 0; i < weblogsFiles.length; i++) {
			weblogsTestMap.put(weblogsFiles[i], arrays[i]);
		}
		return weblogsTestMap;
	}
	
	protected String getAvailableWeblogsList() {
		return String.join(", ", weblogsFiles);
	}
}
