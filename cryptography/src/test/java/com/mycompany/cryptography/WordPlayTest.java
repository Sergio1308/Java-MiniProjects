package com.mycompany.cryptography;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WordPlayTest {
	
	WordPlay wp;
	
	@Before
	public void setUp() {
		wp = new WordPlay();
	}
	
	@Test
	public void testIsVowel() {
		assertEquals(true, wp.isVowel('a'));
		assertEquals(false, wp.isVowel('y'));
		assertEquals(true, wp.isVowel('e'));
		assertEquals(true, wp.isVowel('A'));
	}
	
	@Test
	public void testReplaceVowels() {
		assertEquals("H*ll* W*rld ***yY*k*y*K*Y#", wp.replaceVowels("Hello World EeAyYOkayOKAY#", '*'));
		assertEquals("Th_s _s s_m_ t_st str_ng", wp.replaceVowels("This is some test string", '_'));
		assertEquals("RQPLQCQ QLL vQwQlS QQQQQQQ)#@Q%Q^xw", wp.replaceVowels("REPLACE ALL vOwElS aAaEuUi)#@A%I^xw", 'Q'));
	}
	
	@Test
	public void testEmphasize() {
		assertEquals("dn* ctg+*+ctg+", wp.emphasize("dna ctgaaactga", 'a'));
		assertEquals("M+ry Bell+ Abr*c*d*br+", wp.emphasize("Mary Bella Abracadabra", 'a'));
	}

}
