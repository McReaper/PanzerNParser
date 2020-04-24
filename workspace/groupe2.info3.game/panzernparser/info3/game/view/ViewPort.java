package info3.game.view;

import java.awt.Graphics;
import java.util.List;

import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.entities.Entity;

public class ViewPort {

	static final int AIRPORT_VIEW = 5;

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

	public ViewPort(Model model, Entity player, View view) {
		m_model = model;
		setPlayer(player);
		m_view = view;
		m_width = m_view.m_canvas.getWidth();
		m_height = m_view.m_canvas.getHeight();
	}

	public void setPlayer(Entity player) { /// pour quand on passe de done à tank et inversement
		m_player = player;
		calcul();
	}

	private void calcul() { // à refair quand on change la fenêtre
		m_nbCellsX = AIRPORT_VIEW * 2; // pour les deux coté du tank
		m_nbCellsX += 1; // pour le tank
		m_nbCellsX *= m_player.getWidth(); // pour que ça dépende de la taille de l'entity
		case_width = m_width / m_nbCellsX;
		case_height = case_width; // case carré
		// m_nbCellsY = m_height / case_height; // TODO géré si view porte pas carré
		// m_height%case_height / 2 pour le decalage si pas le bon nombre de case
	}

	private void positionViewPort() {
		m_x = m_player.getX();
		m_x -= AIRPORT_VIEW * m_player.getWidth();
		m_x = m_model.getGrid().realX(m_x);
		m_y = m_player.getY();
		m_y -= AIRPORT_VIEW * m_player.getWidth(); // c'est normal c'est pour les entité non carré
		m_y = m_model.getGrid().realY(m_y);
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
		positionViewPort();
		offset(); // décalage due au mouve du tank
		Entity e;
		int x, y, w, h;
		for (Avatar avatar : lsAvatars) {
			e = avatar.m_entity;
			x = e.getX();
			y = e.getY();
			w = e.getWidth();
			h = e.getHeight();
			if (inView(x, y, w, h)) {
				// position de la case dans le vp
				x -= m_x;
				y -= m_y;
				// position en px de la case
				x *= case_width;
				y *= case_height;
				// position case en px avec décalage
				x -= m_offsetX;
				y -= m_offsetY;
				avatar.paint(g, x, y, case_width, case_height);
			}
		}

	}

	private boolean inView(int x, int y, int w, int h) { // TODO géré les cas ou les viewport est sur "plusieurs map"
																												// utiliser + et -
		if ((x + w) > (m_x - 1) && x < (m_x + m_nbCellsX + 1) && (y + h) > (m_y - 1) && y < (m_y + m_nbCellsY + 1)) {
			return true;
		}
		return false;
	}

}
