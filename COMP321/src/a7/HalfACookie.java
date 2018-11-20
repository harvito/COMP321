package a7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HalfACookie {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line;
    String[] inps;
    
    while ((line = br.readLine()) != null) {
      inps = line.split(" ");
      double r = Double.parseDouble(inps[0]);
      double x = Double.parseDouble(inps[1]);
      double y = Double.parseDouble(inps[2]);

      // h is the distance from origin to strike, h2, its square
      double h2 = x*x + y*y;
      double h = Math.sqrt(h2);
      double r2 = r*r; // radius squared
      
      // check that strike is within cookie area
      if (h > r) {
        System.out.println("miss");
        continue;
      }
      
      // area of cookie
      double area = Math.PI*r2;
      
      // theta is the angle between h and the intersection of the chord and the cookie's edge
      double theta = Math.acos(h/r);
      double mhalf = Math.sqrt(r2 - h2); // that right triangle's non origin edge
      
      // calculate areas
      double areaL = h*mhalf + (Math.PI - theta)*r2;
      double areaS = area - areaL;
      
      System.out.println(areaL + " " + areaS);
    }

  }

}
