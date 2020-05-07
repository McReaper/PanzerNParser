package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.LsKey;
import info3.game.automaton.MyCategory;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Tank;
import info3.game.model.Weapon;
import info3.game.model.WeaponBasic;
import info3.game.model.WeaponLevel2;
import info3.game.model.WeaponLevel3;

/**
 * Classe du canon du tank
 */
public class Turret extends StaticEntity {

	public final static int TURRET_WIDTH = Tank.TANK_WIDTH;
	public final static int TURRET_HEIGHT = Tank.TANK_HEIGHT;

	public static final int TURRET_HEALTH = Tank.TANK_HEALTH;

	public static final long TURRET_HIT_TIME = 500;
	public static final long TURRET_POP_TIME = 500;
	public static final long TURRET_TURN_TIME = 150;
	public static final long TURRET_WAIT_TIME = 10;

	public static final int TURRET_NB_WEAPONS_MAX = 3;
	public static final int TURRET_NB_WEAPONS_DISPO_INIT = 1;

	private Tank m_tank;
	private int m_indexCurrentWeapon;
	private int m_nbWeaponsDispo;
	private Weapon[] m_weapons;
	private Weapon m_currentWeapon;

	public Turret(int x, int y, Automaton aut) {
		super(x, y, TURRET_WIDTH, TURRET_HEIGHT, aut);
		m_tank = null;
		m_category = MyCategory.V;

		m_indexCurrentWeapon = 0;
		m_nbWeaponsDispo = TURRET_NB_WEAPONS_DISPO_INIT;
		m_weapons = new Weapon[TURRET_NB_WEAPONS_MAX];
		initTabsWeapons();
		m_currentWeapon = m_weapons[0];
	}

	private void initTabsWeapons() {
		m_weapons[0] = new WeaponBasic(this);
		m_weapons[1] = new WeaponLevel2(this);
		m_weapons[2] = new WeaponLevel3(this);
	}

	public void setTank(Tank tank) {
		m_tank = tank;
	}

	public Tank getTank() {
		return m_tank;
	}

	public int getIndexWeapon() {
		return m_indexCurrentWeapon;
	}

	public boolean isWeaponUnlockable() {
		return m_nbWeaponsDispo < TURRET_NB_WEAPONS_MAX;
	}

	public void unlockNewWeapon() throws IllegalAccessException {
		m_nbWeaponsDispo++;
		if (m_nbWeaponsDispo > TURRET_NB_WEAPONS_MAX) {
			m_nbWeaponsDispo--;
			throw new IllegalAccessException("Impossible de débloqué une nouvelle arme.");
		}
	}

	@Override
	public void Hit(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Hit) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			if (isNoisy())
				Model.getModel().addSound("canon2");
			m_currentActionDir = dir;
			m_currentAction = LsAction.Hit;
			m_timeOfAction = TURRET_HIT_TIME;
			m_currentWeapon.fire(dir);
		}
	}

	@Override
	public void Pop(MyDirection dir) {// Permet le changement d'arme
		if (m_actionFinished && m_currentAction == LsAction.Pop) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Pop;
			m_currentWeapon = changeWeapon();
			m_timeOfAction = TURRET_POP_TIME;
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
			m_timeOfAction = TURRET_TURN_TIME;
		}
	}

	@Override
	public void Wizz(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			m_actionFinished = false;
			m_currentAction = null;
			Model.getModel().addSound("endReload2");
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = m_currentWeapon.getReloadTime();
			m_currentWeapon.reload();
			Model.getModel().addSound("reloadTurret2");
		}
	}

	public void Wait() {
		if (m_actionFinished && m_currentAction == LsAction.Wait) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = null;
			m_currentAction = LsAction.Wait;
			m_timeOfAction = TURRET_WAIT_TIME;
		}
	}

	@Override
	public boolean Key(LsKey key) {
		if (m_tank.hasControl())
			return super.Key(key);
		return false;
	}

	public Weapon getWeapon() {
		return m_currentWeapon;
	}

	private Weapon changeWeapon() {
		m_indexCurrentWeapon++;
		m_indexCurrentWeapon %= m_nbWeaponsDispo;
		return m_weapons[m_indexCurrentWeapon];
	}

}
