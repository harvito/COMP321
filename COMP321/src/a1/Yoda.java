package a1;

import java.util.Scanner;

public class Yoda {
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		Long N = Long.valueOf(scn.nextLine());
		Long M = Long.valueOf(scn.nextLine());
		
		//flags
		Boolean mYoda = true, nYoda = true;
		Long m1 = M, n1 = N;
		Long mo = 0l, no = 0l;//outputs
		int mi = 1, ni = 1;//multipliers
		while (m1 != 0 || n1 != 0) {
			//compare digits and knock out smaller
			if (m1 % 10 > n1 % 10) {
				mYoda = false; //m can't be yoda
				mo += (m1 % 10)*mi;
				mi *= 10; //increment multiplier
			}
			else if (m1 % 10 < n1 % 10) {
				nYoda = false; //n can't be yoda
				no += (n1 % 10)*ni;
				ni *= 10;
			}
			else { //digits are equal
				mYoda = false;
				nYoda = false;
				mo += (m1 % 10)*mi;
				no += (n1 % 10)*ni;
				mi *= 10;
				ni *= 10;
			}
			m1 /= 10;
			n1 /= 10;
		}
		System.out.println(nYoda ? "YODA" : no);
		System.out.println(mYoda ? "YODA" : mo);
		scn.close(); //close scanner
	}
	
}
