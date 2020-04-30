package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.automaton.MyCategory;

public class Vein extends StaticEntity {

	public final static int VEIN_WIDTH = 1;
	public final static int VEIN_HEIGHT = 1;

	public static final int VEIN_HEALTH = 100;
	public static final int VEIN_SPEED = 100;

	public static final long VEIN_EGG_TIME = 1000;
	public static final long VEIN_GET_TIME = 1000;
	public static final long VEIN_HIT_TIME = 1000;
	public static final long VEIN_JUMP_TIME = 1000;
	public static final long VEIN_EXPLODE_TIME = 1000;
	public static final long VEIN_MOVE_TIME = 200;
	public static final long VEIN_PICK_TIME = 1000;
	public static final long VEIN_POP_TIME = 10000;
	public static final long VEIN_POWER_TIME = 1000;
	public static final long VEIN_PROTECT_TIME = 1000;
	public static final long VEIN_STORE_TIME = 1000;
	public static final long VEIN_TURN_TIME = 1000;
	public static final long VEIN_THROW_TIME = 1000;
	public static final long VEIN_WAIT_TIME = 5000;
	public static final long VEIN_WIZZ_TIME = 1000;

	public Vein(int x, int y, Automaton aut) {
		super(x, y, VEIN_WIDTH, VEIN_HEIGHT, aut);
		m_category = MyCategory.G;
	
	}
	
	@Override
	public void step(long elapsed) {
		m_displayed = (Model.getModel().getVisionType() == VisionType.RESSOURCES);
		super.step(elapsed);
	}
}
