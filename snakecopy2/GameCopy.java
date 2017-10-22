package snakecopy2;
import snakecopy2.AI;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

public class GameCopy extends JFrame{

	public static AI individual;
	//public static ArrayList<AI> individuals;
	public static AI[] individuals;
	public static Board myBoard;
	public static int[] scores;
	
	GameCopy() {
	    add(new Board());
	    setResizable(false);
	    pack();
	    individual = new AI();
	    individual.initialize();
	    //Ai deklarieren
	    setTitle("Snake");
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void oneStep(){
		// SET INPUT ANN
		for (int k=0;k<100;k++){
        	individual.setInput(k,0);
        	}
        	
        	individual.setInput(myBoard.food.getFoodY()*10 + myBoard.food.getFoodX(), 1);
        	
        	 for (int k=myBoard.snake.getJoints(); k>0;k--) {
        		 individual.setInput(myBoard.snake.getSnakeY(k)* 10 + myBoard.snake.getSnakeX(k), -1);
        	 }
        	 // EVALUATE OUTPUT ANN
        	 individual.Layers[0].getOutputLayer0();
        	 individual.Layers[1].updateLayer();
        	 individual.Layers[1].getOutputLayer();
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
	    oneStep();
	    }
	    	
	    myBoard.getScore();
	   	scores = new int[]{
	   			myBoard.getScore()
	   	};
	   	individuals = new AI[]{
    			individual	    	
    		};
		}
		
		/*GA
    	 * 1) selection of the best
    	 * 2) make copies
    	 * 3) randomize copies
    	 * 4) restart loop
    	 */
	}
}

//--> in board change game over and initGame()
// --> Controls or Controller