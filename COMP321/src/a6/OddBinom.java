package a6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class OddBinom {
  
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println(cnt(new BigInteger(br.readLine())));
  }
  
  // counts the number of odd binomial coefficients in space < x
  static BigInteger cnt(BigInteger x) {
    // base case
    if (x.compareTo(new BigInteger("2")) < 0) {
      return x;
    }
    
    // recursive step; odd => add a power of 2
    BigInteger addend;
    if (x.mod(new BigInteger("2")).equals(BigInteger.ONE)) {
      int bc = (x.subtract(BigInteger.ONE)).bitCount();
      addend = BigInteger.ONE.shiftLeft(bc);
    } else {
      // even => add 0
      addend = BigInteger.ZERO;
    }
    return ( cnt(x.shiftRight(1)).multiply(new BigInteger("3")) ).add(addend);
  }
}
