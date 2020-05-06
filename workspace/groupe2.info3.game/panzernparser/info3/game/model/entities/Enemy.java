package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyCategory;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.EntityFactory.MyEntities;

public abstract class Enemy extends MovingEntity {
	public static final int ENEMY_BASIC =1;
	public static final int ENEMY_LEVEL2 =2;
	
	public Enemy(int x, int y,int width, int height,  Automaton aut) {
		super(x, y, width, height, aut);
		m_category = MyCategory.A;
		m_uncrossables.add(MyCategory.A);
	}
	
	public abstract void levelUp();
}
