package a1;

import java.util.Scanner;

public class Easiest {

	//sums the digits of a Long
	public static int sumOfDigits(Long in) {
		int sumOfDigits = 0;
		while (in > 0) {
			sumOfDigits += in % 10;
			in /= 10;
		}
		return sumOfDigits;
	}
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in); //open a scanner on standard input
		int N;
		while((N = Integer.valueOf(scn.nextLine())) != 0) {
			int sumOfDigits = sumOfDigits(Long.valueOf(N));
			int p;
			for (p = 11; sumOfDigits(Long.valueOf(p*N)) != sumOfDigits; p++); //start at 11, increment p until the sums of each numbers' digits are equal
			System.out.println(p);
		}
		scn.close();
	}
	
}


