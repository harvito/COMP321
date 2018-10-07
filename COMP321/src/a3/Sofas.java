package a3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Sofas {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//collections maintained
		ArrayList<Sofa> stock = new ArrayList<Sofa>();
		SubsettableIntegerSet styles = new SubsettableIntegerSet();
		SubsettableIntegerSet colours = new SubsettableIntegerSet();
		
		int n = Integer.parseInt(br.readLine()); //num test cases
		int m, k, numStyleColourInStock; //stock size, output, stock subset counter
		String[] ln; //input line
		Sofa sofaIn;
		
		//MAIN LOOP, 1 ITERATION PER TEST CASE
		testCaseLoop:
		for (int i = 0; i < n; i++) {
			stock.clear();
			styles.clear();
			colours.clear();
			
			m = Integer.parseInt(br.readLine());
			
			//add inputs to the sets
			//there are optimizations possible here, keeping track of the order of each style or colour for example.
			//we omit these since no time limit is placed in the assignment.
			for (int j = 0; j < m; j++) {
				ln = br.readLine().split(" ");
				sofaIn = new Sofa(Integer.parseInt(ln[0]), Integer.parseInt(ln[1]));
				stock.add(sofaIn);
				styles.add(sofaIn.style);
				colours.add(sofaIn.colour);
			}
			
			//the highest k possible is the minimum of (|styles|, |colours|, floor(sqrt(|stock|)))
			//so start testing for a complete subset-pair here, then decrement k and test again
			k = (int) Math.min(Math.min(styles.size(), colours.size()), Math.floor(Math.sqrt(stock.size())));
			for (; k > 1; k--) {
				for (ArrayList<Integer> stylesSub : styles.setOfSubsetsOfThisSize(k)) {
					for (ArrayList<Integer> coloursSub : colours.setOfSubsetsOfThisSize(k)) {
						numStyleColourInStock = 0;

						//subset sofas for style and colour:
						for (Sofa s : stock) {
							if (stylesSub.contains(s.style) && coloursSub.contains(s.colour)) {
								numStyleColourInStock++;
							}
						}

						if (numStyleColourInStock == k * k) { //all desired combinations are in stock!
							System.out.println(k);
							continue testCaseLoop; //next test case
						}
					}
				} 
			}
			//we went through all possible combinations for k > 1 and none worked. So, output 1
			System.out.println(1);
		}

	}

	//POJO int, int pair to populate stock collection
	private static class Sofa {
		public int style;
		public int colour;
		
		public Sofa(int style, int colour) {
			this.style = style;
			this.colour = colour;
		}
	}
	
	//adds a method to ArrayList that returns a set of all <thisSize>-ed subsets of the instance set
	private static class SubsettableIntegerSet extends ArrayList<Integer> {
		private int[] indexes;
		
		public ArrayList<ArrayList<Integer>> setOfSubsetsOfThisSize(int thisSize) {
			ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> subset;
			
			//this is an array of indexes that we will choose
			indexes = new int[thisSize];
			for (int i = 0; i < thisSize; i++) {
				indexes[i] = i;
			}
			
			do {
				subset = new ArrayList<Integer>();
				for (int i = 0; i < thisSize; i++) {
					subset.add(get(indexes[i]));
				}
				res.add(subset);
			} while (incrementIndexes());
			return res;
		}
		//used for subsetting, move the next index up one. returns false when done
		private Boolean incrementIndexes() {
			for (int i = 0; i < indexes.length; i++) {
				if (indexes[indexes.length - 1 - i] != size() - 1 - i) {
					indexes[indexes.length - 1 - i]++;
					return true;
				}
			}
			return false;
		}
	}
}
