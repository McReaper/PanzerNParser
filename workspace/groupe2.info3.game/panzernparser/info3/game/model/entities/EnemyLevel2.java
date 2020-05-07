package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyCategory;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.EntityFactory.MyEntities;

public class EnemyLevel2 extends Enemy {
	public final static int ENEMYLEVEL2_WIDTH = 2;
	public final static int ENEMYLEVEL2_HEIGHT = 2;

	public static final int ENEMYLEVEL2_HEALTH = 100;
	public static final int ENEMYLEVEL2_SPEED = 1000;
	public static final int ENEMYLEVEL2_FOV = 4;

	public static final long ENEMYLEVEL2_EGG_TIME = 0;
	public static final long ENEMYLEVEL2_HIT_TIME = 500;
	public static final long ENEMYLEVEL2_EXPLODE_TIME = 1000;
	public static final long ENEMYLEVEL2_MOVE_TIME = 1000;
	public static final long ENEMYLEVEL2_POP_TIME = 1000;
	public static final long ENEMYLEVEL2_TURN_TIME = 0;
	public static final long ENEMYLEVEL2_WAIT_TIME = 50;
	public static final long ENEMYLEVEL2_WIZZ_TIME = 1000;

	public static final int ENEMYLEVEL2_DAMMAGE_DEALT = 50;

	public EnemyLevel2(int x, int y, Automaton aut) {
		super(x, y, ENEMYLEVEL2_WIDTH, ENEMYLEVEL2_HEIGHT, aut);
		m_range = ENEMYLEVEL2_FOV;
		m_damage_dealt = ENEMYLEVEL2_DAMMAGE_DEALT;
		m_speed = ENEMYLEVEL2_SPEED;
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
			Model.getModel().addSound("hit_pompe2");
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Hit;
			m_timeOfAction = ENEMYLEVEL2_HIT_TIME;
			// creation des shot
			Entity ent1;
			Entity ent2;
			Model.getModel().addSound("hit_pompe");
			switch (m_currentLookAtDir) {
				case SOUTH:
				case NORTH:
					ent1 = EntityFactory.newEntity(MyEntities.ShotFast, m_x, m_y);
					ent2 = EntityFactory.newEntity(MyEntities.ShotFast, m_x + 1, m_y);
					break;
				case WEST:
				case EAST:
					ent1 = EntityFactory.newEntity(MyEntities.ShotFast, m_x, m_y);
					ent2 = EntityFactory.newEntity(MyEntities.ShotFast, m_x, m_y + 1);
					break;
				case SOUTHEAST:
				case NORTHWEST:
					ent1 = EntityFactory.newEntity(MyEntities.ShotFast, m_x + 1, m_y);
					ent2 = EntityFactory.newEntity(MyEntities.ShotFast, m_x, m_y + 1);
					break;
				case SOUTHWEST:
				case NORTHEAST:
					ent1 = EntityFactory.newEntity(MyEntities.ShotFast, m_x, m_y);
					ent2 = EntityFactory.newEntity(MyEntities.ShotFast, m_x + 1, m_y + 1);
					break;
				default:
					ent1 = EntityFactory.newEntity(MyEntities.ShotFast, m_x, m_y);
					ent2 = EntityFactory.newEntity(MyEntities.ShotFast, m_x, m_y);
					break;
			}

			// Donne la direction de regard et d'action
			ent1.setLookDir(this.m_currentLookAtDir);
			ent1.setActionDir(this.m_currentActionDir);

			ent2.setLookDir(this.m_currentLookAtDir);
			ent2.setActionDir(this.m_currentActionDir);

			// Donne l'entité qui l'a tiré
			((Shot) ent1).setOwner(this);
			((Shot) ent2).setOwner(this);
		}
	}

	@Override
	public void Egg(MyDirection dir) {
		// On ignore dir ici (tout le temps HERE)...
		if (m_actionFinished && m_currentAction == LsAction.Egg) {
			m_actionFinished = false;
			m_currentAction = null;
			// creation de la ressource a répendre

			// creation de la ressource a répendre
			if (dir == null || dir == MyDirection.HERE) {
				Entity ent = EntityFactory.newEntity(MyEntities.Droppable, m_x, m_y);
				int rand = (int) (Math.random() * (20 - 1));// 20 correspond au nombre max de ressource dispo et 1 le min
				((Droppable) ent).setQuantity(rand);
			} else {
				int posX = getXCaseDir(dir);
				int posY = getYCaseDir(dir);
				Entity ent = EntityFactory.newEntity(MyEntities.Droppable, posX, posY);
				int rand = (int) (Math.random() * (20 - 1));// 20 correspond au nombre max de ressource dispo et 1 le min
				((Droppable) ent).setQuantity(rand);

			}
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
			m_timeOfAction = ENEMYLEVEL2_TURN_TIME;
		}
	}

	@Override
	public void Explode() {
		if (m_actionFinished && m_currentAction == LsAction.Explode) {
			this.doExplode();
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Explode;
			Model.getModel().addSound("explosion");
			m_timeOfAction = ENEMYLEVEL2_EXPLODE_TIME;
		}
	}

	@Override
	public void Wizz(MyDirection dir) {// Divise la vittesse par 2
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = ENEMYLEVEL2_WIZZ_TIME;
			m_speed *= 2;
		}
	}

	@Override
	public void Pop(MyDirection dir) {// Divise les damage_dealt par 2
		if (m_actionFinished && m_currentAction == LsAction.Pop) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = ENEMYLEVEL2_POP_TIME;
			m_damage_dealt /= 2;
		}
	}
	
	public void levelUp() {/* l'enemy basic bouge peu mais fait très mal*/
		if (Model.getModel().getLevel() % 4 == 0) {
			setMaxHealth(ENEMYLEVEL2_HEALTH*3);
			m_health = getMaxHealth();
			m_range+=2;
			m_damage_dealt = (Model.getModel().getLevel()/3 + 2)* ENEMYLEVEL2_DAMMAGE_DEALT; 
		}
	}

}
