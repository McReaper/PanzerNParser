package info3.game.model.entities;

import info3.game.automaton.Automaton;

public abstract class ShotEnemy extends Shot {
	public ShotEnemy(int x, int y, int width, int height, Automaton aut) {
		super(x, y, width, height, aut);
	}
	
	public void setDamage(int damage) {
		m_damage_dealt = damage;
	}
	
}
