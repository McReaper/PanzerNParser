package info3.game.model.entities;

import java.util.LinkedList;

import info3.game.automaton.Automaton;
import info3.game.automaton.LsKey;
import info3.game.automaton.MyCategory;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Tank;
import info3.game.model.entities.EntityFactory.MyEntities;

/**
 * Chassis du tank
 */
public class TankBody extends MovingEntity {

	public final static int TANKBODY_WIDTH = 3;
	public final static int TANKBODY_HEIGHT = 3;

	public static final int TANKBODY_HEALTH = 100;
	public static final int TANKBODY_SPEED = 100;

	public static final long TANKBODY_EGG_TIME = 1000;
	public static final long TANKBODY_GET_TIME = 1000;
	public static final long TANKBODY_HIT_TIME = 1000;
	public static final long TANKBODY_JUMP_TIME = 1000;
	public static final long TANKBODY_EXPLODE_TIME = 1000;
	public static final long TANKBODY_MOVE_TIME = 800;
	public static final long TANKBODY_PICK_TIME = 50;
	public static final long TANKBODY_POP_TIME = 10000;
	public static final long TANKBODY_POWER_TIME = 1000;
	public static final long TANKBODY_PROTECT_TIME = 1000;
	public static final long TANKBODY_STORE_TIME = 1000;
	public static final long TANKBODY_TURN_TIME = 0;
	public static final long TANKBODY_THROW_TIME = 1000;
	public static final long TANKBODY_WAIT_TIME = 50;
	public static final long TANKBODY_WIZZ_TIME = 1000;

	private Tank m_tank;
	// public int m_range;

	public TankBody(int x, int y, int width, int height, Automaton aut) {
		super(x, y, width, height, Tank.TANK_HEALTH, Tank.TANK_SPEED, aut);
		m_tank = null;
		m_category = MyCategory.AT;
	}

	public void setTank(Tank tank) {
		m_tank = tank;
	}

	@Override
	public void Move(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Move) {
			// System.out.println("Tank fait le move !");
			this.doMove(dir);
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			MyDirection absoluteDir = MyDirection.toAbsolute(m_currentActionDir, dir);
			switch (absoluteDir) {
				case NORTH:
				case EAST:
				case WEST:
				case SOUTH:
					m_timeOfAction = TANKBODY_MOVE_TIME;
					break;
				case NORTHEAST:
				case NORTHWEST:
				case SOUTHEAST:
				case SOUTHWEST:
					m_timeOfAction = (long) Math.sqrt(2 * TANKBODY_MOVE_TIME * TANKBODY_MOVE_TIME);

			}
			m_currentActionDir = dir;
			m_currentAction = LsAction.Move;
		}
	}

	@Override
	public void Pop(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Pop) {
			System.out.println("Tank fait le Pop !");
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Pop;
			m_timeOfAction = TANKBODY_POP_TIME;
		}
	}

	@Override
	public void Turn(MyDirection dir, int angle) {
		if (m_actionFinished && m_currentAction == LsAction.Turn) {
			// System.out.println("Tank fait le Turn !");
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
			System.out.println("TANK fait le wizz");
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = TANKBODY_WIZZ_TIME;
		}
	}

	@Override
	public void Explode() {
		/*
		 * TODO faire un GAME OVER
		 */
		if (m_actionFinished && m_currentAction == LsAction.Explode) {
			System.out.println("Le TANK et le Canon Explose !");
//			m_tank.getTurret().doExplode();
//			this.doExplode();
			this.m_health = 100;// je redonne de la vie le temps qu'on a pas fait le cas de GAME OVER
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Explode;
			m_timeOfAction = TANKBODY_EXPLODE_TIME;
		}
	}

	@Override
	public void Pick(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Pick) {
			System.out.println("Le Tank rammasse un objet!");
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Pick;
			m_timeOfAction = TANKBODY_PICK_TIME;
			LinkedList<Entity> pickables = Model.getModel().getCategoried(MyCategory.P);
			for (Entity ent : pickables) {
				// vérifie si le pickable est dans la zone notre tank
				if (ent instanceof Droppable) {
					if (isPickable(ent)) {
						m_tank.getInventory().add(((Droppable) ent).getMType(), ((Droppable) ent).getQuantity());// on le met dans l'inventaire
						Model.getModel().removeEntity(ent);// et il disparait de la liste des entités du model.
						System.out.println("Dans l'inventaire il y a " + m_tank.getInventory().getQuantity(((Droppable) ent).getMType()) + " matériaux ");
					}
				}
			}
		}
	}

	@Override
	public boolean Key(LsKey key) {
		if (m_tank.hasControl())
			return super.Key(key);
		return false;
	}

	@Override
	public void collide() {
		m_health -= 50;
	}

	/*
	 * vérifie si l'netité pickable donné en param est sous le tank elle vérifie
	 * pour toutes les cases de l'objet à ramasser
	 */
	private boolean isPickable(Entity ent) {
		//TODO si l'objet est sur plus d'une case
//		for (int i = ent.getX(); i < ent.getX() + ent.getWidth(); i++) {
//			for (int j = ent.getY(); i < ent.getY() + ent.getHeight(); i++) {
				if (this.isInMeCase(ent.getX(), ent.getY())) {
					return true;
				}
//			}
//		}
		return false;
	}
}
