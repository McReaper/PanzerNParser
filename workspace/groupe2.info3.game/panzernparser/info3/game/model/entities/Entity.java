package info3.game.model.entities;

import info3.game.automaton.State;
import info3.game.view.Avatar;

public abstract class Entity {
	int m_x;
	int m_y;
	int m_width;
	int m_height;
	Avatar m_avatar;
	long m_elapseTime;
	State m_currentState;//Emilie : TODO gerer les cas null suite a l'automate
	// State m_state; //état courant dans l'automate
	// Automaton m_automate; //automate associé

	public abstract void Egg();

	public abstract void Get(/* Donner une direction ? */);

	public abstract void Hit(/* Donner une direction ? */);

	public abstract void Explode();

	public abstract void Move(/* Donner une direction ? */);

	public abstract void Pick(/* Donner une direction ? */);

	public abstract void Pop();

	public abstract void Power();

	public abstract void Protect();

	public abstract void Store();

	public abstract void Turn(/* Donner une direction ? */);

	public abstract void Throw();

	public abstract void Wait();

	public abstract void Wizz();

	// public abstract boolean GetDir(Direction dir);
	// public abstract boolean Cell(Direction dir, EntityType type);
	// public abstract boolean Closest(Direction dir, EntityType type);
	public abstract boolean GotPower();

	public abstract boolean GotStuff();
	
	public State getState() {
		return m_currentState;
	}
	/*
	 * Emilie : Ceci est une fonction temporaire pour pouvoir gerer les test sur les automates sans parser	
	 */
	public void setState(State state) {
		m_currentState = state;
	}
	

}
