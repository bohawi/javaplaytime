package start;

import javax.swing.*;
import java.awt.*;
import java.lang.*;

class Paddle extends JComponent {
	public Integer velocity = 2;
	private Integer yVector = 0;
	private Color color = Color.WHITE;
	private Point position = new Point(200, 200);
	private Dimension size = new Dimension(10, 100);
	private Rectangle container = new Rectangle();
	// TODO: boundaries shouldn't be initialize with magic numbers
	private Dimension boundaries = new Dimension(100, 100);

	public Point getPosition() {
		return position;
	}

	public void bounceHorizontal() {
		yVector = -yVector;
	}

	public Rectangle getContainer() {
		container.setLocation(position);
		container.setSize(size);

		return container;
	}

	public Boolean isColliding(GameBall thisBall) {
		Rectangle ballRect = thisBall.getContainer();
		Rectangle paddleRect = getContainer();

		return ballRect.intersects(paddleRect);
	}

	public void setBoundaries(Dimension bounds) {
		System.out.println(boundaries.width);
		boundaries.width = bounds.width;
		boundaries.height = bounds.height;
	}

	public void startMove(String command) {
		if (command == "Up") {
			yVector = -velocity;
		} else if (command == "Down") {
			yVector = velocity;
		}
	}

	public void endMove() {
		yVector = 0;
	}

	public void paint(Graphics g) {
		position.y += yVector;

		if (position.y + size.height > boundaries.height) position.y = boundaries.height - size.height;
		if (position.y < 0) position.y = 0;

		g.setColor(color);
		g.fillRect(
			position.x,
			position.y,
			size.width,
			size.height
		);
	}
}
