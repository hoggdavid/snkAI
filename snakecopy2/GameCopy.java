package snakecopy2;
import snakecopy2.AI;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.JFrame;

public class GameCopy extends JFrame{

	public static AI individual;
	//public static ArrayList<AI> individuals;
	public static LinkedList<AI> individuals = new LinkedList<AI>();
	public static Board myBoard;
	public static int[] scores;
	
	GameCopy() {
	    add(new Board());
	    setResizable(false);
	    pack();
	    // add ai
	    setTitle("Snake");
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void indPlay(){
		
	}
	
	public static void oneStep(int player){
		individual = individuals.get(player);
		for (int k=0;k<100;k++){
        	individual.setInput(k,0);
        	}
        	
        	individual.setInput(myBoard.food.getFoodY()*10 + myBoard.food.getFoodX(), 1);
        	
        	 for (int k=myBoard.snake.getJoints(); k>0;k--) {
        		 individual.setInput(myBoard.snake.getSnakeY(k)* 10 + myBoard.snake.getSnakeX(k), -1);
        	 }
        	 // EVALUATE OUTPUT ANN
        	 individual.Layers[1].updateLayer();
        	 individual.Layers[2].updateLayer();
        	 
        	 // GET OUTPUT ANN
        	 double outputUp = individual.getOutput(0);
        	 double outputDown = individual.getOutput(1);
        	 double outputLeft = individual.getOutput(2);
        	 double outputRight = individual.getOutput(3);
        	 //(!snake.isMovingDown())
        	 
        	 if ((outputUp>outputDown)&&(outputUp>outputLeft)&&(outputUp>outputRight)){
        		myBoard.snake.setMovingUp(true);
 	            myBoard.snake.setMovingRight(false);
 	            myBoard.snake.setMovingLeft(false);
 	            myBoard.snake.setMovingDown(false);
        	 }
        	 
        	 if ((outputRight>outputDown)&&(outputRight>outputLeft)&&(outputRight>outputUp)){
        		myBoard.snake.setMovingUp(false);
  	            myBoard.snake.setMovingRight(true);
  	            myBoard.snake.setMovingLeft(false);
  	            myBoard.snake.setMovingDown(false);
        	 }
        	 
        	 if ((outputLeft>outputDown)&&(outputLeft>outputUp)&&(outputLeft>outputRight)){
        		myBoard.snake.setMovingUp(false);
  	            myBoard.snake.setMovingRight(false);
  	            myBoard.snake.setMovingLeft(true);
  	            myBoard.snake.setMovingDown(false);
        	 }
        	 
        	 if ((outputDown>outputUp)&&(outputDown>outputLeft)&&(outputDown>outputRight)){
        		myBoard.snake.setMovingUp(false);
  	            myBoard.snake.setMovingRight(false);
  	            myBoard.snake.setMovingLeft(false);
  	            myBoard.snake.setMovingDown(true);
        	 }
        	 
        	 myBoard.actionPerformed(null);
	}
	
	public static void search(){
		// GA SEARCH
	}

	public static void main(String[] args) {
		
		for (int day7=0;day7<100;day7++){
			individual = new AI();
			individual.initialize();
			individuals.add(individual);
		}
		//10'000er LOOP
	
		for (int a=0;a<100;a++){
	    EventQueue.invokeLater(new Runnable() {
	    	// Creates a new thread so our GUI can process itself
	    	
	        public void run() {
	            JFrame frame = new GameCopy();
	            frame.setVisible(true);
	            // SWITCH OFF
	        }
	    });
	    
	    while (myBoard.inGame=true){
	    oneStep(a);
	    }
	    
	    individuals.get(a).setScore(myBoard.getScore());
	    
	}
		
		Collections.sort(individuals);
		LinkedList<AI> newGen = new LinkedList<AI>();
		for (int o=0;o<13;o++){
			for (int z=13;z>o;z--){
				newGen.add(individuals.get(o));
			}
		}
		
		/*GA
    	 * 3) randomize function
    	 * 4) restart loop
    	 */
	}
}

//--> in board change game over and initGame()
// --> Controls or Controller