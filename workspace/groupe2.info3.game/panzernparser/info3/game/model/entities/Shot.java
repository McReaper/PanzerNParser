package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;

/**
 * Une entité représentant un tir simple, infligeant des dégats à d'autre
 * MovingEntity.
 */
public class Shot extends MovingEntity {
	/* Champs pour donner size par defaut dans la EntityFactory */
	/* a voir comment les shots sont créés (factory ?) */
	final static int SHOT_WIDTH = 1;
	final static int SHOT_HEIGHT = 1;

	public static final int SHOT_HEALTH = 100;
	public static final int SHOT_SPEED = 100;

	public static final long SHOT_EGG_TIME = 1000;
	public static final long SHOT_GET_TIME = 1000;
	public static final long SHOT_HIT_TIME = 1000;
	public static final long SHOT_JUMP_TIME = 1000;
	public static final long SHOT_EXPLODE_TIME = 1000;
	public static final long SHOT_MOVE_TIME = 2000;
	public static final long SHOT_PICK_TIME = 1000;
	public static final long SHOT_POP_TIME = 10000;
	public static final long SHOT_POWER_TIME = 1000;
	public static final long SHOT_PROTECT_TIME = 1000;
	public static final long SHOT_STORE_TIME = 1000;
	public static final long SHOT_TURN_TIME = 1000;
	public static final long SHOT_THROW_TIME = 1000;
	public static final long SHOT_WAIT_TIME = 50;
	public static final long SHOT_WIZZ_TIME = 1000;

	public Shot(int x, int y, int width, int height, int health, int speed, Automaton aut) {
		super(x, y, width, height, health, speed, aut);
		System.out.println("un shot a été créé");
	}

	int m_damage;
	
	@Override
	public void step(long elapsed) {
	//	System.out.println("Le shot fait un step");
		super.step(elapsed);
	}
	
	@Override
	public void Move(MyDirection dir) {
		//System.out.println("Le Shot avance !");
		System.out.println("il est en position " + m_x + ";" + m_y);
			if (m_actionFinished && m_currentAction == LsAction.Move) {
				
				this.doMove(dir);
				m_actionFinished = false;
				m_currentAction = null;
			} else if (m_currentAction == null) {
				m_currentActionDir = dir;
				m_currentAction = LsAction.Move;
				m_timeOfAction = SHOT_MOVE_TIME;
			}
	}
	
	@Override
	public void Explode() {
			if (m_actionFinished && m_currentAction == LsAction.Move) {
				System.out.println("Le Shot Explose !");
				this.doExplode();
				m_actionFinished = false;
				m_currentAction = null;
			} else if (m_currentAction == null) {
				m_currentAction = LsAction.Explode;
				m_timeOfAction = SHOT_EXPLODE_TIME;
			}
	}
	
	@Override
	public void collide(){
		this.Explode();
	}

}
