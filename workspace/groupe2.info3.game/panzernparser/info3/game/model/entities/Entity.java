package info3.game.model.entities;

import info3.game.automata.ast.Direction;

public abstract class Entity {
	int m_x;
	int m_y;
	int m_width;
	int m_height;
	long m_elapseTime;
	Direction m_dir;
	// State m_state; //état courant dans l'automate
	// Automaton m_automate; //automate associé

	public Entity(int x, int y, int width, int height) {
		m_elapseTime = 0;
		m_x = x;
		m_y = y;
		m_width = width;
		m_height = height;
	}

	public abstract void step(long elapsed);

	public int getX() {
		System.out.println("Is GetXing");
		return m_x;
	}

	public int getY() {
		System.out.println("Is GetYing");
		return m_y;
	}

	public void Egg() {
		System.out.println("Is Egging");
	}

	public void Get(/* Donner une direction ? */) {
		System.out.println("Is Getting");
	}

	public void Hit(/* Donner une direction ? */) {
		System.out.println("Is Hitting");
	}

	public void Explode() {
		System.out.println("Is Exploding");
	}

	public void Move(int dx, int dy) {
		System.out.println("Is Moving");
		m_x += dx;
		m_y += dy;
		return;
	}

	public void Pick(/* Donner une direction ? */) {
		System.out.println("Is Picking");
	}

	public void Pop() {
		System.out.println("Is Poping");
	}

	public void Power() {
		System.out.println("Is \"UNLIMITED PAWER!!\"-ing");
	}

	public void Protect() {
		System.out.println("Is Protecting");
	}

	public void Store() {
		System.out.println("Is Storing");
	}

	public void Turn(/* Donner une direction ? */) {
		System.out.println("Is Turning");
	}

	public void Throw() {
		System.out.println("Is Throwing");
	}

	public void Wait() {
		System.out.println("Is Waiting");
	}

	public void Wizz() {
		System.out.println("Is Wizzing");
	}

	public boolean GetDir(Direction dir) {
		System.out.println("Is Getdiring");
		if (m_dir != null) {
			return m_dir.equals(dir);
		}
		return false;
	}

	public boolean Cell(Direction dir, Entity type) {
		System.out.println("Is Celling");
		return false;
	}
	
	public boolean GotPower() {
		System.out.println("Is Got\"UNLIMITED PAWER\"-ing");
		return true;
	}

	public boolean GotStuff() {
		System.out.println("Is Gotstuffing");
		return true;
	}

}
