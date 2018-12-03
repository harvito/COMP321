package a8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class AddingWords {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line;
    String[] tks;
    Integer sum, val;
    
    HashMap<String, Integer> word2val = new HashMap<String, Integer>();
    HashMap<Integer, String> val2word = new HashMap<Integer, String>();
    
    commandLoop:
    while (true) {
      line = br.readLine();
      if (line == null || line.isEmpty()) {
        System.exit(0);
      }
      tks = line.split(" ");
      if (tks[0].equals("def")) {
        word2val.put(tks[1], Integer.parseInt(tks[2]));
        val2word.put(Integer.parseInt(tks[2]), tks[1]);
      } else if (tks[0].equals("calc")) {
    	// prepare output string
    	System.out.print(line.substring(5) + " ");
    	
        sum = 0;
    	boolean add = true;
        for (int i = 1; i < tks.length - 1; i++) {
        	if (tks[i].equals("-")) {
        		add = false;
        	} else if (tks[i].equals("+")) {
        		add = true;
        	} else {
        		if (word2val.containsKey(tks[i])) {
        			val = word2val.get(tks[i]);
        			sum = add ? sum + val : sum - val;
        		} else {
        			System.out.println("unknown");
        			continue commandLoop;
        		}
        	}
        }
        
        if (val2word.containsKey(sum)) {
        	System.out.println(val2word.get(sum));
        } else {
          System.out.println("unknown");
        }
        
      } else { //clear
        word2val.clear();
        val2word.clear();
      }
    }

  }

}
