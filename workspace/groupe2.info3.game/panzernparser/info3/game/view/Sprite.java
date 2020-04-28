package info3.game.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Une classe qui va chargé une image png et permettre de naviguer facilement
 * dans cette dernière.
 */
public class Sprite {

	private static final int SPRITE_HEIGHT = 32;
	private static final int SPRITE_WIDTH = 32;

	BufferedImage[] m_sprites;
	int m_length;

	// ici pathname est typiquement de la forme "sprites/nom.png"
	public Sprite(String pathname) throws IOException {
		BufferedImage img = ImageIO.read(new File(pathname));
		int nbcol = img.getWidth() / SPRITE_WIDTH;
		int nblig = img.getHeight() / SPRITE_HEIGHT;
		m_length = nblig * nbcol;
		m_sprites = new BufferedImage[m_length];
		for (int i = 0; i < m_length; i++) {
			int x = i % nbcol;
			int y = i / nbcol;
			m_sprites[i] = img.getSubimage(x * SPRITE_WIDTH, y * SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT);
		}
	}

	public int nbSprites() {
		return m_length;
	}

	public BufferedImage getSprite(int index) throws IndexOutOfBoundsException {
		return m_sprites[index - 1];
	}

}
