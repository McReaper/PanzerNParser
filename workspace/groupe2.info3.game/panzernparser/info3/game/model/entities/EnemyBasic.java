package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyCategory;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.EntityFactory.MyEntities;

public class EnemyBasic extends Enemy {
	public final static int ENEMYBASIC_WIDTH = 1;
	public final static int ENEMYBASIC_HEIGHT = 1;

	public static final int ENEMYBASIC_HEALTH = 100;
	public static final int ENEMYBASIC_SPEED = 700;
	public static final int ENEMYBASIC_FOV = 4;

	public static final long ENEMYBASIC_EGG_TIME = 0;
	public static final long ENEMYBASIC_HIT_TIME = 500;
	public static final long ENEMYBASIC_EXPLODE_TIME = 1000;
	public static final long ENEMYBASIC_MOVE_TIME = 1000;
	public static final long ENEMYBASIC_POP_TIME = 1000;
	public static final long ENEMYBASIC_TURN_TIME = 0;
	public static final long ENEMYBASIC_WAIT_TIME = 50;
	public static final long ENEMYBASIC_WIZZ_TIME = 1000;

	public static final int ENEMYBASIC_DAMMAGE_DEALT = 5;

	public EnemyBasic(int x, int y, Automaton aut) {
		super(x, y, ENEMYBASIC_WIDTH, ENEMYBASIC_HEIGHT, aut);
		m_category = MyCategory.A;
		m_range = ENEMYBASIC_FOV;
		m_damage_dealt = ENEMYBASIC_DAMMAGE_DEALT;
		m_speed = ENEMYBASIC_SPEED;
		levelUp();
	}

	@Override
	public void step(long elapsed) {
		m_displayed = (Model.getModel().getVisionType() != VisionType.RESSOURCES);
		super.step(elapsed);
	}

	@Override
	public void Hit(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Hit) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Hit;
			m_timeOfAction = ENEMYBASIC_HIT_TIME;

			// creation du shot
			Entity ent = EntityFactory.newEntity(MyEntities.ShotSlow, m_x, m_y);
			((ShotEnemy) ent).setDamage(m_damage_dealt);

			// Donne la direction de regard et d'action
			ent.setLookDir(this.m_currentLookAtDir);
			ent.setActionDir(this.m_currentActionDir);

			// Donne l'entité qui l'a tiré
			((ShotEnemy) ent).setOwner(this);
		}
	}

	@Override
	public void Egg(MyDirection dir) {
		// On ignore dir ici (tout le temps HERE)...
		if (m_actionFinished && m_currentAction == LsAction.Egg) {
			m_actionFinished = false;
			m_currentAction = null;
			// creation de la ressource a répendre
			Entity ent = EntityFactory.newEntity(MyEntities.Droppable, m_x, m_y);
			int rand = (int) (Math.random() * (20 - 1));// 20 correspond au nombre max de ressource dispo et 1 le min
			((Droppable) ent).setQuantity(rand);
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Egg;
			m_timeOfAction = ENEMYBASIC_EGG_TIME;
		}
	}

	@Override
	public void Turn(MyDirection dir, int angle) {
		if (m_actionFinished && m_currentAction == LsAction.Turn) {
			this.doTurn(dir);
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Turn;
			m_timeOfAction = ENEMYBASIC_TURN_TIME;
		}
	}

	@Override
	public void Wizz(MyDirection dir) {// Multiplie la vittesse par 2
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = ENEMYBASIC_WIZZ_TIME;
			m_speed /= 2;
		}
	}

	@Override
	public void Pop(MyDirection dir) {// Multiplie les damage_dealt par 2
		if (m_actionFinished && m_currentAction == LsAction.Pop) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = ENEMYBASIC_POP_TIME;
			m_damage_dealt *= 2;
		}
	}

	public void levelUp() {
		setMaxHealth((int) (ENEMYBASIC_HEALTH + ENEMYBASIC_HEALTH * 0.5 * (Model.getModel().getLevel() - 1)));
		m_health = getMaxHealth();
		setSpeed((int) (ENEMYBASIC_SPEED - ENEMYBASIC_SPEED * 0.2 * (Model.getModel().getLevel() - 1)));
		m_range += Model.getModel().getLevel();
		m_damage_dealt = (int) (ENEMYBASIC_DAMMAGE_DEALT
				+ ENEMYBASIC_DAMMAGE_DEALT * 0.5 * (Model.getModel().getLevel() - 1));
	}
}
