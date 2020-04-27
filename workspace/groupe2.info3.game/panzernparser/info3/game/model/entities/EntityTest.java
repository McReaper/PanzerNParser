package info3.game.model.entities;

import info3.game.automaton.MyDirection;
import info3.game.model.Tank;

public class EntityTest {
	public static void main(String[] args) {
		TankBody tb = new TankBody(4, 4, 1, 1, null);
		Turret tr = new Turret(4, 4, 1, 1, 100, 1, null);
		Tank t = new Tank(tb, tr);
		tb.Move(MyDirection.FRONT);
		tb.Move(MyDirection.NORTH);
		tb.Wizz(MyDirection.SOUTH);
	}
}
