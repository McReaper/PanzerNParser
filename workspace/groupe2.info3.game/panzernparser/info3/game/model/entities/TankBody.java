package info3.game.model.entities;

import java.util.LinkedList;

import info3.game.automaton.Automaton;
import info3.game.automaton.LsKey;
import info3.game.automaton.MyCategory;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Grid;
import info3.game.model.Model;
import info3.game.model.Tank;
import info3.game.model.entities.EntityFactory.MyEntities;

/**
 * Chassis du tank
 */
public class TankBody extends MovingEntity {

	public final static int TANKBODY_WIDTH = Tank.TANK_WIDTH;
	public final static int TANKBODY_HEIGHT = Tank.TANK_HEIGHT;

	public static final int TANKBODY_HEALTH = Tank.TANK_HEALTH;
	public static final int TANKBODY_SPEED = Tank.TANK_SPEED;

	public static final long TANKBODY_EXPLODE_TIME = 1000;
	public static final long TANKBODY_PICK_TIME = 0;
	public static final long TANKBODY_POP_TIME = 3000;
	public static final long TANKBODY_TURN_TIME = 0;
	public static final long TANKBODY_WAIT_TIME = 50;
	public static final long TANKBODY_WIZZ_TIME = 1000;
	public static final int TANKBODY_DAMMAGE_DEALT = 100;

	private Tank m_tank;
	private long m_miningTime;

	public TankBody(int x, int y, Automaton aut) {
		super(x, y, TANKBODY_WIDTH, TANKBODY_HEIGHT, aut);
		m_maxHealth = TANKBODY_HEALTH;
		m_health = TANKBODY_HEALTH;
		m_tank = null;
		m_category = MyCategory.AT;
		m_speed = TANKBODY_SPEED;
		m_miningTime = TANKBODY_POP_TIME;
		m_moveSound = "moveTank2";
		m_range = Tank.TANK_FOV;
	}

	@Override
	public void Move(MyDirection dir) {
		if (m_tank.hasControl()) {
			super.Move(dir);
		}
	}

	public void setTank(Tank tank) {
		m_tank = tank;
	}

	@Override
	public void Pop(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Pop) {

			// recup de la case sur laquelle on creuse
			int posX = getXCaseDir(dir);
			int posY = getYCaseDir(dir);
			LinkedList<Entity> entities = Model.getModel().getGrid().getEntityCell(posX, posY);
			for (Entity ent : entities) {
				if (ent instanceof Vein) {
					// La veine doit egg un droppable via son avatar
					ent.setStuff(true);
				}
			}
			m_actionFinished = false;
			m_currentAction = null;

		} else if (m_currentAction == null) {
			Model.getModel().addSound("tankDig");
			m_currentActionDir = dir;
			m_currentAction = LsAction.Pop;
			m_timeOfAction = m_miningTime;
			int posX = getXCaseDir(dir);
			int posY = getYCaseDir(dir);
			Entity hole = EntityFactory.newEntity(MyEntities.Hole, posX, posY);
			// le trou doit pop via son avatar
			hole.setStuff(true);
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
			m_timeOfAction = TANKBODY_TURN_TIME;
		}
	}

	@Override
	public void Wizz(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			Model.getModel().switchControl();
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			Drone d = Model.getModel().getDrone();
			if (d.getHealth() >= d.getMaxHealth() / 5) {
				m_currentActionDir = dir;
				Model.getModel().addSound("tankToDrone");
				m_currentAction = LsAction.Wizz;
				m_timeOfAction = TANKBODY_WIZZ_TIME;
			}
		}
	}

	@Override
	public void Explode() {
		if (m_actionFinished && m_currentAction == LsAction.Explode) {
			m_actionFinished = false;
			m_currentAction = null;
			m_tank.doExplode();
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Explode;
			m_timeOfAction = TANKBODY_EXPLODE_TIME;
			Model.getModel().addSound("explosion");
			doDead();
		} else if (m_currentAction == LsAction.Explode && (this.getActionProgress() * 12) >= 5) {
		}
	}

	private void doDead() {
		Model.getModel().addSound("deg");
		Entity wreck = EntityFactory.newEntity(MyEntities.WreckTank, m_x, m_y);
		switch (m_currentLookAtDir) {
			case SOUTH:
			case NORTH:
			case NORTHEAST:
			case SOUTHWEST:
				wreck.m_currentLookAtDir = MyDirection.SOUTH;
				break;
			case WEST:
			case EAST:
			case NORTHWEST:
			case SOUTHEAST:
			default:
				wreck.m_currentLookAtDir = MyDirection.WEST;
				break;
		}
	}

	@Override
	public void Pick(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Pick) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Pick;
			m_timeOfAction = TANKBODY_PICK_TIME;
			Grid g = Model.getModel().getGrid();
			LinkedList<Entity> e;
			for (int i = m_x; i < m_x + m_width; i++) {
				for (int j = m_y; j < m_y + m_height; j++) {
					e = g.getEntityCell(i, j);
					for (Entity entity : e) {
						if (entity instanceof Marker) {
							Model.getModel().removeEntity(entity);
							Model.getModel().getDrone().decreaseMarksNb();
						} else if (entity instanceof Droppable) {
							m_tank.getInventory().add((Droppable) entity);
							Model.getModel().addSound("pickDroppable");
							Model.getModel().removeEntity(entity);
							Model.getModel().getScore().scoreDroppable();
						}
					}
				}
			}

		}
	}

	@Override
	public void collide(int dammage) {
		m_tank.setLife(m_tank.getLife() - dammage);
	}

	@Override
	public boolean Key(LsKey key) {
		if (m_tank.hasControl())
			return super.Key(key);
		return false;
	}

	@Override
	public int getHealth() {
		return m_tank.getLife();
	}

	@Override
	public int getMaxHealth() {
		return m_tank.getMaxLife();
	}

	@Override
	public boolean GotPower() {
		return m_tank.gotPower();
	}

	public long getMiningTime() {
		return m_miningTime;
	}

	public void setMiningTime(long time) {
		m_miningTime = time;
	}

}
