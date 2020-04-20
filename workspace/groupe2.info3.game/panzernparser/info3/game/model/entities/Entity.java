package info3.game.model.entities;

import info3.game.view.Avatar;

public abstract class Entity {
	int m_x;
	int m_y;
	int m_width;
	int m_height;
	Avatar m_avatar;
	long m_elapseTime;
	// State m_state; //état courant dans l'automate
	// Automaton m_automate; //automate associé

	public Entity(int x, int y, int width, int height) {
		m_avatar = null;
		m_elapseTime = 0;
		m_x = x;
		m_y = y;
		m_width = width;
		m_height = height;
	}

	public void setAvatar(Avatar a) {
		m_avatar = a;
	}

	public abstract void step(long elapsed);

	public int getX() {
		return m_x;
	}

	public int getY() {
		return m_y;
	}

	public void Egg() {
	}

	public void Get(/* Donner une direction ? */) {
	}

	public void Hit(/* Donner une direction ? */) {
	}

	public void Explode() {
	}

	public void Move(/* Donner une direction ? */) {
	}

	public void Pick(/* Donner une direction ? */) {
	}

	public void Pop() {
	}

	public void Power() {
	}

	public void Protect() {
	}

	public void Store() {
	}

	public void Turn(/* Donner une direction ? */) {
	}

	public void Throw() {
	}

	public void Wait() {
	}

	public void Wizz() {
	}

	// public boolean GetDir(Direction dir) {}
	// public boolean Cell(Direction dir, EntityType type) {}
	// public boolean Closest(Direction dir, EntityType type) {}
	public boolean GotPower() {
		return true;
	}

	public boolean GotStuff() {
		return true;
	}

}
