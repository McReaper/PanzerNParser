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
	public static final int TURRET_WEAPON1_DAMAGE = 50;
	public static final int TURRET_WEAPON2_DAMAGE = 70;
	public static final int TURRET_WEAPON3_DAMAGE = 100;

	public static final long TURRET_WEAPON1_HIT_TIME = 600;
	public static final long TURRET_WEAPON2_HIT_TIME = 500;
	public static final long TURRET_WEAPON3_HIT_TIME = 900;
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
	private double m_damage_factor;

	public Turret(int x, int y, Automaton aut) {
		super(x, y, TURRET_WIDTH, TURRET_HEIGHT, aut);
		m_tank = null;
		m_category = MyCategory.V;

		m_indexCurrentWeapon = 0;
		m_nbWeaponsDispo = TURRET_NB_WEAPONS_DISPO_INIT;
		m_weapons = new Weapon[TURRET_NB_WEAPONS_MAX];
		initTabsWeapons();
		m_currentWeapon = m_weapons[0];
		m_damage_factor = 1.0;
	}

	private void initTabsWeapons() {
		m_weapons[0] = new WeaponBasic(this);
		m_weapons[1] = new WeaponLevel2(this);
		m_weapons[2] = new WeaponLevel3(this);
	}

	public void increaseMaxAmmo(double factor) {
		for (int i = 0; i < TURRET_NB_WEAPONS_MAX; i++) {
			m_weapons[i].improveMagazin(factor);
		}
	}

	public void setTank(Tank tank) {
		m_tank = tank;
	}

	public Tank getTank() {
		return m_tank;
	}

	@Override
	public int getDamageDealt() {
		switch (m_indexCurrentWeapon) {
			case 1:
				return (int) (TURRET_WEAPON2_DAMAGE * m_damage_factor);
			case 2:
				return (int) (TURRET_WEAPON3_DAMAGE * m_damage_factor);
			default:
				return (int) (TURRET_WEAPON1_DAMAGE * m_damage_factor);
		}
	}

	public void increaseDamageFactor(double factor) {
		m_damage_factor += factor;
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
			throw new IllegalAccessException("Impossible de débloquer une nouvelle arme.");
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
			switch (m_indexCurrentWeapon) {
				case 1:
					m_timeOfAction = TURRET_WEAPON2_HIT_TIME;
				case 2:
					m_timeOfAction = TURRET_WEAPON3_HIT_TIME;
				default:
					m_timeOfAction = TURRET_WEAPON1_HIT_TIME;
			}
			m_currentWeapon.fire(dir);
		}
	}

	@Override
	public void Pop(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Pop) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Pop;
			Model.getModel().addSound("changeWeapon");
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
		if (m_currentWeapon.getNbShotLeft() < m_currentWeapon.getCapacity()) {
			if (m_actionFinished && m_currentAction == LsAction.Wizz) {
				m_actionFinished = false;
				m_currentAction = null;
				m_currentWeapon.reload();

				Model.getModel().addSound("endReload2");
			} else if (m_currentAction == null) {
				m_currentActionDir = dir;
				m_currentAction = LsAction.Wizz;
				m_timeOfAction = m_currentWeapon.getReloadTime();
				Model.getModel().addSound("reloadTurret2");
			}
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
