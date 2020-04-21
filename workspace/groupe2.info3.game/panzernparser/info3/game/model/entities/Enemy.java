package info3.game.model.entities;

import info3.game.automaton.action.LsAction;
import info3.game.model.Material.MaterialType;
import info3.game.model.Model;

public class Enemy extends MovingEntity {
	/*
	 * Champs pour donner size par defaut dans la EntityFactory. A voir si on donne
	 * des size differentes pour des categories d'ennemis differents
	 */
	public final static int ENEMY_WIDTH = 1;
	public final static int ENEMY_HEIGHT = 1;

	public static final int ENEMY_HEALTH = 100;
	public static final int ENEMY_SPEED = 1; // nb de cases en 1 déplacement.

	public static final long ENEMY_EGG_TIME = 1000;
	public static final long ENEMY_GET_TIME = 1000;
	public static final long ENEMY_HIT_TIME = 1000;
	public static final long ENEMY_JUMP_TIME = 1000;
	public static final long ENEMY_EXPLODE_TIME = 1000;
	public static final long ENEMY_MOVE_TIME = 2000;
	public static final long ENEMY_PICK_TIME = 1000;
	public static final long ENEMY_POP_TIME = 3000;
	public static final long ENEMY_POWER_TIME = 1000;
	public static final long ENEMY_PROTECT_TIME = 1000;
	public static final long ENEMY_STORE_TIME = 1000;
	public static final long ENEMY_TURN_TIME = 1000;
	public static final long ENEMY_THROW_TIME = 1000;
	public static final long ENEMY_WAIT_TIME = 9999999;
	public static final long ENEMY_WIZZ_TIME = 1000;

	boolean m_triggered; // indique si l'ennemi a détecté le joueur ou non.
	Droppable m_drops;

	public Enemy(int x, int y, int width, int height, Model model) {
		super(x, y, width, height, ENEMY_HEALTH, ENEMY_SPEED, model);
		m_triggered = false; // Valeur par défaut
		m_drops = new Droppable(this.m_x, this.m_y, 1, 1, 1, MaterialType.ELECTRONIC, model);
		m_currentAction = LsAction.Nothing; // TODO : a retirer, c'est pour les tests.
	}

	@Override
	public void step(long elapsed) {
		long timeOfAction = 0;
		switch (m_currentAction) {
			case Egg:
				timeOfAction = ENEMY_EGG_TIME;
				break;
			case Explode:
				timeOfAction = ENEMY_EXPLODE_TIME;
				break;
			case Get:
				timeOfAction = ENEMY_GET_TIME;
				break;
			case Hit:
				timeOfAction = ENEMY_HIT_TIME;
				break;
			case Jump:
				timeOfAction = ENEMY_JUMP_TIME;
				break;
			case Move:
				timeOfAction = ENEMY_MOVE_TIME;
				break;
			case Pick:
				timeOfAction = ENEMY_PICK_TIME;
				break;
			case Pop:
				timeOfAction = ENEMY_POP_TIME;
				break;
			case Power:
				timeOfAction = ENEMY_POWER_TIME;
				break;
			case Protect:
				timeOfAction = ENEMY_PROTECT_TIME;
				break;
			case Store:
				timeOfAction = ENEMY_STORE_TIME;
				break;
			case Throw:
				timeOfAction = ENEMY_THROW_TIME;
				break;
			case Turn:
				timeOfAction = ENEMY_TURN_TIME;
				break;
			case Wait:
				timeOfAction = ENEMY_WAIT_TIME;
				break;
			case Wizz:
				timeOfAction = ENEMY_WIZZ_TIME;
				break;
			case Nothing: // ERASE NOTHING !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				this.setState(this.m_automate.step(this));
				
					
				return; //Il ne fait rien, on sort.
		}
		
		if (m_elapseTime > timeOfAction) {
			m_elapseTime = 0;
			m_currentAction = LsAction.Nothing;
		} else {
			m_elapseTime += elapsed;
		}
	}

	@Override
	public double getActionProgress() {
		switch (m_currentAction) {
			case Egg:
				return ((double) m_elapseTime) / ((double) ENEMY_EGG_TIME);
			case Explode:
				return ((double) m_elapseTime) / ((double) ENEMY_EXPLODE_TIME);
			case Get:
				return ((double) m_elapseTime) / ((double) ENEMY_GET_TIME);
			case Hit:
				return ((double) m_elapseTime) / ((double) ENEMY_HIT_TIME);
			case Jump:
				return ((double) m_elapseTime) / ((double) ENEMY_JUMP_TIME);
			case Move:
				return ((double) m_elapseTime) / ((double) ENEMY_MOVE_TIME);
			case Nothing:
				return 0;
			case Pick:
				return ((double) m_elapseTime) / ((double) ENEMY_PICK_TIME);
			case Pop:
				return ((double) m_elapseTime) / ((double) ENEMY_POP_TIME);
			case Power:
				return ((double) m_elapseTime) / ((double) ENEMY_POWER_TIME);
			case Protect:
				return ((double) m_elapseTime) / ((double) ENEMY_PROTECT_TIME);
			case Store:
				return ((double) m_elapseTime) / ((double) ENEMY_STORE_TIME);
			case Throw:
				return ((double) m_elapseTime) / ((double) ENEMY_THROW_TIME);
			case Turn:
				return ((double) m_elapseTime) / ((double) ENEMY_TURN_TIME);
			case Wait:
				return ((double) m_elapseTime) / ((double) ENEMY_WAIT_TIME);
			case Wizz:
				return ((double) m_elapseTime) / ((double) ENEMY_WIZZ_TIME);
			default:
				return 0;
		}
	}

}
