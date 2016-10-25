package ponggame;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	private static final int DIAMETER = 30; // Ball's diameter

	private Game game;

	int x = 0;
	int y = 0;
	int xa = 1; // xDirection
	int ya = 1; // yDirection

	int ballchecker; // just an additional argument for the ball constructor for
	// 1player mode

	public Ball(Game game) { // ball constructor for 2players mode
		this.game = game;
	}

	public Ball(Game game2, int num) { // ball constructor for 1player mode
		this.game = game2;
		ballchecker = num;
	}

	void move() { // 2player mode ball's move method
		boolean changeDirection = true;

		if (x + xa < 0) {
			game.gameOver(); // if ball did not collide with the racket
		} // then shows the game over dialog
		else if (x + xa > game.getWidth() - DIAMETER) {
			game.gameOver(); // if ball did not collide with the racket
		} // then shows the game over dialog
		else if (y + ya < 0) {
			ya = 1;
			//game.speed=1;
		} else if (y + ya > game.getHeight() - DIAMETER) {
			ya = -1;
			//game.speed=1;
		} else if (collision1()) {  //collision of player1 racket
		
			if(game.speed==5 && game.flag ==1){
				xa = game.speed;
				game.flag =0;
			}
			else
				xa = 1;
			x = game.racquet1.getLeftTopX() + DIAMETER;
			//System.out.println(x);
			game.racquet1.setScore();
			System.out.println(game.speed);
		}

		else if (collision2()) { //collision of player2 racket

			// xa=-1;
			if(game.speed==-5 && game.flag == 1){
			xa = game.speed;
			game.flag =0;
			}
			else
			xa=-1;
			x = game.racquet2.getRightTopx() - DIAMETER;
			//System.out.println(x);
			game.racquet2.setScore();
			System.out.println(game.speed);
		} else
			changeDirection = false;

		if (changeDirection)
			Sound.BALL.play();
		x = x + xa;
		y = y + ya;

	}

	void move2() {						// ball constructor for 1player mode
		boolean changeDirection = true;
		if (x + xa < 0)
			xa = 1;
		else if (x + xa > game.getWidth() - DIAMETER)
			xa = -1;
		else if (y + ya < 0)
			ya = 1;
		else if (y + ya > game.getHeight() - DIAMETER)
			game.gameOver();
		else if (collision()) {
			ya = -1;
			y = game.racquet.getTopY() - DIAMETER;
			game.racquet.setScore();
		} else
			changeDirection = false;

		if (changeDirection)
			Sound.BALL.play();
		
		x = x + xa;
		y = y + ya;
	}

	private boolean collision() {							//Collision for 1player mode racket
		return game.racquet.getBounds2().intersects(getBounds());
	}

	private boolean collision1() {							//collision for player 1 in 2players mode racket
		return game.racquet1.getBounds().intersects(getBounds());
	}

	private boolean collision2() {							//collision for player 2 in 2players mode racket
		return game.racquet2.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {						//paints the ball
		g.fillOval(x, y, DIAMETER, DIAMETER);

	}

	public Rectangle getBounds() {							//to get the bounds of the ball
		return new Rectangle(x, y, DIAMETER, DIAMETER);		//used when checking for collision
	}

}