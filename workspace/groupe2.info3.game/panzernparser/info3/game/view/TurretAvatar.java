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
	public void paint(Graphics g, int case_width, int case_height) {
		MyDirection e_lookAtDir = m_entity.getLookAtDir();
		MyDirection e_actionDir = m_entity.getCurrentActionDir();
		LsAction e_currAction = m_entity.getCurrentAction();
		MyDirection e_absoluteActionDir = MyDirection.toAbsolute(e_lookAtDir, e_actionDir);
		double progress = m_entity.getActionProgress();

		Turret turrEntity = (Turret) m_entity;

		MyDirection e_bodyActionDir = turrEntity.getBodyDirection();
		double bodyProgress = turrEntity.getBodyProgress();
		boolean bodyMoving = turrEntity.isBodyMoving();

		int width = m_entity.getWidth() * case_width;
		int height = m_entity.getHeight() * case_height;
		int x = m_entity.getX() * case_width;
		int y = m_entity.getY() * case_height;
		System.out.println(bodyMoving + "|" + bodyProgress + "|" + e_bodyActionDir);

		// Pour réaliser un affichage progressif dans le cas d'un move.
		if (bodyMoving) {
			x = progressivePaintX(e_bodyActionDir, x, bodyProgress, case_width);
			y = progressivePaintY(e_bodyActionDir, y, bodyProgress, case_height);
		}
		Image sprite;
		// if (m_entity.getCurrentAction() != LsAction.Wait) {
		sprite = m_animation.getImage(progress, e_currAction, e_absoluteActionDir);
//		}else {
//			sprite = m_animation.getImage(1, e_currAction, e_absoluteActionDir);
//		}

		g.drawImage(sprite, x, y, width, height, null);
	}

}
