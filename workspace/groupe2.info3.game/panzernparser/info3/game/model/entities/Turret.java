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
import info3.game.model.entities.EntityFactory.MyEntities;

/**
 * Classe du canon du tank
 */
public class Turret extends StaticEntity {

	public final static int TURRET_WIDTH = Tank.TANK_WIDTH;
	public final static int TURRET_HEIGHT = Tank.TANK_HEIGHT;

	public static final int TURRET_HEALTH = Tank.TANK_HEALTH;
	public static final int TURRET_SPEED = Tank.TANK_SPEED;

	public static final long TURRET_EGG_TIME = 1000;
	public static final long TURRET_GET_TIME = 1000;
	public static final long TURRET_HIT_TIME = 500;
	public static final long TURRET_JUMP_TIME = 1000;
	public static final long TURRET_EXPLODE_TIME = 1000;
	public static final long TURRET_MOVE_TIME = 1000;
	public static final long TURRET_PICK_TIME = 1000;
	public static final long TURRET_POP_TIME = 500;
	public static final long TURRET_POWER_TIME = 1000;
	public static final long TURRET_PROTECT_TIME = 1000;
	public static final long TURRET_STORE_TIME = 1000;
	public static final long TURRET_TURN_TIME = 200;
	public static final long TURRET_THROW_TIME = 1000;
	public static final long TURRET_WAIT_TIME = 0;
	public static final long TURRET_WIZZ_TIME = 1000;
	
	public static final int TURRET_NB_WEAPONS_MAX = 3;
	public static final int TURRET_NB_WEAPONS_DISPO_INIT = 2;

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
		
	//	m_currentWeapon = new Weapon();
	}

	private void initTabsWeapons() {
		m_weapons[0]= new WeaponBasic(this);
		m_weapons[1]= new WeaponBasic(this);
		m_weapons[2]= new WeaponBasic(this);
	}

	public void setTank(Tank tank) {
		m_tank = tank;
	}

	public Tank getTank() {
		return m_tank;
	}

	@Override
	public void Hit(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Hit) {
			m_actionFinished = false;
			m_currentAction = null;
			Model.getModel().addSound("Canon");
		} else if (m_currentAction == null) {
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
			printConsolGun();
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
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = TURRET_WIZZ_TIME;
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

	private void printConsolGun() {
//		switch (m_typeGun) {
//			case GUN_BULLET_SLOW:
//				System.out.println("Changement d'arme pour GUN_BULLET_SLOW");
//				break;
//			case GUN_BULLET_FAST:
//				System.out.println("Changement d'arme pour GUN_BULLET_FAST");
//				break;
//			case GUN_BIG_BULLET:
//				System.out.println("Changement d'arme pour GUN_BIG_BULLET");
//				break;
//			default:
//				System.out.println("Arme non reconnue");
//				break;
//
//		}
	}

}
