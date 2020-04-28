package info3.game.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.SwingConstants;

public class CompassWidget extends JComponent implements SwingConstants {

	private static final long serialVersionUID = 1L;

	private LinkedList<Arrow> m_arrows;

	CompassWidget() {
		m_arrows = new LinkedList<Arrow>();
	}

	public void addArrow(Color color, int angle) {
		Arrow ar = new Arrow(color, angle);
		m_arrows.add(ar);
	}

	public void resetArrow() {
		m_arrows = new LinkedList<Arrow>();
	}

	private class Arrow {

		Color m_color;
		int m_direction;

		Arrow(Color color, int direction) {
			if (color == null) {
				m_color = Color.RED;
			} else {
				m_color = color;
			}
			m_direction = direction;
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		int taille = Math.min(this.getWidth(), this.getHeight());
		int offsetX = (this.getWidth() - taille) / 2;
		int offsetY = (this.getHeight() - taille) / 2;
		int width = taille;
		int height = taille;
		g.setColor(this.getForeground());
		g.fillOval(offsetX, offsetY, width, height);
		g.setColor(this.getBackground());
		g.fillOval(offsetX+5, offsetY+5, width - 10, height - 10);
		for (Arrow arrow : m_arrows) {
			g.setColor(arrow.m_color);
			int x = (int) (Math.cos(Math.toRadians(arrow.m_direction)) * (width / 2 - 5)) + width / 2;
			int y = (int) (Math.sin(Math.toRadians(arrow.m_direction)) * (height / 2 - 5)) + height / 2;
			g.drawLine(offsetX+width / 2, offsetY+height / 2,offsetX+ x,offsetY+ y);
			int x2 = (int) (Math.cos(Math.toRadians(155 + arrow.m_direction)) * 10) + x;
			int y2 = (int) (Math.sin(Math.toRadians(155 + arrow.m_direction)) * 10) + y;
			g.drawLine(offsetX + x,offsetY+ y,offsetX+ x2,offsetY+ y2);
			x2 = (int) (Math.cos(Math.toRadians(-155 + arrow.m_direction)) * 10) + x;
			y2 = (int) (Math.sin(Math.toRadians(-155 + arrow.m_direction)) * 10) + y;
			g.drawLine(offsetX+x,offsetY+ y,offsetX+ x2,offsetY+ y2);

		}
		g.setColor(this.getForeground());
		g.fillOval(offsetX+(int) (width * 0.45),offsetY+(int) (height * 0.45),(int) (width * 0.1),(int) (height * 0.1));
	}
}
