package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyCategory;

public abstract class Enemy extends MovingEntity {
	public static final int ENEMY_BASIC = 1;
	public static final int ENEMY_LEVEL2 = 2;

	public Enemy(int x, int y, int width, int height, Automaton aut) {
		super(x, y, width, height, aut);
		m_category = MyCategory.A;
		m_uncrossables.add(MyCategory.A);
	}

	public abstract void levelUp();
}
