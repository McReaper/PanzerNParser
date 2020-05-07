package info3.game.view.avatars;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.Entity;
import info3.game.model.entities.MovingEntity;
import info3.game.view.Animation;

public class EnemyBasicAvatar extends Avatar {

	public EnemyBasicAvatar(Animation animation) {
		super(animation);
	}

	public void paint(Graphics g, Entity entity, int xcase, int ycase, int case_width, int case_height) {
		VisionType vision = Model.getModel().getVisionType();
		MyDirection e_lookAtDir = entity.getLookAtDir();
		MyDirection e_actionDir = entity.getCurrentActionDir();
		LsAction e_currAction = entity.getCurrentAction();
		MyDirection e_absoluteActionDir = MyDirection.toAbsolute(e_lookAtDir, e_actionDir);
		if (e_absoluteActionDir == null) {
			e_absoluteActionDir = e_lookAtDir;
		}
		double progress = entity.getActionProgress();

		int width = entity.getWidth() * case_width;
		int height = entity.getHeight() * case_height;
		int x = xcase;
		int y = ycase;
		double progressMoveAtDie = ((MovingEntity) entity).progressMoveAtDie;
		MyDirection directionMoveAtDie = ((MovingEntity) entity).directionMoveAtDie;

		// Pour réaliser un affichage progressif dans le cas d'un move.
		if (e_currAction == LsAction.Move) {
			x = progressivePaintX(e_absoluteActionDir, x, progress, case_width);
			y = progressivePaintY(e_absoluteActionDir, y, progress, case_height);
		} else if (progressMoveAtDie != 0) {
			x = progressivePaintX(directionMoveAtDie, x, progressMoveAtDie, case_width);
			y = progressivePaintY(directionMoveAtDie, y, progressMoveAtDie, case_height);
		}

		if (vision == VisionType.TANK) {
			int maxHealth = entity.getMaxHealth();
			int health = entity.getHealth();
			double percent = (double) health / (double) maxHealth;
			g.setColor(Color.BLACK);
			g.fillRect(x, y - 10, width, 5);
			g.setColor(Color.RED);
			g.fillRect(x, y - 10, (int) (width * percent), 5);
		}

		Image sprite;
		if (e_currAction == LsAction.Hit || e_currAction == LsAction.Move) {
			sprite = m_animation.getImage(progress, e_currAction, e_absoluteActionDir, vision);
		} else {
			sprite = m_animation.getImage(0, LsAction.Wait, e_absoluteActionDir, vision);
		}

		if (ExplosionAvatar.printEntity(entity)) {
			g.drawImage(sprite, x, y, width, height, null);
		}
		ExplosionAvatar.exploding(g, entity, x, y, width, height);
	}

}
