package com.mycompany.CaesarCipher;

import java.util.Random;

public class DiceRolling {
	
	public static void main(String[] args) {
		DiceRolling dr = new DiceRolling();
		dr.simulate(10);
	}
	
	public void simulate(int rolls) {
		Random rnd = new Random();
		int[] counts = new int[13];
		
		for (int i = 0; i < rolls; i++) {
			int d1 = rnd.nextInt(6) + 1;
			int d2 = rnd.nextInt(6) + 1;
			System.out.println("roll is " + d1 + " + " + d2 + " = " + (d1 + d2));
			
			counts[d1 + d2] += 1;
		}
		
		for (int i = 2; i <= 12; i++) {
			System.out.println(i + "\t" + counts[i] + "\t" + 100.0 * counts[i]/rolls);
		}
	}

}
