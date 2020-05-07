package info3.game.model.entities;

import info3.game.automaton.Automaton;

public class EnemyBoss extends Enemy {

	public static final int ENEMYBOSS_WIDTH = 3;
	public static final int ENEMYBOSS_HEIGHT = 3;
	public EnemyBoss(int x, int y, Automaton aut) {
		super(x, y, ENEMYBOSS_WIDTH, ENEMYBOSS_HEIGHT, aut);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void levelUp() {
		// TODO Auto-generated method stub
		
	}

}
