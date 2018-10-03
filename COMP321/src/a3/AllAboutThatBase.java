package a3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AllAboutThatBase {

	private static int[] add(int[] a, int[] b, int base) {
		//TODO: implement dis
		return new int[0];
	}
	private static int[] subtract(int[] a, int[] b, int base) {
		//TODO: implement dis
		return new int[0];
	}
	private static int[] multiply(int[] a, int[] b, int base) {
		//TODO: implement dis
		return new int[0];
	}
	private static int[] divide(int[] a, int[] b, int base) {
		//TODO: implement dis
		return new int[0];
	}
	
	private static int[] stringToIntArray(String s) {
		int[] result = new int[s.length()];
		char digit;
		for (int i = 0; i < s.length(); i++) {
			digit = s.charAt(i);
			if (digit >= 'a')
				result[i] = digit - 'a' + 10;
			else
				result[i] = digit - '0';
		}
		return result;
	}
	
	private static char intToBaseChar(int baseInt) {
		if (baseInt == 36)
			return '0';
		else if (baseInt < 10)
			return (char) (baseInt + '0');
		else
			return (char) (baseInt - 11 + 'a');
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		char op;
		String[] nugs;
		int[] X, Y, Z;
		for (int i = 0; i < N; i++) {
			//find the biggest digit (0 < 9 < a < z) and start testing from there
			//test all bases, outputting the ones that work as we go ("0" is base 36)
			//if no bases work print "invalid"
			nugs = br.readLine().split(" ");
			X = stringToIntArray(nugs[0]);
			Y = stringToIntArray(nugs[2]);
			Z = stringToIntArray(nugs[4]);
			op = nugs[1].charAt(0);
			for (int j = 1; j <= 36; j++) {
				switch (op) {
				case '+' :
					if (add(X, Y, j) == Z)
						System.out.print(intToBaseChar(j));
					break;
				case '-' :
					if (subtract(X, Y, j) == Z)
						System.out.print(intToBaseChar(j));
					break;
				case '*' :
					if (multiply(X, Y, j) == Z)
						System.out.print(intToBaseChar(j));
					break;
				case '/' :
					if (divide(X, Y, j) == Z)
						System.out.print(intToBaseChar(j));
					break;
				}
			}
			
		}
	}

}
