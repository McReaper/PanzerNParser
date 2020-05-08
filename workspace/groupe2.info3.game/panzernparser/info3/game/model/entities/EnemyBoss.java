package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyCategory;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.EntityFactory.MyEntities;

public class EnemyBoss extends Enemy {

	public static final int ENEMYBOSS_WIDTH = 3;
	public static final int ENEMYBOSS_HEIGHT = 3;

	public static final int ENEMYBOSS_FOV = 10;
	public static final int ENEMYBOSS_DAMMAGE_DEALT = 25;
	public static final int ENEMYBOSS_HEALTH = 500;
	public static final int ENEMYBOSS_SPEED = 2000;

	public static final int ENEMYBOSS_HIT_TIME = 1000;
	public static final int ENEMYBOSS_TURN_TIME = 1000;
	public static final int ENEMYBOSS_POP_TIME = 200;

	public static final int ENEMYBOSS_DROP_QUANTITY_MIN = 30;
	public static final int ENEMYBOSS_DROP_QUANTITY_MAX = 70;
	
	public EnemyBoss(int x, int y, Automaton aut) {
		super(x, y, ENEMYBOSS_WIDTH, ENEMYBOSS_HEIGHT, aut);
		m_category = MyCategory.A;
		m_range = ENEMYBOSS_FOV;
		m_damage_dealt = ENEMYBOSS_DAMMAGE_DEALT;
		m_speed = ENEMYBOSS_SPEED;
		levelUp();
		m_moveSound = "moveBigEnemy2";
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
			m_timeOfAction = ENEMYBOSS_HIT_TIME;
			Model.getModel().addSound("shotBossComplete");
			// creation du shot
			Entity ent = EntityFactory.newEntity(MyEntities.ShotEnemyBoss, m_x, m_y);
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
			int rand = (int) ((Math.random() * ((ENEMYBOSS_DROP_QUANTITY_MAX - ENEMYBOSS_DROP_QUANTITY_MIN) + 1))
					+ ENEMYBOSS_DROP_QUANTITY_MIN);
			rand *= Model.getModel().getLevel();
			((Droppable) ent).setQuantity(rand);
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Egg;
			m_timeOfAction = DEFAULT_EGG_TIME;
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
			m_timeOfAction = ENEMYBOSS_TURN_TIME;
		}
	}

	@Override
	public void levelUp() {
		setMaxHealth((int) (ENEMYBOSS_HEALTH + ENEMYBOSS_HEALTH * 0.5 * (Model.getModel().getLevel() - 1)));
		m_health = getMaxHealth();
		setSpeed((int) (ENEMYBOSS_SPEED - ENEMYBOSS_SPEED * 0.2 * (Model.getModel().getLevel() - 1)));
		m_range += Model.getModel().getLevel();
		m_damage_dealt = (int) (ENEMYBOSS_DAMMAGE_DEALT
				+ ENEMYBOSS_DAMMAGE_DEALT * 0.5 * (Model.getModel().getLevel() - 1));
	}

	@Override
	public void Wizz(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			switch (dir) {
				case NORTH:
				case SOUTH:
				case EAST:
				case WEST:
					Entity ent1 = EntityFactory.newEntity(MyEntities.ShotEnemyLevel2, m_x + 1, m_y - 1);
					((ShotEnemy) ent1).setLookDir(MyDirection.NORTH);
					((ShotEnemy) ent1).setDamage(m_damage_dealt / 2);
					Entity ent2 = EntityFactory.newEntity(MyEntities.ShotEnemyLevel2, m_x + 1, m_y + 3);
					((ShotEnemy) ent2).setLookDir(MyDirection.SOUTH);
					((ShotEnemy) ent2).setDamage(m_damage_dealt / 2);
					Entity ent3 = EntityFactory.newEntity(MyEntities.ShotEnemyLevel2, m_x + 3, m_y + 1);
					((ShotEnemy) ent3).setLookDir(MyDirection.EAST);
					((ShotEnemy) ent3).setDamage(m_damage_dealt / 2);
					Entity ent4 = EntityFactory.newEntity(MyEntities.ShotEnemyLevel2, m_x - 1, m_y + 1);
					((ShotEnemy) ent4).setLookDir(MyDirection.WEST);
					((ShotEnemy) ent4).setDamage(m_damage_dealt / 2);
					break;
				case NORTHEAST:
				case NORTHWEST:
				case SOUTHEAST:
				case SOUTHWEST:
					Entity ent5 = EntityFactory.newEntity(MyEntities.ShotEnemyLevel2, m_x - 1, m_y - 1);
					((ShotEnemy) ent5).setLookDir(MyDirection.NORTHWEST);
					((ShotEnemy) ent5).setDamage(m_damage_dealt / 2);
					Entity ent6 = EntityFactory.newEntity(MyEntities.ShotEnemyLevel2, m_x + 3, m_y - 1);
					((ShotEnemy) ent6).setLookDir(MyDirection.NORTHEAST);
					((ShotEnemy) ent6).setDamage(m_damage_dealt / 2);
					Entity ent7 = EntityFactory.newEntity(MyEntities.ShotEnemyLevel2, m_x + 3, m_y + 3);
					((ShotEnemy) ent7).setLookDir(MyDirection.SOUTHEAST);
					((ShotEnemy) ent7).setDamage(m_damage_dealt / 2);
					Entity ent8 = EntityFactory.newEntity(MyEntities.ShotEnemyLevel2, m_x - 1, m_y + 3);
					((ShotEnemy) ent8).setLookDir(MyDirection.SOUTHWEST);
					((ShotEnemy) ent8).setDamage(m_damage_dealt / 2);
					break;
				default:
					break;
			}

			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = ENEMYBOSS_HIT_TIME;
		}
	}

	@Override
	public void Pop(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Pop) {
			m_actionFinished = false;
			m_currentAction = null;
			m_health += 30;
			if(m_health > ENEMYBOSS_HEALTH) m_health = ENEMYBOSS_HEALTH;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Pop;
			m_timeOfAction = ENEMYBOSS_HIT_TIME;
		}
	}

}
