package info3.game.view.avatars;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import info3.game.GameConfiguration;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.Entity;
import info3.game.view.Sprite;

public class ExplosionAvatar {

	private static boolean noInit = true;
	private static Sprite m_explosion;
	private static int progress;

	private static void init() {
		try {
			m_explosion = new Sprite(GameConfiguration.SPRITE_PATH + "Explosion.png");
		} catch (IOException e) {
			GameConfiguration.fileNotFound(GameConfiguration.SPRITE_PATH + "Explosion.png");
		}
		noInit = true;
	}

	public static void exploding(Graphics g, Entity entity, int x, int y, int w, int h) {
		if (Model.getModel().getVisionType() != VisionType.TANK)
			return;
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
