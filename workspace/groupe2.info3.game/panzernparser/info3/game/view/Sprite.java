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

	BufferedImage m_img;
	int m_nbcol;
	int m_nblig;
	int m_width;
	int m_height;

	// ici pathname est typiquement de la forme "sprites/nom.png"
	public Sprite(String pathname) throws IOException {
		m_img = ImageIO.read(new File(pathname));
		m_width = m_img.getWidth();
		m_height = m_img.getHeight();
		m_nbcol = m_width / SPRITE_WIDTH;
		m_nblig = m_height / SPRITE_HEIGHT;
	}

	public int nbSprites() {
		return m_nbcol * m_nblig;
	}

	public BufferedImage getSprite(int index) {
		System.out.println("nbcol = "+m_nbcol+" nblig = "+m_nblig+" index = "+index);
		int x = (index - 1) % (m_nbcol);
		int y = (index - 1) / (m_nbcol);
		System.out.println("x = "+x+" y = "+y);
		return m_img.getSubimage(x * SPRITE_WIDTH, y * SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT);
	}
	
}
