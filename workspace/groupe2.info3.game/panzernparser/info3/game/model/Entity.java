package info3.game.model;

import info3.game.view.Avatar;

public abstract class Entity {
	int m_x;
	int m_y;
	int m_width;
	int m_height;
	Avatar m_avatar;
	// State m_state; //état courant dans l'automate
	// Automaton m_automate; //automate associé

	abstract void Egg();

	abstract void Get();

	abstract void Hit();

	abstract void Explode();

	abstract void Move();

	abstract void Pick();

	abstract void Pop();

	abstract void Power();

	abstract void Protect();

	abstract void Store();

	abstract void Turn();

	abstract void Throw();

	abstract void Wait();

	abstract void Wizz();

	// abstract boolean GetDir(Direction dir);
	// abstract boolean Cell(Direction dir, EntityType type);
	// abstract boolean Closest(Direction dir, EntityType type);
	abstract boolean GotPower();

	abstract boolean GotStuff();

}
