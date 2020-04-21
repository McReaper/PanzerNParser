package info3.game.model.entities;

import info3.game.model.Model;

/**
 * Représente une unité immobile comme un trou, un arbre, un caillou, ...
 */
public class Ground extends StaticEntity {

	/*Champs pour donner size par defaut dans la EntityFactory */
	final static int GROUND_WIDTH = 1;
	final static int GROUND_HEIGHT = 1;
	
	public Ground(int x, int y, int width, int height, Model model) {
		super(x, y, width, height, model);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void step(long elapsed) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getActionProgress() {
		// TODO Auto-generated method stub
		return 0;
	}

}
