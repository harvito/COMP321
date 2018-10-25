package COMP321;



public class narrowArtGallery {
    public static final int L = 0;
    public static final int R = 1;
    public static final int NEITHER = 2;
    public static int NMAX = 9; //index of last row in art gallery
    public static int[][] worths = new int[NMAX+1][2];
    
    public static void main(String[] args) {
        for (int i=0; i <= NMAX; i++) {
            worths[i][0] = i%3;
            worths[i][1] = (i+1)%3;
        }
        
        int[][] blah = {
            {7, 8},
            {4, 9},
            {3, 7},
            {5, 9},
            {7, 2},
            {10, 3},
            {0, 10},
            {3, 2},
            {6, 3},
            {7, 9}};
        
        int[][] bloh = {
            {3, 4},
            {1, 1},
            {1, 1},
            {5, 6}};
        worths = bloh;
        int val = value(0, NEITHER, 3);
        System.out.println(val);
    }
    
    
    private static int value(int start, int colOfOpenRoomInStartRow, int numRoomsToClose) {
        //BASE CASES: k == 0 or k == NMAX - start + 1
	if (numRoomsToClose == 0) {
		return sum(start + 1, NMAX, NEITHER) + sum(start, start, colOfOpenRoomInStartRow);
	}
	if (numRoomsToClose == NMAX - start + 1) {
		return Math.max(sum(start, NMAX, L), sum(start, NMAX, R));
	}
	
	//recursive cases
	if (colOfOpenRoomInStartRow != NEITHER) { //one of the rooms in the start row must stay open. we may close room in came col as previous row, or not.
		//return max(closing room on same side as previous row, not closing room on same side as previous row)
		return Math.max(sum(start+1, start+1, colOfOpenRoomInStartRow) + value(start+1, colOfOpenRoomInStartRow, numRoomsToClose-1),
			sum(start, start, colOfOpenRoomInStartRow == L ? R : L) + value(start+1, NEITHER, numRoomsToClose));
	} else { //we may close L, R, or neither
		//return max(closing left room, closing right room, closing neither)
		return Math.max(Math.max(sum(start, start+1, R) + value(start+1, R, numRoomsToClose-1),
			sum(start, start+1, L) + value(start+1, L, numRoomsToClose-1)),
			sum(start, start, NEITHER) + value(start+1, NEITHER, numRoomsToClose));
	}
    }
    
    static int sum(int start, int end, int excludeCol) {
        int sumL = 0, sumR = 0;
        
        for (int i = start; i <= end; i++) {
            sumL += worths[i][0];
            sumR += worths[i][1];
        }
        
        if (excludeCol == L) {
            return sumR;
        } else if (excludeCol == R) {
            return sumL;
        } else {
            return sumL + sumR;
        }
    }
}
