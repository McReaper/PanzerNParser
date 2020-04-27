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

	static final int RANGE_VIEW = 7;

	Model m_model;
	Entity m_player;
	View m_view;
	Grid m_grid;
	int m_width; // Taille du canvas en pixel
	int m_height;
	int m_nbCellsX; //Nombre de cellule du view port
	int m_nbCellsY;
	int m_case_width; // Taille d'une case en pixel
	int m_case_height;
	int m_x; //Position du viewPort dans la grid /!\ Marge de 2 autour de ce que l'on voit
	int m_y;
	int m_offsetX; // Décalage propre au animations du move du player
	int m_offsetY;

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
		m_case_width = m_width / m_nbCellsX;
		m_case_height = m_case_width; // case carré
		m_nbCellsX += 4; // pour la marge
		m_nbCellsY = m_nbCellsX;
		// m_nbCellsY = m_height / m_case_height; // TODO géré si view porte pas carré
		// m_height%m_case_height / 2 pour le decalage si pas le bon nombre de case
	}

	private void positionViewPort() {
//		System.out.println("player (" + m_player.getX() + ";" + m_player.getY() + ")");
		m_x = m_player.getX();
		m_x -= RANGE_VIEW * m_player.getWidth();
		m_x -= 2; // pour la marge
		m_x = m_grid.realX(m_x);
		m_y = m_player.getY();
		m_y -= RANGE_VIEW * m_player.getWidth(); // c'est normal c'est pour les entité non carré
		m_y -= 2;
		m_y = m_grid.realY(m_y);
//		System.out.println("(" + m_x + ";" + m_y + ")");
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
					m_offsetY -= (m_case_height * progress) - m_case_height;
					break;
				case SOUTH:
				case SOUTHEAST:
				case SOUTHWEST:
					m_offsetY += (m_case_height * progress) - m_case_height;
					break;
				default:
					break;
			}
			switch (actDir) {
				case EAST:
				case NORTHEAST:
				case SOUTHEAST:
					m_offsetX += (m_case_width * progress) - m_case_width;
					break;
				case WEST:
				case NORTHWEST:
				case SOUTHWEST:
					m_offsetX -= (m_case_width * progress) - m_case_width;
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
		
		//Décalage du aux dimensions de la fenêtre (centrage en y)
		int offsetWindow = (m_nbCellsY * m_case_height - (m_height + 4 * m_case_height)) / 2;
		g.setColor(Color.BLACK);
		for (int i = -m_offsetX; i < m_nbCellsX * m_case_width; i += m_case_width)
			g.drawLine(i, 0, i, m_case_height * m_nbCellsY - offsetWindow);
		for (int j = -m_offsetY; j < m_nbCellsY * m_case_height; j += m_case_height)
			g.drawLine(0, j - offsetWindow, m_case_width * m_nbCellsX, j - offsetWindow);
		for (Avatar avatar : lsAvatars) {
			e = avatar.m_entity;
			x = e.getX();
			y = e.getY();
			w = e.getWidth();
			h = e.getHeight();
			int intView = this.inView(x, y, w, h);
			if (intView != DO_NOT_PAINT) {
				// position de la case dans le vp
				x -= m_x;
				y -= m_y;
				// pour le décalage
				x -= 2;
				y -= 2;
				// position en px de la case
				x *= m_case_width;
				y *= m_case_height;
				// position case en px avec décalage
				x -= m_offsetX;
				y -= m_offsetY + offsetWindow;
				switch (intView) {
					//Téléportation pour rentrer dans le viewPort
					case PAINT_MOVE_X:
						x += m_grid.getNbCellsX() * m_case_width;
						break;
					case PAINT_MOVE_Y:
						y += m_grid.getNbCellsY() * m_case_height;
						break;
					case PAINT_MOVE_XY:
						x += m_grid.getNbCellsX() * m_case_width;
						y += m_grid.getNbCellsY() * m_case_height;
						break;
				}
				avatar.paint(g, x, y, m_case_width, m_case_height);

			}

		}
		//Pour empêcher de regarder plus loin grâce à la redimension de la fenêtre
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, m_width, (m_height - m_width) / 2);
		g.fillRect(0, (m_height + m_width) / 2, m_width, (m_height - m_width) / 2);

	}

	private static final int DO_NOT_PAINT = -1;
	private static final int PAINT_HERE = 0;
	private static final int PAINT_MOVE_X = 1;
	private static final int PAINT_MOVE_Y = 2;
	private static final int PAINT_MOVE_XY = 3;

	private int inView(int x, int y, int w, int h) {
		int xL = m_x;
		int xR = m_grid.realX(m_x + m_nbCellsX);
		int yU = m_y;
		int yD = m_grid.realY(m_y + m_nbCellsY);
		boolean inX = false;
		boolean inY = false;
		int painting = PAINT_HERE;
		if ((x + w) > xL && x < xR) {
			inX = true;
		} else if (xL > xR && ((x + w) > xL || x < xR)) {
			inX = true;
			if (x < xL) {
				painting += PAINT_MOVE_X;
			}
		}
		if ((y + h) > yU && y < yD) {
			inY = true;
		} else if (yU > yD && ((y + h) > yU || y < yD)) {
			inY = true;
			if (y < yU) {
				painting += PAINT_MOVE_Y;
			}
		}

		if (inX && inY) {
			return painting;
		}
		return DO_NOT_PAINT;
	}

}
