package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyCategory;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Tank;
import info3.game.model.entities.EntityFactory.MyEntities;

public class AutomaticTurret extends StaticEntity {
	public final static int AUTOMATIC_TURRET_WIDTH = 2;
	public final static int AUTOMATIC_TURRET_HEIGHT = 2;

	public static final long AUTOMATIC_TURRET_HIT_TIME = 500;
	public static final long AUTOMATIC_TURRET_POP_TIME = 50;
	public static final long AUTOMATIC_TURRET_TURN_TIME = 50;
	public static final long AUTOMATIC_TURRET_WAIT_TIME = 10;
	public static final int AUTOMATIC_TURRET_DAMAGE = 20;

	public static final int AUTOMATIC_TURRET_RANGE = 7;

	private Tank m_tank;
	private boolean m_isActivated;

	public AutomaticTurret(int x, int y, Automaton aut) {
		super(x, y, AUTOMATIC_TURRET_WIDTH, AUTOMATIC_TURRET_HEIGHT, aut);
		m_tank = null;
		m_category = MyCategory.V;
		m_isActivated = false;
		m_range = AUTOMATIC_TURRET_RANGE;
		m_damage_dealt = AUTOMATIC_TURRET_DAMAGE;
	}

	@Override
	public boolean isShown() {
		return m_isActivated && m_tank.gotPower();
	}

	public void setTank(Tank tank) {
		m_tank = tank;
	}

	public Tank getTank() {
		return m_tank;
	}

	public boolean getIsActivate() {
		return m_isActivated;
	}

	public void setIsActivate(boolean b) {
		m_isActivated = b;
	}

	@Override
	public void Hit(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Hit) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Hit;
			m_timeOfAction = AUTOMATIC_TURRET_HIT_TIME;
			// creation du shot
			if (dir == null) {
				dir = MyDirection.FRONT;
			}
			if (isNoisy())
				Model.getModel().addSound("turretShot");
			Entity ent = EntityFactory.newEntity(MyEntities.ShotFast, m_x + 1, m_y + 1);
			((Shot) ent).setDamage(this.getDamageDealt());

			// Donne la direction de regard et d'action
			ent.setLookDir(this.m_currentLookAtDir);
			ent.setActionDir(this.m_currentActionDir);

			// Donne l'entit?? qui l'a tir??
			((Shot) ent).setOwner(m_tank.getBody());
		}
	}

	@Override
	public void Pop(MyDirection dir) {// diminue la port??e de vision des ennemies
		if (m_actionFinished && m_currentAction == LsAction.Pop) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Pop;
			m_timeOfAction = AUTOMATIC_TURRET_POP_TIME;
			m_range--;
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
			m_timeOfAction = AUTOMATIC_TURRET_TURN_TIME;
		}
	}

	@Override
	public void Wizz(MyDirection dir) {// augmente la port??e de vision des ennemies
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Wizz;
			m_range++;
		}
	}

	@Override
	public void Wait() {
		if (m_actionFinished && m_currentAction == LsAction.Wait) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			//m_currentActionDir = null;
			m_currentAction = LsAction.Wait;
			m_timeOfAction = AUTOMATIC_TURRET_WAIT_TIME;
		}
	}

	@Override
	public boolean GotPower() {
		return this.m_isActivated;
	}
	
	@Override
	public int getDamageDealt() {
		return m_damage_dealt * Model.getModel().getLevel();
	}
}
