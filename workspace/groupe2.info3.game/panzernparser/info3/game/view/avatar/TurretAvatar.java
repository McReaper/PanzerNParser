package info3.game.view.avatar;

import java.awt.Graphics;
import java.awt.Image;

import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.Entity;
import info3.game.model.entities.Turret;
import info3.game.view.Animation;
import info3.game.view.Sprite;

public class TurretAvatar extends Avatar {

	public TurretAvatar(Animation animation) {
		super(animation);
	}

	@Override
	public void paint(Graphics g, Entity entity, int xcase, int ycase, int case_width, int case_height) {
		VisionType vision = Model.getModel().getVisionType();
		MyDirection e_lookAtDir = entity.getLookAtDir();
		MyDirection e_actionDir = entity.getCurrentActionDir();
		LsAction e_currAction = entity.getCurrentAction();
		MyDirection e_absoluteActionDir = MyDirection.toAbsolute(e_lookAtDir, e_actionDir);
		double progress = entity.getActionProgress();

		Turret turrEntity = (Turret) entity;

		MyDirection e_bodyActionDir = turrEntity.getTank().getBodyDirection();
		double bodyProgress = turrEntity.getTank().getBodyProgress();
		boolean bodyMoving = turrEntity.getTank().isBodyMoving();

		int width = entity.getWidth() * case_width;
		int height = entity.getHeight() * case_height;
		int x = xcase;
		int y = ycase;

		// Pour réaliser un affichage progressif dans le cas d'un move.
		if (bodyMoving) {
			x = progressivePaintX(e_bodyActionDir, x, bodyProgress, case_width);
			y = progressivePaintY(e_bodyActionDir, y, bodyProgress, case_height);
		}
		Image sprite;
		if (e_currAction != LsAction.Hit) {
			sprite = m_animation.getImage(0, LsAction.Hit, e_lookAtDir, vision);
		} else {
			sprite = m_animation.getImage(progress, e_currAction, e_absoluteActionDir, vision);
		}
		// pour l'explosion
		int tankX = x;
		int tankY = y;
		int tankW = width;
		int tankH = height;
		// décal tourelle
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
		if (ExplosionAvatar.printEntity(turrEntity.getTank().getBody())) {
			g.drawImage(sprite, x, y, width, height, null);
		}
		ExplosionAvatar.exploding(g, turrEntity.getTank().getBody(), tankX, tankY, tankW, tankH);
	}

}
