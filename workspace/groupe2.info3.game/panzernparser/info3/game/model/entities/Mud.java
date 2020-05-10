package info3.game.model.entities;

import java.util.LinkedList;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;

public class Mud extends StaticEntity {

	public static final int MUD_WIDTH = 1;
	public static final int MUD_HEIGHT = 1;

	public static final int MUD_WIZZ_TIME = 50;
	public static final int MUD_COEFFICIENT_SPEED = 2;// indique par quel coefficient va être divisé la vitesse
	public static final int MUD_POP_TIME = 100;

	public static final int FASTER = 1;
	public static final int NORMAL = 0;
	public static final int SLOWER = -1;
	
	LinkedList<Entity> m_entityHere;

	public Mud(int x, int y, Automaton aut) {
		super(x, y, MUD_WIDTH, MUD_HEIGHT, aut);
		m_entityHere = new LinkedList<Entity>();
	}

	public void Wizz(MyDirection dir) {// La boue fait avancer moins vite
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = MUD_WIZZ_TIME;
			LinkedList<Entity> entHere = getMovingEntityHere();
			for (Entity entity : entHere) {
				if(!m_entityHere.contains(entity)) {
					m_entityHere.add(entity);
					entity.setSpeed(entity.getSpeed() * MUD_COEFFICIENT_SPEED);
					entity.setHasChangedSpeed(SLOWER);
				}
			}
		}
	}

	public void Pop(MyDirection dir) {// La boue se transforme en glace et on avance plus vite dessu
		if (m_actionFinished && m_currentAction == LsAction.Pop) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = MUD_POP_TIME;
			LinkedList<Entity> entHere = getMovingEntityHere();
			for (Entity entity : entHere) {
				if(!m_entityHere.contains(entity)) {
					m_entityHere.add(entity);
					entity.setSpeed(entity.getSpeed() / MUD_COEFFICIENT_SPEED);
					entity.setHasChangedSpeed(FASTER);
				}
			}
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
			m_timeOfAction = 0;
		}
	}
	
	private LinkedList<Entity> getMovingEntityHere() {
		LinkedList<Entity> entHere = new LinkedList<Entity>();
		LinkedList<Entity> entities = Model.getModel().getGrid().getEntityCells(m_x, m_y, m_width, m_height);
		for (Entity entity : entities) {
			if (entity instanceof TankBody || entity instanceof Enemy) {
				entHere.add(entity);
			}
		}
		return entHere;
	}
	
	@Override
	public boolean isShown() {
		return Model.getModel().getVisionType() == VisionType.TANK;
	}
	
	@Override
	public void step(long elapsed) {
		super.step(elapsed);
		LinkedList<Entity> entHere = getMovingEntityHere();
		for (Entity entity : m_entityHere) {
			if(!entHere.contains(entity)) {
				m_entityHere.remove(entity);
				switch(entity.getHasChangedSpeed()) {
					case SLOWER:
						entity.setSpeed(entity.getSpeed() / MUD_COEFFICIENT_SPEED);
						break;
					case FASTER:
						entity.setSpeed(entity.getSpeed() * MUD_COEFFICIENT_SPEED);
						break;
					case NORMAL:
						break;
				}
				entity.setHasChangedSpeed(NORMAL);
			}
		}
	}

}
