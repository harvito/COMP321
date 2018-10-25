package a4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class NarrowArtGalleryBottomUp {
  static ArrayList<IntPair> g;
  static HashMap<IntTriple, Integer> memo = new HashMap<IntTriple, Integer>();
  static final int LEFT = 0, RIGHT = 1, BOTH = 2;
  
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] line = br.readLine().split(" ");
    int N, k;
    
    g = new ArrayList<IntPair>();
    
    while (line.length != 0) {
      g.clear();
      
      N = Integer.parseInt(line[0]);
      k = Integer.parseInt(line[1]);
      for (int i = 0; i < N; i++) {
        line = br.readLine().split(" ");
        g.add( new IntPair(Integer.parseInt(line[0]), Integer.parseInt(line[1])) );
      }
      fillTable(k);
      System.out.println(value(0, N-1, k));
      br.readLine();
    }
  }
  
  private static void fillTable(int k) {
    //0 rows
    for (int i = 0; i < g.size(); i++) {
      memo.put(new IntTriple(i, i, 0), 0);
    }
    
    //BASE CASES: 1 row, k = 0 or 1
    IntPair ip;
    for (int i = 0; i < g.size(); i++) {
      ip = g.get(i);
      memo.put(new IntTriple(i, i+1, 0), sum(BOTH, i, i));
      memo.put(new IntTriple(i, i+1, 1), Math.max(ip.L, ip.R));
    }
    
    //BASE CASES: i == k
    for (int c = 1; c < k; c++) { //k == i
      for (int d = 0; d <= g.size() - c; d++) { //base
        memo.put(new IntTriple(c, d, c), Math.max(sum(LEFT, c, g.size()-1), sum(RIGHT, c, g.size()-1)));
      }
      
    }
    
  }
  
  private static int value(int n1, int n2, int roomsToClose) throws Exception {
    int numRows = n2 - n1 + 1;
    Integer ans;
    
    IntTriple inps = new IntTriple(n1, n2, roomsToClose);
    ans = memo.get(inps);
    if (!(ans == null)) {
      return ans;
    }
    
    //sanity check
    if (roomsToClose > numRows) {
      throw new Exception("attempting to close more rooms than there are rows. n1="+n1+", n2="+n2+", roomsToClose="+roomsToClose);
    } else if (roomsToClose == numRows) {
      ans = Math.max(sum(LEFT, n1, n2), sum(RIGHT, n1, n2));
      memo.put(inps, ans);
      return ans;
    }
    
    //roomsToClose < numRows
    int v, max = 0;
    for (int i = 0; i < g.size() - roomsToClose; i++) {
      if ((v = sum(BOTH, 0, i-1) + value(i+1, g.size()-1, roomsToClose-1)) > max) {
        max = v;
      }
    }
    memo.put(inps, max);
    return max;
    
  }
  
  private static int sum(int side, int n1, int n2) {
    int result = 0;
    if (side == LEFT) {
      for (int i = n1; i <= n2; i++) {
        result += g.get(i).L;
      }
    } else if (side == RIGHT) {
      for (int i = n1; i <= n2; i++) {
        result += g.get(i).R;
      }
    } else {
      for (int i = n1; i <= n2; i++) {
        result += g.get(i).R + g.get(i).L;
      }
    }
    return result;
  }
}

class IntPair {
  public int L;
  public int R;
  
  public IntPair(int L, int R) {
    this.L = L;
    this.R = R;
  }
}

class IntTriple {
  public int b, i, k;
  
  public IntTriple(int i1, int i2, int i3) {
    this.b = i1;
    this.i = i2;
    this.k = i3;
  }
}
