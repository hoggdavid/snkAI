package snakecopy2;
import snakecopy2.AI;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Game extends JFrame{

	public static AI individual;
	public static ArrayList<AI> individuals;
	public static Board myBoard;
	
	Game() {
	    add(new Board());
	    setResizable(false);
	    pack();
	    individual = new AI();
	    //Ai deklarieren
	    setTitle("Snake");
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void oneStep(){
		for (int k=0;k<100;k++){
        	individual.setInput(k,0);
        	}
        	
        	individual.setInput(myBoard.food.getFoodY()*10 + myBoard.food.getFoodX(), 1);
        	
        	 for (int k=myBoard.snake.getJoints(); k>0;k--) {
        		 individual.setInput(myBoard.snake.getSnakeY(k)* 10 + myBoard.snake.getSnakeX(k), -1);
        	 }
        	 
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
        	 
        	 if ((outputUp>outputDown)&&(outputUp>outputLeft)&&(outputUp>outputRight)){
        		 
        	 }
        	 
        	 if ((outputUp>outputDown)&&(outputUp>outputLeft)&&(outputUp>outputRight)){
        		 
        	 }
        	 
        	 if ((outputUp>outputDown)&&(outputUp>outputLeft)&&(outputUp>outputRight)){
        		 
        	 }
        	 
        	 myBoard.actionPerformed(null);
	}

	public static void main(String[] args) {

	    // Creates a new thread so our GUI can process itself
	    EventQueue.invokeLater(new Runnable() {
	        
	        public void run() {
	            JFrame frame = new Game();
	            frame.setVisible(true);
	        }
	    });
	    
	    oneStep();
	    
	    // 100 for-loop in 10'000 loop
	    
	    // IMPLEMENT GA HERE
	    
	}
}

//--> in board change game over and initGame()
// --> Controls or Controller