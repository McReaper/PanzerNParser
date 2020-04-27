package info3.game.model.entities;

import info3.game.automaton.Automaton;

/**
 * Un marqueur est disposé par le drone lors d'un clic dans le canvas
 */
public class Marker extends StaticEntity {

	public final static int MARKER_WIDTH = 1;
	public final static int MARKER_HEIGHT = 1;

	public static final long MARKER_EGG_TIME = 1000;
	public static final long MARKER_GET_TIME = 1000;
	public static final long MARKER_HIT_TIME = 1000;
	public static final long MARKER_JUMP_TIME = 1000;
	public static final long MARKER_EXPLODE_TIME = 1000;
	public static final long MARKER_MOVE_TIME = 200;
	public static final long MARKER_PICK_TIME = 1000;
	public static final long MARKER_POP_TIME = 10000;
	public static final long MARKER_POWER_TIME = 1000;
	public static final long MARKER_PROTECT_TIME = 1000;
	public static final long MARKER_STORE_TIME = 1000;
	public static final long MARKER_TURN_TIME = 1000;
	public static final long MARKER_THROW_TIME = 1000;
	public static final long MARKER_WAIT_TIME = 50;
	public static final long MARKER_WIZZ_TIME = 1000;

	public Marker(int x, int y, int width, int height, Automaton aut) {
		super(x, y, width, height, aut);
	}

}
