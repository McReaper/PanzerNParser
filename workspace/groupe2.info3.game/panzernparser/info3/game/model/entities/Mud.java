package info3.game.model.entities;

import java.util.Iterator;
import java.util.LinkedList;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;

public class Mud extends Ground {
	public static final int MUD_WIZZ_TIME = 50;
	public static final int MUD_COEFFICIENT_SPEED = 2;// indique par quel coefficient va être divisé la vitesse
	public static final int MUD_POP_TIME = 100;

	Entity m_entityHere;

	public Mud(int x, int y, Automaton aut) {
		super(x, y, aut);
	}

	public void Wizz(MyDirection dir) {//La boue fait avancer moins vite
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			m_actionFinished = false;
			m_currentAction = null;
			if (m_entityHere.getHasChangedSpeed()) {
				m_entityHere.setSpeed(m_entityHere.getSpeed() / MUD_COEFFICIENT_SPEED);

				m_entityHere.setHasChangedSpeed(false);
			}
			m_entityHere = null;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = MUD_WIZZ_TIME;
			getMovingEntityHere();
			if (!m_entityHere.getHasChangedSpeed()) {
				m_entityHere.setSpeed(m_entityHere.getSpeed() * MUD_COEFFICIENT_SPEED);
				m_entityHere.setHasChangedSpeed(true);
			}
		}
	}

	public void Pop(MyDirection dir) {//La boue se transforme en glace et on avance plus vite dessu
		if (m_actionFinished && m_currentAction == LsAction.Pop) {
			m_actionFinished = false;
			m_currentAction = null;
			m_entityHere = null;
			if (m_entityHere.getHasChangedSpeed()) {
				m_entityHere.setSpeed(m_entityHere.getSpeed() * MUD_COEFFICIENT_SPEED);

				m_entityHere.setHasChangedSpeed(false);
			}
			m_entityHere = null;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = MUD_POP_TIME;
			getMovingEntityHere();
			if (!m_entityHere.getHasChangedSpeed()) {
				m_entityHere.setSpeed(m_entityHere.getSpeed() / MUD_COEFFICIENT_SPEED);
				m_entityHere.setHasChangedSpeed(true);
			}
		}
	}

	private void getMovingEntityHere() {
		LinkedList<Entity> entities = Model.getModel().getGrid().getEntityCells(m_x, m_y, m_x + m_width - 1,
				m_y + m_height - 1);
		for (Entity entity : entities) {
			if (entity instanceof MovingEntity) {
				m_entityHere = entity;
			}
		}
	}

}
