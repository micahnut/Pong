package ponggame;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet {
	private static final int Y = 330;         //used this static integers
	private static final int WITH = 60;			// for the racquet for 1player 	
	private static final int HEIGHT = 10;		//mode
	
	int width = 0;
	int height = 0;
	int y = 0;
	int x = 0;
	int ya = 0; //yDirection
	int xa = 0;	
	int score = 0;
	private Game game;


	public Racquet(Game game,int x, int y, int width, int height) {   //Racquet constructor for 2players mode
		this.game = game;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}

	public Racquet(Game game2) {								//Racquet constructor for 1player mode
		game = game2;
	}

	public void move() {										//move method for 2players mode racquets
		if (y + ya > 0 && y + ya < game.getHeight() - height)  //movement up and down
			y = y + ya;
	}
	
	public void move2() {									//move method for 1player mode racquet
		if (x + xa > 0 && x + xa < game.getWidth() - WITH)	//movement left to right and vice versa
			x = x + xa;
	}

	public void paint(Graphics2D g) {						//paints the racquets for 2player mode
			g.fillRect(x, y, width, height);
	  }
		
	public void paint2(Graphics2D g) {						//paints the racquet for 1player mode
		g.fillRect(x, Y, WITH, HEIGHT);
	}

	public void keyReleased(KeyEvent e) {			
			ya = 0;
			xa = 0;
	}

		
	public Rectangle getBounds(){						//get bounds of racquets for 2player mode
		return new Rectangle(x,y, width, height);
		
	}
	public Rectangle getBounds2() {							//get bounds of racquet for 1player mode
		return new Rectangle(x, Y, WITH, HEIGHT);
	}
	
	public void setScore() {										//adding the score by 1 if there is a collision
		score += 1;
	}

	public int getScore() {											//getting the score
		return score;
	}
	
	public int getLeftTopX(){									//get the top of x of the player 1 racquet
		//System.out.println(x-width);
		return x;
	}
	public int getRightTopx(){									//get the top of x of the player 2 racquet
		//System.out.println(x);
		return x-width;
		
	}
	public int getTopY() {								     //get the top of the racquet of player1 mode
		return Y - HEIGHT;
	}
	
}