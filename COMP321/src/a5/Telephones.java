package a5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Telephones {

	public static void main(String[] args) throws IOException {
		int N, M, Source, Destination, Start, Duration;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] splitLine;
        
        //main loop
        while (!line.startsWith("0")) {
        	splitLine = line.split(" ");
        	N = Integer.parseInt(splitLine[0]);
        	M = Integer.parseInt(splitLine[1]);
        	ArrayList<int[]> calls = new ArrayList<int[]>(); // store calls
        	
        	//store calls
        	for (int i = 0; i < N; i++) {
        		line = br.readLine();
        		splitLine = line.split(" ");
        		
        		Source = Integer.parseInt(splitLine[0]);
        		Destination = Integer.parseInt(splitLine[1]);
        		Start = Integer.parseInt(splitLine[2]);
        		Duration = Integer.parseInt(splitLine[3]);

        		calls.add(new int[] {Start, Start + Duration});
        	}
        	
        	// for each interval of interest
        	for (int i = 0; i < M; i++) {
        		line = br.readLine();
        		splitLine = line.split(" ");
        		
        		Start = Integer.parseInt(splitLine[0]);
        		Duration = Integer.parseInt(splitLine[1]);
        		
        		int end = Start + Duration;
        		
        		int result = 0;
        		for (int[] call : calls) {
        			// if call overlaps at all, increments number of calls active
        			if ((call[0] <= Start && call[1] > Start) || (call[0] < end && call[1] > end) || (call[0] >= Start && call[1] <= end)) {
        				result++;
        			}
        		}
        		System.out.println(result);
        	}
        	line = br.readLine();
        }

	}

}
