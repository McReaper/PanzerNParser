package info3.game.view;

import java.awt.Graphics;
import java.awt.Image;

import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.entities.Entity;

public class ChassisAvatar extends Avatar {

	public ChassisAvatar(Entity entity, Animation animation) {
		super(entity, animation);
	}
	
	@Override
	public void paint(Graphics g, int case_width, int case_height) {
		MyDirection e_lookAtDir = m_entity.getLookAtDir();
		MyDirection e_actionDir = m_entity.getCurrentActionDir();
		LsAction e_currAction = m_entity.getCurrentAction();
		MyDirection e_absoluteActionDir = MyDirection.toAbsolute(e_lookAtDir, e_actionDir);
		double progress = m_entity.getActionProgress();

		int width = m_entity.getWidth() * case_width;
		int height = m_entity.getHeight() * case_height;
		int x = m_entity.getX() * case_width;
		int y = m_entity.getY() * case_height;

		// Pour réaliser un affichage progressif dans le cas d'un move.
		if (e_currAction == LsAction.Move) {
			switch (e_absoluteActionDir) {
				case NORTH:
				case NORTHEAST:
				case NORTHWEST:
					y -= (case_height * progress) - case_height;
					break;
				case SOUTH:
				case SOUTHEAST:
				case SOUTHWEST:
					y += (case_height * progress) - case_height;
					break;
				default:
					break;
			}
			switch (e_absoluteActionDir) {
				case EAST:
				case NORTHEAST:
				case SOUTHEAST:
					x += (case_width * progress) - case_width;
					break;
				case WEST:
				case NORTHWEST:
				case SOUTHWEST:
					x -= (case_width * progress) - case_width;
					break;
				default:
					break;
			}
		}

		Image sprite = null;
		if(e_currAction == null || e_currAction == LsAction.Wait) {
			sprite = m_animation.getImage(0, LsAction.Move,e_lookAtDir);
		} else {
			sprite =m_animation.getImage(progress, e_currAction, e_absoluteActionDir);			
		}

		g.drawImage(sprite, x, y, width, height, null);
	}

}
