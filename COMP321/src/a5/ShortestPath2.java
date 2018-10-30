package a5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class ShortestPath2 {

	
	
	static HashMap<Integer, Integer> unvisitedSet = new HashMap<Integer, Integer>();
	static HashMap<Integer, Integer> visitedSet = new HashMap<Integer, Integer>();
	static ArrayList<ArrayList<Edge>> outgoingEdges = new ArrayList<ArrayList<Edge>>();
	static ArrayList<ArrayList<Edge>> incomingEdges = new ArrayList<ArrayList<Edge>>();

	public static void main(String[] args) throws IOException {
		// read in full graph
		// BFS Dijsktra style; marking each node as we go
		int n, m, q, s, u, v, t0, P, d, target;
		String[] splitLine;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        
        mainLoop:
        while(line.charAt(0) != '0') {
        	splitLine = line.split(" ");
        	n = Integer.parseInt(splitLine[0]);
        	m = Integer.parseInt(splitLine[1]);
        	q = Integer.parseInt(splitLine[2]);
        	s = Integer.parseInt(splitLine[3]);
        	
        	// clear incoming / outgoing edges lists
        	unvisitedSet.clear();
        	visitedSet.clear();
        	outgoingEdges.clear();
        	incomingEdges.clear();
        	
        	
        	// reinitialize arrays
        	unvisitedSet = new HashMap<Integer, Integer>();
        	for (int i = 0; i < n; i++) {
        		unvisitedSet.put(i, Integer.MAX_VALUE);
        		outgoingEdges.add(new ArrayList<Edge>());
        		incomingEdges.add(new ArrayList<Edge>());
        	}
        	unvisitedSet.put(s, 0); //trivial case
        	
        	//read in edges
        	for (int i = 0; i < m; i++) {
        		line = br.readLine();
        		splitLine = line.split(" ");
        		
        		u = Integer.parseInt(splitLine[0]);
            	v = Integer.parseInt(splitLine[1]);
            	t0 = Integer.parseInt(splitLine[2]);
            	P = Integer.parseInt(splitLine[3]);
            	d = Integer.parseInt(splitLine[4]);
        		
            	outgoingEdges.get(u).add(new Edge(u, v, t0, P, d));
            	incomingEdges.get(v).add(new Edge(u, v, t0, P, d));
        	}
        	
        	
        	//process queries
        	LinkedList<Edge> edgesQueue = new LinkedList<Edge>();
        	
        	edgesQueue.addAll(outgoingEdges.get(s));
        	
        	queryLoop:
        	for (int i = 0; i < q; i++) {
        		line = br.readLine();
        		target = Integer.parseInt(line);
        		
        		nodesLoop:
        		while(true) {
        			//check if target is marked as visited
            		if (visitedSet.containsKey(target)) {
            			if (visitedSet.get(target) == Integer.MAX_VALUE) {
            				System.out.println("Impossible");
            			} else {
            				System.out.println(visitedSet.get(target));
            			}
            			continue queryLoop;
            		}
            		
            		// target is unvisited. Let's process a node.
            		// find the lowest-valued unvisited node
            		int node = -1;
            		int minVal = Integer.MAX_VALUE;
            		for (int k : unvisitedSet.keySet()) {
            			int val = unvisitedSet.get(k);
            			if (val < minVal) {
            				minVal = val;
            				node = k;
            			}
            		}
            		if (minVal == Integer.MAX_VALUE) { //algorithm is over
            			// move all to visited.
            			for (int k : unvisitedSet.keySet()) {
            				visitedSet.put(k, unvisitedSet.remove(k));
            			}
            			continue nodesLoop;
            		}
            		
            		
            		for (Edge e : outgoingEdges.get(node)) {
            			if (visitedSet.containsKey(e.v)) {
            				// don't consider visited nodes
            				continue;
            			}
            			int now = unvisitedSet.get(node);
            			int traversalTime = e.traverse(now);
            			if (now + traversalTime < unvisitedSet.get(e.v)) {
            				unvisitedSet.put(e.v, now + traversalTime);
            			}
            		}
            		visitedSet.put(node, unvisitedSet.remove(node));
        			
        		}
        		
        	}
        	System.out.println();
        	line = br.readLine();
        }
	}
}



class Edge {
	public int u, v, t0, P, d;

	public Edge(int u, int v, int t0, int P, int d) {
		this.u = u;
		this.v = v;
		this.t0 = t0;
		this.P = P;
		this.d = d;
	}
	
	int traverse(int time) {
		if (P == 0) {
			if (time > t0) {
				return Integer.MAX_VALUE;
			} else {
				return t0 - time + d;
			}
		} else {
			int base = t0 - time;
			// if base is nonnegative then return (base + d) else return ((base mod P) + d)
			if (base >= 0 ) {
				return base + d;
			} else {
				return ((base%P + P)%P) + d; // since % is remainder and not modulo
			}
		}
	}
	
	@Override
	public String toString() {
		return u+" "+v+" "+t0+" "+P+" "+d;
	}
}
