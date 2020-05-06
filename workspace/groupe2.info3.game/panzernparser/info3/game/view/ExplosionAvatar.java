package info3.game.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import info3.game.automaton.action.LsAction;
import info3.game.model.entities.EnemyLevel2;
import info3.game.model.entities.Entity;

public class ExplosionAvatar {

	private static boolean noInit = true;
	private static Sprite m_explosion;
	private static int progress;

	private static void init() {
		try {
			m_explosion = new Sprite("sprites/Explosion.png");
		} catch (IOException e) {
			System.out.println("pas d'explosion chargé");
			e.printStackTrace();
		}
		noInit = true;
	}

	public static void exploding(Graphics g, Entity entity, int x, int y, int w, int h) {
		if (noInit) {
			init();
		}
		if (entity.getCurrentAction() == LsAction.Explode) {
			Image img = m_explosion.getSprite(progress);
			g.drawImage(img, x, y, w, h, null);
		}
	}

	public static boolean printEntity(Entity entity) {
		if (entity.getCurrentAction() == LsAction.Explode) {
			progress = 1;
			progress += (int) (entity.getActionProgress() * m_explosion.nbSprites());
			if (progress > m_explosion.nbSprites()) {
				progress = m_explosion.nbSprites();
			}
			if (progress > 5) {
				return false;
			}
		}
		return true;
	}

}
