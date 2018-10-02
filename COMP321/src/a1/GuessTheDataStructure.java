package a1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author mharve17
 */
public class GuessTheDataStructure {
    public static void main(String[] args) throws FileNotFoundException {
    	//InputStream is = new FileInputStream( new File(".\\src\\a1\\GuessTheDataStructureTest2.txt"));
    	
        Scanner scn = new Scanner(System.in);
        //Kattio kio = new Kattio(System.in);
        
        Boolean isQ, isPQ, isStack;
        
        Queue<Integer> q = new LinkedList<Integer>();
        Queue<Integer> pQ = new PriorityQueue<Integer>(Collections.reverseOrder());
        Stack<Integer> stack = new Stack<Integer>();
       
        Integer n, op, val, numTrue;
        n = 0;
        op = 0;
        val = 0;
        String[] arr = {"1", "1"};
        while (scn.hasNext()) {
            //flags
            isQ = true;
            isPQ = true;
            isStack = true;
            
            q.clear();
            pQ.clear();
            stack.clear();
            try {
				n = Integer.parseInt(scn.nextLine());
			} catch (Exception e1) {
				System.exit(0);
			}
            
            for (int i = 0; i < n; i++) {
        		arr = scn.nextLine().split(" ");
        		op = Integer.parseInt(arr[0]);
            	val = Integer.parseInt(arr[1]); //THIS LINE CRASHES. NO IT DOESN'T
            	
                System.err.println("op: " + op + " val: " + val);
            	
                if (op.equals(1)) {
                    //push onto each data struct
                    q.add(val);
                    pQ.add(val);
                    stack.push(val);
                }
                else {
                    //check that the pop is consistent and set flags
                    try {
                        if (val != q.remove())
                            isQ = false;
                        if (val != pQ.remove())
                            isPQ = false;
                        if (val != stack.pop())
                            isStack = false;
                    }
                    catch (Exception e) {
                    	//System.err.println("Exception caught");
                        isQ = false;
                        isPQ = false;
                        isStack = false;
                        break;
                    }
                }
            }
            //count the number of still-valid data structures
            numTrue = 0;
            numTrue += (isQ ? 1 : 0);
            numTrue += (isPQ ? 1 : 0);
            numTrue += (isStack ? 1 : 0);
            
            if (numTrue == 0)
                System.out.println("impossible");
            else if (numTrue == 1) {
                if (isQ) System.out.println("queue");
                if (isPQ) System.out.println("priority queue");
                if (isStack) System.out.println("stack");
            }
            else
                System.out.println("not sure");
        }
        //scn.close();
    }
}