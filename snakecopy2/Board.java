package snakecopy2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

	// Holds height and width of the window
	public final static int BOARDWIDTH = 150;
	public final static int BOARDHEIGHT = 150;
	//900.630
	public int totaltime = 0;
	int turns;
	
	/*public void scoreBoard(Graphics g){	
	VK_Enter umschreiben (Linie 256+ In 'Board')
	*/

	// Used to represent pixel size of food & our snake's joints
	public final static int PIXELSIZE = 15;
	public final static int BOARDWIDTHVIRT = BOARDWIDTH / PIXELSIZE;
	public final static int BOARDHEIGHTVIRT = BOARDHEIGHT / PIXELSIZE;

	// The total amount of pixels the game could possibly have.

	public final static int TOTALPIXELS = (BOARDWIDTH * BOARDHEIGHT)
	        / (PIXELSIZE * PIXELSIZE);

	// Check if game is running
	public boolean inGame = true;

	// Timer record tick times
	private Timer timer;

	// set game speed, lower # -->faster
	private int timeperiod = 300;
	//50

	// Instances of our snake & food so we can use their methods
	public Snake snake = new Snake();
	public Food food = new Food();

	public Board() {

	    addKeyListener(new Keys());
	    setBackground(Color.pink);
	    setFocusable(true);

	    setPreferredSize(new Dimension(BOARDWIDTH, BOARDHEIGHT));

	    initializeGame();
	}

	// Used to paint our components to the screen
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    draw(g);
	}

	// Draw our Snake & Food (Called on repaint()).
	void draw(Graphics g) {
	    // Only draw if the game is running / the snake is alive
	    if (inGame == true) {
	        g.setColor(Color.black);
	        g.fillRect(food.getFoodX()*PIXELSIZE, food.getFoodY()*PIXELSIZE, PIXELSIZE, PIXELSIZE); 
	        // food

	        // Draw our snake.
	        for (int i = 0; i < snake.getJoints(); i++) {
	            // Snake's head
	            if (i == 0) {
	                g.setColor(Color.white);
	                g.fillRect(snake.getSnakeX(i)*PIXELSIZE, snake.getSnakeY(i)*PIXELSIZE,
	                        PIXELSIZE, PIXELSIZE);
	                // Body of snake
	            } else {
	                g.fillRect(snake.getSnakeX(i)*PIXELSIZE, snake.getSnakeY(i)*PIXELSIZE,
	                        PIXELSIZE, PIXELSIZE);
	            }
	        }

	        // Sync our graphics together
	        Toolkit.getDefaultToolkit().sync();
	    } else {
	        // If we're not alive, then we end our game
	        endGame(g);
	    }
	}

	void initializeGame() {
	    
		totaltime = 0;
		turns = 0;
		snake.setJoints(3); // set our snake's initial size

	    // Create our snake's body
	    for (int i = 0; i < snake.getJoints(); i++) {
	        snake.setSnakeX(BOARDWIDTHVIRT / 2);
	        snake.setSnakeY(BOARDHEIGHTVIRT / 2);
	    }
	    // Start off our snake moving right
	    snake.setMovingRight(true);

	    // Generate our first 'food'
	    food.createFood();

	    // set the timer to record our game's speed / make the game move
	    timer = new Timer(timeperiod, this);
	    timer.start();
	}

	// if our snake is in the close proximity of the food..
	void checkFoodCollisions() {

	    if ((snake.getSnakeX(0) == food.getFoodX()) && (snake.getSnakeY(0) == food.getFoodY())) {

	        //System.out.println("intersection");
	    	
	        // Add a 'joint' to our snake
	        snake.setJoints(snake.getJoints() + 1);
	        // Create new food
	        food.createFood();
	    }
	}

	// Used to check collisions with snake's self and board edges
	void checkCollisions() {

	    // If the snake hits its' own joints..
	    for (int i = snake.getJoints(); i > 0; i--) {

	        // Snake can't intersect with itself if it's not larger than 5
	        if ((i > 5) && (snake.getSnakeX(0) == snake.getSnakeX(i) && (snake.getSnakeY(0) == snake.getSnakeY(i)))) {
	            inGame = false; // then the game ends
	        }
	    }

	    // If the snake intersects with the board edges...
	    if (snake.getSnakeY(0) >= BOARDHEIGHTVIRT) {
	        inGame = false;
	    }

	    if (snake.getSnakeY(0) < 0) {
	        inGame = false;
	    }

	    if (snake.getSnakeX(0) >= BOARDWIDTHVIRT) {
	        inGame = false;
	    }

	    if (snake.getSnakeX(0) < 0) {
	        inGame = false;
	    }

	    // If the game has ended, then we can stop our timer
	    if (!inGame) {
	        timer.stop();
	    }
	}
	
	public int getScore(){
		int score = snake.getJoints();
		return score;
	}
	
	void endGame(Graphics g) {

	    // Create a message telling the player the game is over
	    String message = "Game over";
	    String info = "Press Enter To Restart";

	    // Create a new font instance
	    Font font = new Font("Times New Roman", Font.BOLD, 20);
	    FontMetrics metrics = getFontMetrics(font);
	    Font fontinfo = new Font("Times New Roman", Font.ITALIC, 14);
	    FontMetrics metricsinfo = getFontMetrics(fontinfo);

	    // Set the color of the text to red, and set the font
	    g.setColor(Color.black);
	    g.setFont(font);
	    // Draw the message to the board
	    g.drawString(message, (BOARDWIDTH - metrics.stringWidth(message)) / 2, BOARDHEIGHT / 2);
	    
	    g.setColor(Color.red);
	    g.setFont(fontinfo);
	    g.drawString(info, (BOARDWIDTH - metrics.stringWidth(info))/2 +33, BOARDHEIGHT/2 +20);

	    System.out.println("Game Ended");
	    System.out.println("Your time: "+totaltime/1000 + "s");
	    //System.out.println("Your score: "+ (int i - 4));
	    //totaltime in millisekunden

	}

	// Run constantly as long as we're in game.
	
	
	//packed in a void for another void to return int-array
	
	
	public void actionPerformed(ActionEvent e) {
	    if (inGame == true) {

	        checkFoodCollisions();
	        checkCollisions();
	        snake.move();
	        }
	    
	    totaltime = totaltime + timeperiod;
	    // Repaint or 'render' our screen
	    repaint();
	    // AUSKOMMENTIEREN
	    
	}

	private class Keys extends KeyAdapter {

	    @Override
	    // RAUSNEHMEN FÜR AI
	    public void keyPressed(KeyEvent e) {

	        int key = e.getKeyCode();

	        if ((key == KeyEvent.VK_LEFT) && (!snake.isMovingRight())) {
	            snake.setMovingLeft(true);
	            snake.setMovingUp(false);
	            snake.setMovingDown(false);
	        }

	        if ((key == KeyEvent.VK_RIGHT) && (!snake.isMovingLeft())) {
	            snake.setMovingRight(true);
	            snake.setMovingUp(false);
	            snake.setMovingDown(false);
	        }

	        if ((key == KeyEvent.VK_UP) && (!snake.isMovingDown())) {
	            snake.setMovingUp(true);
	            snake.setMovingRight(false);
	            snake.setMovingLeft(false);
	        }

	        if ((key == KeyEvent.VK_DOWN) && (!snake.isMovingUp())) {
	            snake.setMovingDown(true);
	            snake.setMovingRight(false);
	            snake.setMovingLeft(false);
	        }

	        //  IMPORTANT
	        
	        if ((key == KeyEvent.VK_ENTER) && (inGame == false)) {

	            inGame = true;
	            snake.setMovingDown(false);
	            snake.setMovingRight(false);
	            snake.setMovingLeft(false);
	            snake.setMovingUp(false);

	            initializeGame();
	            
	        }
	    }
	}

	private boolean proximity(int a, int b, int closeness) {
	    return Math.abs((long) a - b) <= closeness;
	}

	public static int getAllDots() {
	    return TOTALPIXELS;
	}

	public static int getDotSize() {
	    return PIXELSIZE;
	}
	
	public static int getBoardHeightVirt(){
		return BOARDHEIGHTVIRT;
	}
	
	public static int getBoardWidthVirt(){
		return BOARDWIDTHVIRT;
	}
}