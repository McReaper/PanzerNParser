package info3.game.model;

import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.entities.TankBody;
import info3.game.model.entities.Turret;

/**
 * Classe qui va servir de jointure entre le chassis et le canon
 * particulièrement lors des moves.
 */
public class Tank {

	public final static int TANKBODY_WIDTH = TankBody.TANKBODY_WIDTH;
	public final static int TANKBODY_HEIGHT = TankBody.TANKBODY_HEIGHT;
	public final static int TANKTURRET_WIDTH = Turret.TURRET_WIDTH;
	public final static int TANKTURRET_HEIGHT = Turret.TURRET_HEIGHT;

	public static final int TANK_HEALTH = 100;
	public static final int TANK_SPEED = 100;

	// Upgrade m_upgrade[];
	// Explosion m_explosion;
	// private int m_level;
	
	private TankBody m_body;
	private Turret m_turret;
	private Inventory m_inventory;

	public Tank(TankBody body, Turret turret) {
		m_body = body;
		m_turret = turret;
		m_body.setTank(this);
		m_turret.setTank(this);
		m_inventory = new Inventory();
	}

	public boolean hasControl() {
		return Model.getModel().isPlayingTank();
	}

	public void relocateParts() {
		if (m_turret.getX() != m_body.getX() || m_turret.getY() != m_body.getY())
			m_turret.setPosition(m_body.getX(), m_body.getY());
	}

	public void showTank(boolean b) {
		m_body.showEntity(b);
		m_turret.showEntity(b);
	}

	public void step() {
		if (this.hasControl()) {
			relocateParts();
		}
	}

	public TankBody getBody() {
		return m_body;
	}
	
	public Turret getTurret() {
		return m_turret;
	}
	
	public Inventory getInventory() {
		return m_inventory;
	}

	///// METHODES POUR LA VUE /////

	public boolean isBodyMoving() {
		return m_body.getCurrentAction() == LsAction.Move;
	}

	public MyDirection getBodyDirection() {
		return MyDirection.toAbsolute(m_body.getLookAtDir(), m_body.getCurrentActionDir());
	}

	public double getBodyProgress() {
		return m_body.getActionProgress();
	}

}
