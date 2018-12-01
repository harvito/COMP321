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
    boolean unknown;
    
    HashMap<String, Integer> defs = new HashMap<String, Integer>();
    
    commandLoop:
    while ((line = br.readLine()) != null) {
//      System.err.println("<" + line + ">");
      tks = line.split(" ");
      if (tks[0].equals("def")) {
        defs.put(tks[1], Integer.parseInt(tks[2]));
      } else if (tks[0].equals("calc")) {
        
        unknown = false;
        // set sum to initial value and check if it's null
        if ((sum = defs.get(tks[1])) == null) {
          unknown = true;
        }
        
        // go thru list
        for (int i = 2; i < tks.length - 1; i += 2) {
          if (unknown || (val = defs.get(tks[i+1])) == null) {
            unknown = true;
            break;
          } else {
            if (tks[i].equals("+")) {
              sum += val;
            } else {
              sum -= val;
            }
          }
        }
        
        // output
        System.out.print(line.substring(5) + " ");
        if (unknown) {
          System.out.println("unknown");
          continue commandLoop;
        }
        // check if we have key for sum
        for (Entry<String, Integer> entry : defs.entrySet()) {
          if (entry.getValue() == sum) {
            System.out.println(entry.getKey());
            continue commandLoop;
          }
        }
        System.out.println("unknown");
        
      } else { //clear
        defs.clear();
      }
    }

  }

}
