/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author mharve17
 */
public class Downtime {
    //greedy algorithm: at each step, always assign the earliest request to any idle stream
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);
        
        //store all reqs in a priority queue (lowest time out first)
        Queue<Integer> reqs = new PriorityQueue<Integer>();
        for (int i = 0; i < n; i++) {
            reqs.offer(Integer.parseInt(br.readLine()));
        }
        
        //assign each request to a stream (an integer in the list), adding a new stream
        List<Integer> streams = new ArrayList<Integer>();
        int req;
        int smallestStream = 0;
        nextRequest: //loop label
        while(!reqs.isEmpty()) {
            req = reqs.poll();
            
            //get the lowest stream and compare it to req
            //if ();
            
            
            //check if there is an idle stream in the list and assign
            for (int i = 0; i < streams.size(); i++) {
                if (streams.get(i) < req) { //strictly less than
                    streams.set(i, req + 999); //ex request comes in at t=0, the time taken begins counting at 0, so the last ms of req processing is t=999 and the next availability is t=1000
                    continue nextRequest; //stop both loops and go back to the while
                }
            }
            streams.add(req + 999);
        }
        
        System.out.println((int) Math.ceil((float) streams.size() / (float) k)); //always round up
    }
}
