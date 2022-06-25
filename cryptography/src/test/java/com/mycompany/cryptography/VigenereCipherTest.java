package com.mycompany.cryptography;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

import com.mycompany.VigenereCipher.CaesarCipher;
import com.mycompany.VigenereCipher.CaesarCracker;
import com.mycompany.VigenereCipher.VigenereBreaker;
import com.mycompany.VigenereCipher.VigenereCipher;

import util.FileResource;

public class VigenereCipherTest {
	
	String dataSource = "VigenereTestData/";
	FileResource fr;
	VigenereCipher vc;

	@Test
	public void caesarCipherClassTest() {
		CaesarCipher cc = new CaesarCipher(1);
		fr = new FileResource(dataSource + "titus-small.txt");
		String output = fr.asString();
		String encrypted = cc.encrypt(output);
		String decrypted = cc.decrypt(encrypted);
		
		assertEquals("Dpbm-cmbdl jt cfuufs uibo bopuifs ivf,\n"
				+ "Jo uibu ju tdpsot up cfbs bopuifs ivf;\n"
				+ "Gps bmm uif xbufs jo uif pdfbo\n"
				+ "Dbo ofwfs uvso uif txbo't cmbdl mfht up xijuf,\n"
				+ "Bmuipvhi tif mbwf uifn ipvsmz jo uif gmppe.\n", encrypted);  // encryption test
		
		assertEquals(output, decrypted); // decryption test
		
		assertEquals('D', cc.encryptLetter(output.charAt(0)));  // encrypt 1 letter test
		assertEquals('C', cc.decryptLetter(encrypted.charAt(0))); // decrypt 1 letter test
	}
	
	@Test
	public void caesarCrackerTest() {
		CaesarCracker cc = new CaesarCracker();
		fr = new FileResource(dataSource + "titus-small_key5.txt");
		String output = fr.asString();
		String decrypted = cc.decrypt(output);
		assertEquals("Coal-black is better than another hue,\n"
				+ "In that it scorns to bear another hue;\n"
				+ "For all the water in the ocean\n"
				+ "Can never turn the swan's black legs to white,\n"
				+ "Although she lave them hourly in the flood.\n", decrypted);
		
		cc = new CaesarCracker('a');
		fr = new FileResource(dataSource + "oslusiadas_key17.txt");
		output = fr.asString();
		decrypted = cc.decrypt(output);
		assertEquals("As armas e os barões assinalados\n"
				+ "Que, da ocidental praia lusitana,\n"
				+ "Por mares nunca de antes navegados\n"
				+ "Passaram ainda além da Taprobana,\n"
				+ "Em perigos e guerras esforçados,\n"
				+ "Mais do que prometia a força humana,\n"
				+ "E entre gente remota edificaram\n"
				+ "Novo reino, que tanto sublimaram.\n"
				+ ".....\n"
				+ "Cantando espalharei por toda a parte,\n"
				+ "Se a tanto me ajudar o engenho e arte\n", decrypted);
	}
	
	@Test
	public void vigenereCipherTest() {
		vc = new VigenereCipher(new int[] {17, 14, 12, 4});
		fr = new FileResource(dataSource + "titus-small.txt");
		String output = fr.asString();
		String encrypted = vc.encrypt(output);
		assertEquals("Tcmp-pxety mj nikhqv htee mrfhtii tyv,\n"
				+ "Me flrh mk egffzw ha ssmv ozskvqv vgi;\n"
				+ "Rsi mpc flv ieksd zb xys stsmr\n"
				+ "Qmr bqzvf xlfz kvq jkmr'g fcooo zqkj fs ktmks,\n"
				+ "Rzflfisl gti zmzv flva lfidpp ur hti txsfr.\n", encrypted);
		
		assertEquals("Coal-black is better than another hue,\n"
				+ "In that it scorns to bear another hue;\n"
				+ "For all the water in the ocean\n"
				+ "Can never turn the swan's black legs to white,\n"
				+ "Although she lave them hourly in the flood.\n", vc.decrypt(encrypted));
	}
	
	
	public void testVigenereBreaker() {
		VigenereBreaker vb = new VigenereBreaker();
		// sliceString method test
		String testSlice = "abcdefghijklm";
		assertEquals("adgjm", vb.sliceString(testSlice, 0, 3));
		assertEquals("behk", vb.sliceString(testSlice, 1, 3));
		assertEquals("cfil", vb.sliceString(testSlice, 2, 3));
		assertEquals("aeim", vb.sliceString(testSlice, 0, 4));
		assertEquals("bfj", vb.sliceString(testSlice, 1, 4));
		assertEquals("cgk", vb.sliceString(testSlice, 2, 4));
		assertEquals("dhl", vb.sliceString(testSlice, 3, 4));
		assertEquals("afk", vb.sliceString(testSlice, 0, 5));
		assertEquals("bgl", vb.sliceString(testSlice, 1, 5));
		assertEquals("chm", vb.sliceString(testSlice, 2, 5));
		assertEquals("di", vb.sliceString(testSlice, 3, 5));
		assertEquals("ej", vb.sliceString(testSlice, 4, 5));
		
		fr = new FileResource(dataSource + "athens_keyflute.txt");
		String output = fr.asString();
		// thyKeyLength method test
		assertArrayEquals(new int[] {5, 11, 20, 19, 4}, vb.tryKeyLength(output, "flute".length(), 'e'));
		
		HashSet<String> words = vb.readDictionary(new FileResource("dictionaries/English"));
		// countWords method test
		assertEquals(4, vb.countWords("Lol it's enough phrase", words));
	}

}
