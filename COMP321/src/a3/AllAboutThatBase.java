package a3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AllAboutThatBase {
	//useful constant
	final public static String BASES = "0123456789abcdefghijklmnopqrstuvwxyz0";
	
	//check for the highest symbol that occurs and output the lowest possible base
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
		if (res == 1 && isUnary) { //111 is unary but 101 is not
			return 1;
		} else {
			return res + 1;
		}
	}
	
	//interpret s as base b and output the corresponding long value
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
	
	//check the math operation
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
				return x%y == 0 && x/y == z; //integer division check
			} catch (ArithmeticException e) {
				return false;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        String[] ln; //input line
        String out; //build the output for each test case
        int lowestBase; //start from the lowest base and work our way up
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
        	if (out == "") {//valid in no base
        		System.out.println("invalid");
        	} else {
        		System.out.println(out);
        	}
        	
        }
	}

}
