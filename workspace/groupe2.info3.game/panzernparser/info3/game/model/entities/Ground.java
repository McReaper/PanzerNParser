package info3.game.model.entities;

import info3.game.model.Model;

/**
 * Représente une unité immobile comme un trou, un arbre, un caillou, ...
 */
public class Ground extends StaticEntity {

	public Ground(int x, int y, int width, int height, Model model) {
		super(x, y, width, height, model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void step(long elapsed) {
		// TODO Auto-generated method stub

	}

}
