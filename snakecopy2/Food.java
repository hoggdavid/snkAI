package snakecopy2;

public class Food {

	private Snake snake = new Snake();
	private int foodX; // Stores X pos of our food
	private int foodY; // Stores Y pos of our food

	public void createFood() {

	    // Set our food's x & y position to a random position

	    int location = (int) (Math.random() * Board.getBoardWidthVirt());
	    foodX = location ;

	    location = (int) (Math.random() * Board.getBoardHeightVirt());
	    foodY = location;

	    for (int i = 0; i<snake.getJoints();i++){
	    	
	    	if ((foodX == snake.getSnakeX(i)) && (foodY == snake.getSnakeY(i))) {
		        createFood();
		    }
	    	
	    }
	    
	}

	public int getFoodX() {

	    return foodX;
	}

	public int getFoodY() {
	    return foodY;
	}
}
