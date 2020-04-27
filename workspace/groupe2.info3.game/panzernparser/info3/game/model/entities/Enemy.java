package info3.game.model.entities;

import info3.game.automaton.Automaton;

public class Enemy extends MovingEntity {
	/*
	 * Champs pour donner size par defaut dans la EntityFactory. A voir si on donne
	 * des size differentes pour des categories d'ennemis differents
	 */
	public final static int ENEMY_WIDTH = 1;
	public final static int ENEMY_HEIGHT = 1;

	public static final int ENEMY_HEALTH = 100;
	public static final int ENEMY_SPEED = 100;

	public static final long ENEMY_EGG_TIME = 1000;
	public static final long ENEMY_GET_TIME = 1000;
	public static final long ENEMY_HIT_TIME = 1000;
	public static final long ENEMY_JUMP_TIME = 1000;
	public static final long ENEMY_EXPLODE_TIME = 1000;
	public static final long ENEMY_MOVE_TIME = 1000;
	public static final long ENEMY_PICK_TIME = 1000;
	public static final long ENEMY_POP_TIME = 10000;
	public static final long ENEMY_POWER_TIME = 1000;
	public static final long ENEMY_PROTECT_TIME = 1000;
	public static final long ENEMY_STORE_TIME = 1000;
	public static final long ENEMY_TURN_TIME = 0;
	public static final long ENEMY_THROW_TIME = 1000;
	public static final long ENEMY_WAIT_TIME = 50;
	public static final long ENEMY_WIZZ_TIME = 1000;

//	private boolean m_triggered; // indique si l'ennemi a détecté le joueur ou non.
//	private Droppable m_drops;

	public Enemy(int x, int y, int width, int height, Automaton aut) {
		super(x, y, width, height, ENEMY_HEALTH, ENEMY_SPEED, aut);
	}

}
