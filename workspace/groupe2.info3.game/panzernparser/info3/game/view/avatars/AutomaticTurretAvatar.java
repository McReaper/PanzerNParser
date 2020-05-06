package info3.game.view.avatars;

import java.awt.Graphics;
import java.awt.Image;

import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.AutomaticTurret;
import info3.game.model.entities.Entity;
import info3.game.view.Animation;

public class AutomaticTurretAvatar extends Avatar {

	public AutomaticTurretAvatar(Animation animation) {
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

		AutomaticTurret turrAuto = (AutomaticTurret) entity;
		
		MyDirection e_bodyActionDir = turrAuto.getTank().getBodyDirection();
		double bodyProgress = turrAuto.getTank().getBodyProgress();
		boolean bodyMoving = turrAuto.getTank().isBodyMoving();
		
		int width = entity.getWidth() * case_width;
		int height = entity.getHeight() * case_height;
		int x = xcase + case_width/2;
		int y = ycase + case_height/2;

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
		
		g.drawImage(sprite, x, y, width, height, null);
	}

}
