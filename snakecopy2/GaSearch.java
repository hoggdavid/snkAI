package snakecopy2;

import snakecopy2.AI;
import java.awt.EventQueue;
import java.util.ArrayList;

public class GaSearch {
	
	public static AI individual;
	//public static ArrayList<AI> individuals;
	public static AI[] individuals;
	public static Board myBoard;
	public static int[] scores;
	
	public static int[] best13;

	public static void main(String[] args) {

		// 100er loop {
		 	myBoard.getScore();
		   	scores = new int[]{
		   			myBoard.getScore()
		   	};
		   	individuals = new AI[]{
	    			individual	    	
	    		};
		   	// }

	        //the 13 best
	        for(int j = 0; j < 13; j++) {
	            int best = 0;
	            
	            for(int k = 1; k < 100; k++) {
	                if(scores[k] > scores[best]) {
	                    best = k;
	                }
	            }
	            best13[j] = best;
	            scores[best] = 0;
	        }
	        
	        System.out.println(" worth "+ individuals[best13[0]]);
			
	}
}
