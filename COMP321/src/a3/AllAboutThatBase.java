package a3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AllAboutThatBase {
	
	final public static String BASES = "0123456789abcdefghijklmnopqrstuvwxyz0";
	
	private static int lowestBase(String s) {
		int res = 0;
		Boolean isUnary = true;
		for (int i = 0; i < s.length(); i++) {
			//check if a non-unary digit exists
			if (s.charAt(i) != '1') {
				isUnary = false;
			}
			
			if (Character.digit(s.charAt(i), 36) > res) {
				res = Character.digit(s.charAt(i), 36);
			}
		}
		if (res == 1 && isUnary) {
			return 1;
		} else {
			return res + 1;
		}
	}
	
	private static long longFromBase(String s, int b) {
		if (b == 1) {
			return s.length();
		}
		long res = 0;
		for (int i = 0; i < s.length(); i++) {
			res = res*b + Character.digit(s.charAt(i), 36);
		}
		return res;
	}
	
	private static Boolean check(long x, char op, long y, long z) {
		switch (op) {
		case '+' :
			return x + y == z;
		case '-' :
			return x - y == z;
		case '*' :
			return x * y == z;
		case '/' :
			try {
				return x%y == 0 && x/y == z;
			} catch (ArithmeticException e) {
				return false;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        String[] ln;
        String out;
        int lowestBase;
        long x, y, z;
        for (int i = 0; i < N; i++) {
        	out = "";
        	ln = br.readLine().split(" ");
        	lowestBase = lowestBase(ln[0] + ln[2] + ln[4]);
        	for (int b = lowestBase; b <= 36; b++) {
        		x = longFromBase(ln[0], b);
        		y = longFromBase(ln[2], b);
        		z = longFromBase(ln[4], b);
        		
        		if (check(x, ln[1].charAt(0), y, z)) {
        			out += BASES.charAt(b);
        		}
        	}
        	if (out == "") {
        		System.out.println("invalid");
        	} else {
        		System.out.println(out);
        	}
        	
        }
	}

}
