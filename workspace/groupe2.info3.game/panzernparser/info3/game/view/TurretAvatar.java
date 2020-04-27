package info3.game.view;

import java.awt.Graphics;
import java.awt.Image;

import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.entities.Entity;
import info3.game.model.entities.Turret;

public class TurretAvatar extends Avatar {

	public TurretAvatar(Entity entity, Animation animation) {
		super(entity, animation);
	}

	@Override
	public void paint(Graphics g, int xcase, int ycase, int case_width, int case_height) {
		MyDirection e_lookAtDir = m_entity.getLookAtDir();
		MyDirection e_actionDir = m_entity.getCurrentActionDir();
		LsAction e_currAction = m_entity.getCurrentAction();
		MyDirection e_absoluteActionDir = MyDirection.toAbsolute(e_lookAtDir, e_actionDir);
		double progress = m_entity.getActionProgress();

		Turret turrEntity = (Turret) m_entity;

		MyDirection e_bodyActionDir = turrEntity.getTank().getBodyDirection();
		double bodyProgress = turrEntity.getTank().getBodyProgress();
		boolean bodyMoving = turrEntity.getTank().isBodyMoving();

		int width = m_entity.getWidth() * case_width;
		int height = m_entity.getHeight() * case_height;
		int x = xcase;
		int y = ycase;
//		System.out.println(bodyMoving + "|" + bodyProgress + "|" + e_bodyActionDir);

		// Pour réaliser un affichage progressif dans le cas d'un move.
		if (bodyMoving) {
			x = progressivePaintX(e_bodyActionDir, x, bodyProgress, case_width);
			y = progressivePaintY(e_bodyActionDir, y, bodyProgress, case_height);
		}
		Image sprite;
		if (e_currAction != LsAction.Hit) {
			sprite = m_animation.getImage(0, LsAction.Hit, e_lookAtDir);
		} else {
			sprite = m_animation.getImage(progress, e_currAction, e_absoluteActionDir);
		}
		switch (e_lookAtDir) {
			case NORTH:
			case NORTHEAST:
			case NORTHWEST:
				y -= (height * 10 / Sprite.SPRITE_HEIGHT);
				break;
			case SOUTH:
			case SOUTHEAST:
			case SOUTHWEST:
				y += (height * 10 / Sprite.SPRITE_HEIGHT);
				break;
			default:
				break;
		}
		switch (e_lookAtDir) {
			case EAST:
			case NORTHEAST:
			case SOUTHEAST:
				x += (width * 10 / Sprite.SPRITE_WIDTH);
				break;
			case WEST:
			case NORTHWEST:
			case SOUTHWEST:
				x -= (width * 10 / Sprite.SPRITE_WIDTH);
				break;
			default:
				break;
		}
		g.drawImage(sprite, x, y, width, height, null);
	}

}
