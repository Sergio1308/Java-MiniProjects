package com.mycompany.WebServerLogs;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class LogEntryTester {
	
	LogEntry le;

	@Test
	@SuppressWarnings("deprecation")
	public void testLogEntry() {
		le = new LogEntry("1.2.3.4", new Date(2000, 0, 01), 
				"example request", 200, 500);
		assertEquals("1.2.3.4 Mon Jan 01 00:00:00 EET 3900 example request 200 500", le.toString());
		
		le = new LogEntry("1.2.100.4", new Date(2000, 01, 01), 
				"example request 2", 300, 400);
		assertEquals("1.2.100.4 Thu Feb 01 00:00:00 EET 3900 example request 2 300 400", le.toString());
		
		le = new LogEntry("192.168.1.99", new Date(1999, 11, 31), 
				"example request 3", 100, 700);
		assertEquals("192.168.1.99 Sun Dec 31 00:00:00 EET 3899 example request 3 100 700", le.toString());
	}

}
