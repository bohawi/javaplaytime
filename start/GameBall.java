package start;

import javax.swing.*;
import java.awt.*;

public class GameBall {
	private Integer xVector = 2;
	private Integer yVector = 1;
	private Color color = Color.RED;
	private Point position = new Point(3, 3);
	private Dimension size = new Dimension(7, 7);
	private Rectangle container = new Rectangle();

	public void bounceVertical() {
		xVector = -xVector;
	}

	public void bounceHorizontal() {
		yVector = -yVector;
	}

	public Point getPosition() {
		return position;
	}

	public Dimension getSize() {
		return size;
	}

	public Rectangle getContainer() {
		container.setLocation(position);
		container.setSize(size);

		return container;
	}

	public void paint(Graphics g) {
		position.y = position.y += yVector;
		position.x = position.x += xVector;

		g.setColor(color);
		g.fillOval(
			position.x,
			position.y,
			size.width,
			size.height
		);
	}
}
