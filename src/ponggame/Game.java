package ponggame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

	Ball ball = new Ball(this); // 2players ball
	Racquet racquet1 = new Racquet(this, 10, 300, 10, 60); // p1 paddle
	Racquet racquet2 = new Racquet(this, 460, 0, 10, 60); // p2 paddle
	Ball ball2 = new Ball(this, 1); // 1player ball
	Racquet racquet = new Racquet(this); // 1player paddle
	int speed = 1;
	static int option; // choosing if 1 player or 2 player mode
	int flag=0;			//if R or SHIFT is pressed 
	
	
	public Game() {
		addKeyListener(new KeyListener() {      //checks the key pressed
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racquet1.keyReleased(e);
				racquet2.keyReleased(e);
				racquet.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {		//movement of racquets like up and down for 2players  mode
				// speed =0;							//or left and right for 1player mode

				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					racquet.xa = -1;

				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					racquet.xa = 1;

				}

				if (e.getKeyCode() == KeyEvent.VK_W) {
					racquet1.ya = -1;

				}

				if (e.getKeyCode() == KeyEvent.VK_S) {
					racquet1.ya = 1;

				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					racquet2.ya = -1;

				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					racquet2.ya = 1;

				}

				if (e.getKeyCode() == KeyEvent.VK_R) { // if player 1 will press
					// r , it will increase
					// the speed
					//if (speed == 1)
						speed=5;
						flag=1;
					System.out.println("Speed ="+speed);
				}

				if (e.getKeyCode() == KeyEvent.VK_SHIFT) { // if player 2 will
					// press shift, it
					// will decrease the
					// speed(be faster
					// for player 2)
					//if (speed == 1)
						speed=-5;
						flag=1;				
						
					System.out.println("Speed ="+speed);
				}
			}
		});
		setFocusable(true);
		Sound.BACK.play(); // audio back
	}

	private void move() {
		if (option == 1) {
			ball.move();
			racquet1.move();
			racquet2.move();
			
		}
		if (option == 2) {
			
			ball2.move2();
			racquet.move2();

		}
	}

	@Override
	public void paint(Graphics g) {  //paints everything on the frame

		// game
		super.paint(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// ball
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (option == 1) { // 2 players mode
			g.setColor(Color.black);
			ball.paint(g2d);

			// racquets or paddles
			g2d.setColor(Color.BLUE);
			racquet1.paint(g2d);

			g2d.setColor(Color.RED);
			racquet2.paint(g2d);

			g2d.setColor(Color.GRAY);
			g2d.setFont(new Font("Verdana", Font.BOLD, 30));

			// scores
			g2d.drawString("" + String.valueOf(racquet1.getScore()), 15, 30);
			g2d.drawString("" + String.valueOf(racquet2.getScore()), 400, 30);
			
		}

		if (option == 2) { // 1 player mode
			g.setColor(Color.black);
			ball2.paint(g2d);
			g2d.setColor(Color.BLUE);
			racquet.paint2(g2d);

			g2d.setColor(Color.GRAY);
			g2d.setFont(new Font("Verdana", Font.BOLD, 30));
			g2d.drawString(String.valueOf(racquet.getScore()), 10, 30);
			
		}
	}

	public void gameOver() {

		if (option == 1) {
			JOptionPane.showMessageDialog(this,
					"P1 score : " + racquet1.getScore() + " || P2 score :" + racquet2.getScore(), "Game Over",
					JOptionPane.YES_NO_OPTION);

			if (!(racquet1.getScore() - racquet2.getScore() == 3)
					&& !(racquet2.getScore() - racquet1.getScore() == 3)) {
				JOptionPane.showMessageDialog(this, "Continue");
				// checking if
				// the
				// player's
				// score is
				// higher by
				// 3 than
				// the other

				ball = new Ball(this);
				repaint();

			}
			// "Do you want to Restart the Game\n",
			else {
				Sound.GAMEOVER.play(); // play game over music
				int n = JOptionPane.showConfirmDialog(this, "Do you want to Restart the Game\n", "Question",
						JOptionPane.YES_NO_OPTION);

				if (n == JOptionPane.YES_OPTION)
					reset();
				else {
					Sound.BACK.stop();
					System.exit(ABORT);
				}
			}

		}
		if (option == 2) {
			Sound.GAMEOVER.play(); // play game over music
			Sound.BACK.stop(); // stop the bg music
			JOptionPane.showMessageDialog(this, "your score is: " + racquet.getScore(), "Game Over", JOptionPane.YES_NO_OPTION);

			int n = JOptionPane.showConfirmDialog( // asking the user if he/she
					// wants to restart the game
					this, "Do you want to Restart the Game\n", "Question", JOptionPane.YES_NO_OPTION);

			if (n == JOptionPane.YES_OPTION)
				reset();
			else {
				System.exit(ABORT);
			}

		}

	}

	public void reset() {		//reset method..but only able to reset what mode was chosen
		if (option == 1) {
			ball = new Ball(this);
			racquet1.score = 0;
			racquet2.score = 0;
			speed = 1;
			repaint();
			Sound.BACK.play();
		}
		if (option == 2) {
			ball2 = new Ball(this, 1);
			racquet.score=0;
			repaint();
			speed = 1;
			Sound.BACK.play();
		}

	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Mini Tennis");
		Game game = new Game();
		frame.add(game);
		frame.setSize(500, 400);

		// Custom button text
		Object[] options = { "2 Players", "1 Player", "Exit" };
		int n = JOptionPane.showOptionDialog(frame, "Let's Play Pong Game ", "Pong", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

		if (n == JOptionPane.YES_OPTION) { // 2 players mode
			option = 1;
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			while (true) {
				game.move();
				game.repaint();
				Thread.sleep(10);
			}
		} else if (n == JOptionPane.NO_OPTION) { // 1 player mode
			
			JFrame frame1 = new JFrame("Mini Tennis");
			frame1.add(game);
			frame1.setSize(300, 400);
			frame1.setVisible(true);
			frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			option = 2;
			while (true) {
				game.move();
				game.repaint();
				Thread.sleep(10);
			}

		} else
			System.exit(ABORT);

	}
}