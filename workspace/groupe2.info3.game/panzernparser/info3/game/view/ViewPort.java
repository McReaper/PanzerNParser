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

	Model m_model;
	Entity m_player;
	View m_view;
	Grid m_grid;
	int m_width; // Taille du canvas en pixel
	int m_height;
	int m_nbCells; // Nombre de cellule du view port en une dimention
	int m_caseSize; // Taille d'une case en pixel
	int m_x; // Position du viewPort dans la grid /!\ Marge de 2 autour de ce que l'on voit
	int m_y;
	int m_offsetX; // Décalage propre au animations du move du player
	int m_offsetY;
	int m_field_of_view;
	int m_paintSize;

	public ViewPort(Entity player, View view) {
		m_view = view;
		m_grid = Model.getModel().getGrid();
		setPlayer(player);
	}

	public void setPlayer(Entity player) { /// pour quand on passe de done à tank et inversement
		m_player = player;
		m_field_of_view = m_player.getFieldOfView(); // TODO peut être changé si entity peut changer de field_of_view
		m_nbCells = m_field_of_view * 2; // pour les deux coté du tank
		m_nbCells += m_player.getWidth(); // pour le tank
		m_nbCells += 4; // pour la marge
		int maxCells = Math.min(m_grid.getNbCellsX(), m_grid.getNbCellsY());
		while (m_nbCells >= maxCells) {
			m_nbCells -= 2;
			m_field_of_view--;
		}
	}

	private void calcul() {
		m_width = m_view.m_canvas.getWidth();
		m_height = m_view.m_canvas.getHeight();
		m_paintSize = Math.min(m_height, m_width);
		m_caseSize = m_paintSize / (m_nbCells - 4);
	}

	private void positionViewPort() {
//		System.out.println("player (" + m_player.getX() + ";" + m_player.getY() + ")");
		m_x = m_player.getX();
		m_x -= m_field_of_view;
		m_x -= 2; // pour la marge
		m_x = m_grid.realX(m_x);
		m_y = m_player.getY();
		m_y -= m_field_of_view;
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
					m_offsetY -= (m_caseSize * progress);
					break;
				case SOUTH:
				case SOUTHEAST:
				case SOUTHWEST:
					m_offsetY += (m_caseSize * progress);
					break;
				default:
					break;
			}
			switch (actDir) {
				case EAST:
				case NORTHEAST:
				case SOUTHEAST:
					m_offsetX += (m_caseSize * progress);
					break;
				case WEST:
				case NORTHWEST:
				case SOUTHWEST:
					m_offsetX -= (m_caseSize * progress);
					break;
				default:
					break;
			}
		}
	}

	public void paint(Graphics g, List<Avatar> lsAvatars) {
		Entity play = Model.getModel().getPlayed();
		if (m_player != play) {
			setPlayer(play);
		}
		calcul();
		positionViewPort();
		offset(); // décalage due au mouve du tank
		Entity e;
		int x, y, w, h;

		// Décalage du aux dimensions de la fenêtre (centrage en y)
		int offsetWindowX = (m_width - m_paintSize) / 2;
		int offsetWindowY = (m_height - m_paintSize) / 2;
		g.setColor(Color.BLACK);
		
		for (int i = offsetWindowX - m_offsetX; i < m_paintSize + offsetWindowX; i += m_caseSize)
			g.drawLine(i, offsetWindowY, i, offsetWindowY + m_paintSize);
		
		for (int j = offsetWindowY - m_offsetY; j < m_paintSize + offsetWindowY; j += m_caseSize)
			g.drawLine(offsetWindowX, j, offsetWindowX + m_paintSize, j);
		
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
				x *= m_caseSize;
				y *= m_caseSize;
				// position case en px avec décalage
				x += offsetWindowX - m_offsetX;
				y += offsetWindowY - m_offsetY;
				switch (intView) {
					// Téléportation pour rentrer dans le viewPort
					case PAINT_MOVE_X:
						x += m_grid.getNbCellsX() * m_caseSize;
						break;
					case PAINT_MOVE_Y:
						y += m_grid.getNbCellsY() * m_caseSize;
						break;
					case PAINT_MOVE_XY:
						x += m_grid.getNbCellsX() * m_caseSize;
						y += m_grid.getNbCellsY() * m_caseSize;
						break;
				}
				avatar.paint(g, x, y, m_caseSize, m_caseSize);

			}

		}
		// Pour empêcher de regarder plus loin grâce à la redimension de la fenêtre
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, m_width, offsetWindowY);
		g.fillRect(0, offsetWindowY + m_paintSize, m_width, offsetWindowY);
		g.fillRect(0, 0, offsetWindowX, m_height);
		g.fillRect(offsetWindowX + m_paintSize, 0, offsetWindowX, m_height);

	}

	private static final int DO_NOT_PAINT = -1;
	private static final int PAINT_HERE = 0;
	private static final int PAINT_MOVE_X = 1;
	private static final int PAINT_MOVE_Y = 2;
	private static final int PAINT_MOVE_XY = 3;

	private int inView(int x, int y, int w, int h) {
		int xL = m_x;
		int xR = m_grid.realX(m_x + m_nbCells);
		int yU = m_y;
		int yD = m_grid.realY(m_y + m_nbCells);
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
