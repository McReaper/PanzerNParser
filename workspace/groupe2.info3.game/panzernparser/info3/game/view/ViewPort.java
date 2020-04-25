package info3.game.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Grid;
import info3.game.model.Model;
import info3.game.model.entities.Entity;

public class ViewPort {

	static final int RANGE_VIEW = 3;

	Model m_model;
	Entity m_player;
	View m_view;
	int m_width;
	int m_height;
	int m_nbCellsX;
	int m_nbCellsY;
	int case_width;
	int case_height;
	int m_x;
	int m_y;
	int m_offsetX;
	int m_offsetY;
	Grid m_grid;

	public ViewPort(Model model, Entity player, View view) {
		m_model = model;
		m_view = view;
		m_grid = model.getGrid();
		setPlayer(player);
	}

	public void setPlayer(Entity player) { /// pour quand on passe de done à tank et inversement
		m_player = player;
	}

	private void calcul() { // à refair quand on change la fenêtre
		m_nbCellsX = RANGE_VIEW * 2; // pour les deux coté du tank
		m_nbCellsX += 1; // pour le tank
		m_nbCellsX *= m_player.getWidth(); // pour que ça dépende de la taille de l'entity
		case_width = m_width / m_nbCellsX;
		case_height = case_width; // case carré
		m_nbCellsY = m_nbCellsX;
		// m_nbCellsY = m_height / case_height; // TODO géré si view porte pas carré
		// m_height%case_height / 2 pour le decalage si pas le bon nombre de case
	}

	private void positionViewPort() {
		System.out.println("player (" + m_player.getX() + ";" + m_player.getY() + ")");
		m_x = m_player.getX();
		m_x -= RANGE_VIEW * m_player.getWidth();
		m_x = m_grid.realX(m_x);
		m_y = m_player.getY();
		m_y -= RANGE_VIEW * m_player.getWidth(); // c'est normal c'est pour les entité non carré
		m_y = m_grid.realY(m_y);
		System.out.println("(" + m_x + ";" + m_y + ")");
	}

	private void offset() {
		m_offsetX = 0;
		m_offsetY = 0;
		if (m_player.getCurrentAction() == LsAction.Move) {
			double progress = m_player.getActionProgress();
			MyDirection actDir = m_player.getCurrentActionDir();
			switch (actDir) {
				case NORTH:
				case NORTHEAST:
				case NORTHWEST:
					m_offsetY -= (case_height * progress) - case_height;
					break;
				case SOUTH:
				case SOUTHEAST:
				case SOUTHWEST:
					m_offsetY += (case_height * progress) - case_height;
					break;
				default:
					break;
			}
			switch (actDir) {
				case EAST:
				case NORTHEAST:
				case SOUTHEAST:
					m_offsetX += (case_width * progress) - case_width;
					break;
				case WEST:
				case NORTHWEST:
				case SOUTHWEST:
					m_offsetX -= (case_width * progress) - case_width;
					break;
				default:
					break;
			}
		}
	}

	public void paint(Graphics g, List<Avatar> lsAvatars) {

		m_width = m_view.m_canvas.getWidth();
		m_height = m_view.m_canvas.getHeight();
		calcul();
		positionViewPort();
		offset(); // décalage due au mouve du tank
		Entity e;
		int x, y, w, h;
		g.setColor(Color.BLACK);
		System.out.println("la GRID");
		System.out.println(case_width);
		for (int i = -m_offsetX; i < m_nbCellsX * case_width; i += case_width)
			g.drawLine(i, 0, i, case_height * m_nbCellsY);
		for (int j = -m_offsetY; j < m_nbCellsY * case_height; j += case_height)
			g.drawLine(0, j, case_width * m_nbCellsX, j);
		for (Avatar avatar : lsAvatars) {
			e = avatar.m_entity;
			x = e.getX();
			y = e.getY();
			w = e.getWidth();
			h = e.getHeight();
			int intView = this.inView(x, y, w, h);
			if(intView != DO_NOT_PAINT) {
				// position de la case dans le vp
				x -= m_x;
				y -= m_y;
				// position en px de la case
				x *= case_width;
				y *= case_height;
				// position case en px avec décalage
				x -= m_offsetX;
				y -= m_offsetY;
				switch(intView) {
					case PAINT_MOVE_X:
						x += m_grid.getNbCellsX() * case_width;
						break;
					case PAINT_MOVE_Y:
						y += m_grid.getNbCellsY() * case_height;
						break;
					case PAINT_MOVE_XY:
						x += m_grid.getNbCellsX() * case_width;
						y += m_grid.getNbCellsY() * case_height;
						break;
				}
				avatar.paint(g, x, y, case_width, case_height);
				
			}

		}

	}

	private static final int DO_NOT_PAINT = -1;
	private static final int PAINT_HERE = 0;
	private static final int PAINT_MOVE_X = 1;
	private static final int PAINT_MOVE_Y = 2;
	private static final int PAINT_MOVE_XY = 3;

	private int inView(int x, int y, int w, int h) { // TODO géré les cas ou les viewport est sur "plusieurs map"
																										// utiliser + et -
		// if ((x + w) > (m_x - 2) && x < (m_x + m_nbCellsX + 2) && (y + h) > (m_y - 2)
		// && y < (m_y + m_nbCellsY + 2)) {
		/// return true;
		// }
		/* TODO : Il reste un problème pour quand on est sur les deux cases du bord
		 * de map (haut et gauche)
		 * 
		 */
		int xL = m_grid.realX(m_x - 2);
		int xR = m_grid.realX(m_x + m_nbCellsX + 2);
		int yU = m_grid.realY(m_y - 2);
		int yD = m_grid.realY(m_y + m_nbCellsY + 2);
		boolean inX = false;
		boolean inY = false;
		int painting = PAINT_HERE;
		if ((x + w) > xL && x < xR) {
			inX = true;
		} else if (xL > xR && ((x + w) > xL || x < xR)) {
			inX = true;
			if (x < xR) {
				painting += PAINT_MOVE_X;
			}
		}
		if ((y + h) > yU && y < yD) {
			inY = true;
		} else if (yU > yD && ((y + h) > yU || y < yD)) {
			inY = true;
			if (y < yD) {
				painting += PAINT_MOVE_Y;
			}
		}

		if (inX && inY) {
			return painting;
		}
		return DO_NOT_PAINT;
	}

}
