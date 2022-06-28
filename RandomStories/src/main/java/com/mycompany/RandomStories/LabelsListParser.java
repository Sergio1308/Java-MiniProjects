package com.mycompany.RandomStories;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LabelsListParser {
	
	private static String labelsListSourceDir = "./src/main/resources/labelsList.json";
	private static String dictionariesSourseDir = "./src/main/resources/dictionaries";
	
	private boolean areDictionariesUpdated(JSONObject obj) {
		return obj.size() == new File(dictionariesSourseDir).list().length;
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject updateLabelsListJSON() {
		JSONObject jsonObj = new JSONObject();
		String[] dictList = new File(dictionariesSourseDir).list();
		for (String file: dictList) {
			String label = file.substring(0, file.indexOf("."));
				jsonObj.put(label, file);
		}
		writeJSON(jsonObj);
		System.out.println("JSON file successfully rewritten. Key set size: " + jsonObj.size());
		return jsonObj;
	}
	
	private void writeJSON(JSONObject obj) {
		try (FileWriter file = new FileWriter(labelsListSourceDir)) {
			// using JACKSON for readable JSON format
			ObjectMapper mp = new ObjectMapper();
			String getJSONObj = mp.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
			
            file.write(getJSONObj); 
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	protected JSONObject readJSON() {
		JSONObject jsonObj = null;
		JSONParser jparser = new JSONParser();
		
		try (FileReader reader = new FileReader(labelsListSourceDir)) {
            Object obj = jparser.parse(reader);
            jsonObj = (JSONObject) obj;
            // if a new file dictionary has been added, update json labels list
            if (!areDictionariesUpdated(jsonObj)) {
            	jsonObj = updateLabelsListJSON();
            	System.out.println("Changes have been detected. Updating JSON labels list...");
            }
        } 
		catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
		catch (IOException e) {
            e.printStackTrace();
        } 
		catch (ParseException e) {
			e.printStackTrace();
		} 
		
		return jsonObj;
	}
}
