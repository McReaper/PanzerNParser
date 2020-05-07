package info3.game.model.entities;

import info3.game.automaton.Automaton;

public class ShotEnemyBoss extends ShotEnemy{
	public static final int SHOTENEMYBOSS_WIDTH = 1;
	public static final int SHOTENEMYBOSS_HEIGHT = 1;
	public ShotEnemyBoss(int x, int y, Automaton aut) {
		super(x, y, SHOTENEMYBOSS_WIDTH, SHOTENEMYBOSS_HEIGHT, aut);
		// TODO Auto-generated constructor stub
	}

}
