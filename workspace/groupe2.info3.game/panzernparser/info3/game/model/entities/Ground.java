package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyCategory;

/**
 * Représente une unité immobile comme un trou, un arbre, un caillou, ...
 */
public class Ground extends StaticEntity {

	private static final int GROUND_WIDTH = 1;
	private static final int GROUND_HEIGHT = 1;
	
	public static final long GROUND_EGG_TIME = 1000;
	public static final long GROUND_GET_TIME = 1000;
	public static final long GROUND_HIT_TIME = 1000;
	public static final long GROUND_JUMP_TIME = 1000;
	public static final long GROUND_EXPLODE_TIME = 1000;
	public static final long GROUND_MOVE_TIME = 200;
	public static final long GROUND_PICK_TIME = 1000;
	public static final long GROUND_POP_TIME = 10000;
	public static final long GROUND_POWER_TIME = 1000;
	public static final long GROUND_PROTECT_TIME = 1000;
	public static final long GROUND_STORE_TIME = 1000;
	public static final long GROUND_TURN_TIME = 1000;
	public static final long GROUND_THROW_TIME = 1000;
	public static final long GROUND_WAIT_TIME = 1000;
	public static final long GROUND_WIZZ_TIME = 1000;

	public Ground(int x, int y, Automaton aut) {
		super(x, y, GROUND_WIDTH, GROUND_HEIGHT, aut);
		m_category = MyCategory.V;
	}
	
	@Override
	public void collide(int dammage) {
		if(getCategory() != MyCategory.O)
			super.collide(dammage);
	}

}
