package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.LsKey;
import info3.game.automaton.MyCategory;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Tank;
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

	public static final int GUN_BULLET_SLOW = 0;
	public static final int GUN_BULLET_FAST = 1;
	public static final int GUN_BIG_BULLET = 2;

	private Tank m_tank;
	private int m_typeGun;
	private int m_nbGun;

	public Turret(int x, int y, Automaton aut) {
		super(x, y, TURRET_WIDTH, TURRET_HEIGHT, aut);
		m_tank = null;
		m_category = MyCategory.AT;
		m_typeGun = GUN_BULLET_SLOW;
		m_nbGun = 3;
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

			// creation du shot en fonction de l'arme
			int pos_x = m_x;
			int pos_y = m_y;
			switch (m_typeGun) {
				case GUN_BIG_BULLET:
					pos_x = m_x + m_width / 2 - ShotBig.SHOTBIG_WIDTH / 2;
					pos_y = m_y + m_height / 2 - ShotBig.SHOTBIG_HEIGHT / 2;
					break;
				case GUN_BULLET_FAST:
					pos_x = m_x + m_width / 2 - ShotFast.SHOTFAST_WIDTH / 2;
					pos_y = m_y + m_height / 2 - ShotFast.SHOTFAST_HEIGHT / 2;
					break;
				case GUN_BULLET_SLOW:
					pos_x = m_x + m_width / 2 - ShotSlow.SHOTSLOW_WIDTH / 2;
					pos_y = m_y + m_height / 2 - ShotSlow.SHOTSLOW_HEIGHT / 2;
					break;

			}
			Entity ent = EntityFactory.newEntityShot(MyEntities.Shot, pos_x, pos_y, m_typeGun);

			// Donne la direction de regard et d'action
			ent.setLookDir(MyDirection.toAbsolute(this.m_currentLookAtDir, dir));
			ent.setActionDir(MyDirection.toAbsolute(this.m_currentActionDir, dir));

			// Donne l'entité qui l'a tiré (ici le tankBody)
			((Shot) ent).setOwner(m_tank.getBody());
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
			m_typeGun = changeGun();
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

	public int getWeapon() {
		return m_typeGun;
	}

	private int changeGun() {
		m_typeGun++;
		return m_typeGun % m_nbGun;
	}

	private void printConsolGun() {
		switch (m_typeGun) {
			case GUN_BULLET_SLOW:
				System.out.println("Changement d'arme pour GUN_BULLET_SLOW");
				break;
			case GUN_BULLET_FAST:
				System.out.println("Changement d'arme pour GUN_BULLET_FAST");
				break;
			case GUN_BIG_BULLET:
				System.out.println("Changement d'arme pour GUN_BIG_BULLET");
				break;
			default:
				System.out.println("Arme non reconnue");
				break;

		}
	}

}
