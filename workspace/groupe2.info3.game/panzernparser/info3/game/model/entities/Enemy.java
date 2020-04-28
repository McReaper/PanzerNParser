package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.entities.EntityFactory.MyEntities;

public class Enemy extends MovingEntity {
	/*
	 * Champs pour donner size par defaut dans la EntityFactory. A voir si on donne
	 * des size differentes pour des categories d'ennemis differents
	 */
	public final static int ENEMY_WIDTH = 1;
	public final static int ENEMY_HEIGHT = 1;

	public static final int ENEMY_HEALTH = 100;
	public static final int ENEMY_SPEED = 100;

	public static final long ENEMY_EGG_TIME = 1000;
	public static final long ENEMY_GET_TIME = 1000;
	public static final long ENEMY_HIT_TIME = 1000;
	public static final long ENEMY_JUMP_TIME = 1000;
	public static final long ENEMY_EXPLODE_TIME = 1000;
	public static final long ENEMY_MOVE_TIME = 1000;
	public static final long ENEMY_PICK_TIME = 1000;
	public static final long ENEMY_POP_TIME = 10000;
	public static final long ENEMY_POWER_TIME = 1000;
	public static final long ENEMY_PROTECT_TIME = 1000;
	public static final long ENEMY_STORE_TIME = 1000;
	public static final long ENEMY_TURN_TIME = 0;
	public static final long ENEMY_THROW_TIME = 1000;
	public static final long ENEMY_WAIT_TIME = 50;
	public static final long ENEMY_WIZZ_TIME = 1000;

//	private boolean m_triggered; // indique si l'ennemi a détecté le joueur ou non.
//	private Droppable m_drops;
	private int m_health;

	public Enemy(int x, int y, int width, int height, Automaton aut) {
		super(x, y, width, height, ENEMY_HEALTH, ENEMY_SPEED, aut);
		m_health = ENEMY_HEALTH;
	}
	
	@Override
	public void step(long elapsed) {
		super.step(elapsed);
	}
	
	@Override
	public void Hit(MyDirection dir) {
			if (m_actionFinished && m_currentAction == LsAction.Hit) {
				System.out.println("FIRE !");
				m_actionFinished = false;
				m_currentAction = null;
			} else if (m_currentAction == null) {
				m_currentActionDir = dir;
				m_currentAction = LsAction.Hit;
				m_timeOfAction = ENEMY_HIT_TIME;
				
				//creation du shot
				Entity ent = EntityFactory.newEntity(MyEntities.Shot, this.m_x , m_y );
				
				//Donne la direction de regard et d'action
				ent.setLookDir(this.m_currentLookAtDir);
				ent.setActionDir(this.m_currentActionDir);
				
				//Donne l'entité qui l'a tiré (ici le tankBody)
				((Shot ) ent).setOwner(this);
				
				//Ajoute l'entité au model
				Model.getModel().addEntity(ent);
			}
	}
	
	@Override
	public void Move(MyDirection dir) {
			if (m_actionFinished && m_currentAction == LsAction.Move) {
				
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
						m_timeOfAction = ENEMY_MOVE_TIME;
						break;
					case NORTHEAST:
					case NORTHWEST:
					case SOUTHEAST:
					case SOUTHWEST:
						m_timeOfAction =(long) Math.sqrt(2*ENEMY_MOVE_TIME*ENEMY_MOVE_TIME);
						
				}
				m_currentActionDir = dir;
				m_currentAction = LsAction.Move;
				m_timeOfAction = ENEMY_MOVE_TIME;
			}
	}
	
	
	@Override
	public void Turn(MyDirection dir, int angle) {
			if (m_actionFinished && m_currentAction == LsAction.Turn) {
				System.out.println("Turret turning !");
				this.doTurn(dir);
				m_actionFinished = false;
				m_currentAction = null;
			} else if (m_currentAction == null) {
				m_currentActionDir = dir;
				m_currentAction = LsAction.Turn;
				m_timeOfAction = ENEMY_TURN_TIME;
			}
	}
	
	@Override
	public void Explode() {
			if (m_actionFinished && m_currentAction == LsAction.Explode) {
				System.out.println("L'enemy Explose !");
				this.doExplode();
				m_actionFinished = false;
				m_currentAction = null;
			} else if (m_currentAction == null) {
				m_currentAction = LsAction.Explode;
				m_timeOfAction = ENEMY_EXPLODE_TIME;
			}
	}
	
	@Override
	public boolean GotPower() {
		if (m_health>0) {
			return true;
		}
		return false;
	}
	
	@Override
	public void collide() {
		m_health -= 25;
	}

}
