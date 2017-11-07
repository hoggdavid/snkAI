package snakecopy2;
import snakecopy2.AI;
import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
	    //Select AI
	    //Let AI play
	    setTitle("Snake");
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void letPlay(int u){
		//select safed AI
		//new class for letting an safed AI play
	}
	//safe individual
	public static void write (String filename, ArrayList<Double>p) throws IOException{
		  BufferedWriter outputWriter = null;
		  outputWriter = new BufferedWriter(new FileWriter(filename));
		  for (int i = 0; i < p.size(); i++) {
		    outputWriter.write(Integer.toString((i)));
		    outputWriter.newLine();
		  }
		  outputWriter.flush();  
		  outputWriter.close();  
		}
	
	public void load(int u){
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

	public static void main(String[] args) {
		
		for (int day7=0;day7<100;day7++){
			individual = new AI();
			individual.initialize();
			individuals.add(individual);
		}
		for (int x=0;x<10000;x++){
	
		for (int a=0;a<100;a++){
		
			new GameCopy();
		/*	
	    EventQueue.invokeLater(new Runnable() {
	    	
	        public void run() {
	            JFrame frame = new GameCopy();
	            frame.setVisible(true);
	        }
	    });*/
	    
	    while (myBoard.inGame=true){
	    oneStep(a);
	    }
	    
	    individuals.get(a).setScore(myBoard.getScore());
	    
	}
		//Genetic Algorithm
		Collections.sort(individuals);
		LinkedList<AI> newGen = new LinkedList<AI>();
		//13 best + copies
		for (int o=0;o<13;o++){
			for (int z=13;z>o;z--){
				newGen.add(individuals.get(o));
				//individuals nr.0/90
			}
		}
		
		for (int n=0;n<9;n++){
			individual = new AI();
			individual.initialize();
			newGen.add(individual);
			//individuals 91/99
		}
		
		int j = 1;
		int k = 13;
		int jPlus = 13;
		int kPlus = 12;
		
		// RANDOMIZE
		for (int l=0;l<12;l++){

			for (int r=j;r<k;r++){
				newGen.get(r);
				individual.randomize();
			}
			j = j+jPlus;
			k = k+kPlus;
			jPlus = jPlus -1;
			kPlus = kPlus -1;
		}
		if (x==9900){
			for (int layer=1;layer<3;layer++){
				for (int n=0;n<newGen.get(0).Layers[layer].Neurons.size();n++){
					
					final ArrayList<Double>FinalWeights;
					FinalWeights = new ArrayList<Double>();
					for (int w=0;w<newGen.get(0).Layers[layer].Neurons.get(n).Weights.size();w++){
						FinalWeights.add(newGen.get(0).Layers[layer].Neurons.get(n).Weights.get(w));
					}
					try {
						GameCopy.write("AI_gen("+x+")_layer("+layer+")_neuron("+n+")", FinalWeights);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}
		}
	}		
}
	
}

//Board change game over and initGame()