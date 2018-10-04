package a3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;

public class AllAboutThatBase {
	
	final public static String BASES = "0123456789abcdefghijklmnopqrstuvwxyz0";
	
	private static int lowestBase(String s) {
		char greatestChar = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) > greatestChar)
				greatestChar = s.charAt(i);
		}
		if (greatestChar == '1')
			return 1;
		else if (greatestChar == 'z')
			return 36;
        return BASES.indexOf(greatestChar+1);
	}
	
	private static String check(String X, String Y, char op, String Z, int b) {
		BigDecimal x;
		BigDecimal y;
		BigDecimal z;
		
		//special case: input is unary and uses symbol "1" instead of "0"
		if (b == 1) {
			// Although unspecified in the problem, assume "0" represents zero in unary
			x = (X == "0") ? BigDecimal.ZERO : new BigDecimal(Integer.toString(X.length(), 10));
			y = (Y == "0") ? BigDecimal.ZERO : new BigDecimal(Integer.toString(Y.length(), 10));
			z = (Z == "0") ? BigDecimal.ZERO : new BigDecimal(Integer.toString(Z.length(), 10));
		}
		else {
			x = new BigDecimal(new BigInteger(X, b));
			y = new BigDecimal(new BigInteger(Y, b));
			z = new BigDecimal(new BigInteger(Z, b));
		}
		
		switch (op) {
		case '+' :
			if ((x.add(y)).equals(z))
				return Character.toString(BASES.charAt(b));
			break;
		case '-' :
			if ((x.subtract(y)).equals(z))
				return Character.toString(BASES.charAt(b));
			break;
		case '*' :
			if ((x.multiply(y)).equals(z))
				return Character.toString(BASES.charAt(b));
			break;
		case '/' :
			if ((x.divide(y)).equals(z))
				return Character.toString(BASES.charAt(b));
			break;
		}
		return "";
	}

	//using Java's BigInteger, we still need to deal with special case unary
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//System.out.println(new BigDecimal(111));
		//System.exit(0);
		
		int N = Integer.parseInt(br.readLine()), lowestBase;
		char op;
		String[] nugs;
		String X, Y, Z, output;
		for (int i = 0; i < N; i++) {
			//find the biggest digit (0 < 9 < a < z) and start testing from there
			//test all bases, outputting the ones that work as we go ("0" is base 36)
			//if no bases work print "invalid"
			nugs = br.readLine().split(" ");
			X = nugs[0];
			Y = nugs[2];
			Z = nugs[4];
			output = "";
			lowestBase = lowestBase(X+Y+Z);
			op = nugs[1].charAt(0);
			for (int j = lowestBase; j <= 36; j++) {
				output += check(X, Y, op, Z, j);
			}
			if (output.isEmpty())
				System.out.println("invalid");
			else
				System.out.println(output);
		}
	}

}
