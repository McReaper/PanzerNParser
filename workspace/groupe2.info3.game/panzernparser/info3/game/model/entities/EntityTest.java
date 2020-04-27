package info3.game.model.entities;

import info3.game.automaton.MyDirection;
import info3.game.model.Model;
import info3.game.model.Tank;

public class EntityTest {
	public static void main(String[] args) throws InterruptedException {
		Model.getModel();
		TankBody tb = new TankBody(4, 4, 1, 1, null);
		Turret tr = new Turret(4, 4, 1, 1, 100, 1, null);
		Tank t = new Tank(tb, tr);
		Drone d = new Drone(4, 4, 1, 1, 100, 1, null);
		tb.m_actionFinished = true;
		tb.m_currentActionDir = MyDirection.FRONT;
		tb.Move(MyDirection.FRONT);
		d.Wizz(null);
		Thread.sleep(2000);
		tb.m_actionFinished = true;
		tb.Move(MyDirection.NORTH);
		tb.m_actionFinished = true;
		tb.Wizz(MyDirection.SOUTH);
		tb.Wizz(MyDirection.SOUTH);
		d.Wizz(null);
		t.step();
	}
}
