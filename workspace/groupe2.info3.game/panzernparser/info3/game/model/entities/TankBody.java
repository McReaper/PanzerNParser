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

	public final static int TANKBODY_WIDTH = Tank.TANK_WIDTH;
	public final static int TANKBODY_HEIGHT = Tank.TANK_HEIGHT;

	public static final int TANKBODY_HEALTH = Tank.TANK_HEALTH;
	public static final int TANKBODY_SPEED = Tank.TANK_SPEED;

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

	public static final int TANKBODY_DAMMAGE_DEALT= 100;
	private static final MyDirection NORTH = null;

	private Tank m_tank;

	public TankBody(int x, int y, Automaton aut) {
		super(x, y, TANKBODY_WIDTH, TANKBODY_HEIGHT, aut);
		m_maxHealth=TANKBODY_HEALTH;
		m_tank = null;
		m_category = MyCategory.AT;
		m_level = 1;
		m_dammage_dealt = TANKBODY_DAMMAGE_DEALT;
		m_speed = TANKBODY_SPEED;
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
			
			//recup de la case sur laquelle on creuse
			int posX = 0;
			int posY =0;
			MyDirection AbsoluteDir = MyDirection.toAbsolute(m_currentLookAtDir, dir);
			switch (AbsoluteDir) {
				case NORTH:
					posX = m_x + (m_width -1)/2;//ce sera un peu décalé vers la gauche si m_width est pair
					posY = m_y -1;
					break;
				case SOUTH : 
					posX = m_x + (m_width -1)/2;//ce sera un peu décalé vers la gauche si m_width est pair
					posY = m_y + m_width;
					break;
				case EAST : 
					posX = m_x + m_width;
					posY = m_y + (m_height -1)/2;//ce sera un peu décalé vers le haut si m_height est pair
					break;
				case WEST : 
					posX = m_x - 1;
					posY = m_y + (m_height -1)/2;//ce sera un peu décalé vers le haut si m_height est pair
					break;
				case NORTHEAST:
					posX = m_x + m_width;
					posY = m_y -1;
					break;
				case SOUTHEAST:
					posX = m_x + m_width;
					posY = m_y + m_height;
					break;
				case NORTHWEST:
					posX = m_x -1;
					posY = m_y -1;
					break;
				case SOUTHWEST:
					posX = m_x -1;
					posY = m_y + m_height;
					break;
			}
			LinkedList<Entity> entities = Model.getModel().getGrid().getEntityCell(posX, posY);
			for (Entity ent : entities) {
				if ( ent instanceof Vein) {
					System.out.println("je creuse sur une vein");
					//creation de la droppable lorsqu'on a fini de creuser
						Model.getModel().getGrid().removeEntity(ent);//suppression de l'entitté de la grid
						Model.getModel().removeEntity(ent);//suppresion de la vein dans la liste d'ent du model
					
						Entity drop = EntityFactory.newEntity(MyEntities.Droppable, posX, posY);
						((Droppable) drop).setQuantity(10);//TODO a mettre dans une variable
					}
			}

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
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_tank.setLife(Tank.TANK_HEALTH);// je redonne de la vie le temps qu'on a pas fait le cas de GAME OVER
			// m_tank.doExplode();
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
			LinkedList<Entity> clues = Model.getModel().getCategoried(MyCategory.C);
			for (Entity ent : pickables) {
				// vérifie si le pickable est dans la zone notre tank
				if (ent instanceof Droppable) {
					if (isPickable(ent)) {
						m_tank.getInventory().add(((Droppable) ent).getMType(), ((Droppable) ent).getQuantity());// on le met dans
																																																			// l'inventaire
						Model.getModel().removeEntity(ent);// et il disparait de la liste des entités du model.
						System.out.println("Dans l'inventaire il y a "
								+ m_tank.getInventory().getQuantity(((Droppable) ent).getMType()) + " matériaux ");
					}
				} 
			}
			for (Entity ent : clues) {
				// vérifie si le pickable est dans la zone notre tank
				if (ent instanceof Marker) {
					if (isPickable(ent)) {
						Model.getModel().removeEntity(ent);// et il disparait de la liste des entités du model.
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
	public boolean GotPower() {
		return m_tank.gotPower();
	}
	
}
