package start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import start.GameBall;
import start.Paddle;

public class Game {
  Integer score = 0;
  JFrame frame;
  MyPanel panel;
  GameBall ball;
  Paddle lPaddle;
  JLabel scoreboard;

  Dimension frameSize = new Dimension(800, 600);

  public static void main(String[] args) {
    new Game().go();
  }

  private void go() {
    // Set up the window
    frame = new JFrame("Hello World!");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBackground(Color.BLACK);

    panel = new MyPanel();
    scoreboard = new JLabel();
    ball = new GameBall();
    lPaddle = new Paddle();
    lPaddle.setBoundaries(frameSize);
    // MoveAction moveUp = new MoveAction("UP", null);
    // MoveAction moveDown = new MoveAction("DOWN", null);
    // InputMap inputMap = panel.getInputMap(panel.WHEN_IN_FOCUSED_WINDOW);

    frame.addKeyListener(new MoveListener());
    frame.getContentPane().add(panel);
    panel.setLayout(null);
    panel.add(scoreboard);

    frame.pack();
    frame.setVisible(true);

    // inputMap.put(KeyStroke.getKeyStroke("UP"), "moveUp");
    // inputMap.put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
    //
    // panel.getActionMap().put("moveUp", moveUp);
    // panel.getActionMap().put("moveDown", moveDown);

    while (true) {
      try{
        Thread.sleep(3);
      } catch (Exception exc){
        exc.printStackTrace();
      }
      move();
    }
  }

  private void move() {
    Point ballPosition = ball.getPosition();
    Dimension ballSize = ball.getSize();
    Integer left = ballPosition.x;
    Integer top = ballPosition.y;
    Boolean bounceVertical = left <= 0 || left >= frameSize.width - ballSize.width;
    Boolean bounceHorizontal = top <= 0 || top >= frameSize.height - ballSize.width;

    if (lPaddle.isColliding(ball)) {
      ball.bounceVertical();
      score++;
    }
    if (bounceVertical) ball.bounceVertical();
    if (bounceHorizontal) ball.bounceHorizontal();

    frame.repaint();
  }


  class MyPanel extends JPanel {
    public MyPanel() {
      setBorder(BorderFactory.createLineBorder(Color.WHITE));
      requestFocusInWindow();
    }

    public Dimension getPreferredSize() {
      return new Dimension(frameSize.width, frameSize.height);
    }

    public void paintComponent(Graphics g) {
      ball.paint(g);
      lPaddle.paint(g);

      g.setColor(Color.WHITE);
      g.drawString(String.valueOf(score), 100, 50);
    }
  }

  public class MoveListener implements KeyListener {
		/** Handle the key typed event from the text field. */
		public void keyTyped(KeyEvent e) {
					// Do nothing
		}

		/** Handle the key-pressed event from the text field. */
		public void keyPressed(KeyEvent e) {
      String keyText = e.getKeyText(e.getKeyCode());
			lPaddle.startMove(keyText);
		}

		/** Handle the key-released event from the text field. */
		public void keyReleased(KeyEvent e) {
      String keyText = e.getKeyText(e.getKeyCode());

      lPaddle.endMove();
		}
	}
}
