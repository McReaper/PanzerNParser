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

	public final static int TANK_BODY_WIDTH = 1;
	public final static int TANK_BODY_HEIGHT = 1;
	public final static int TANK_TURRET_WIDTH = 1;
	public final static int TANK_TURRET_HEIGHT = 1;

	public static final int TANK_HEALTH = 100;
	public static final int TANK_SPEED = 100;

	// Upgrade m_upgrade[];
	// Explosion m_explosion;
	// private int m_level;
	
	private TankBody m_body;
	private Turret m_turret;

	public Tank(TankBody body, Turret turret) {
		m_body = body;
		m_turret = turret;
		m_body.setTank(this);
		m_turret.setTank(this);
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

	///// METHODES POUR LA VUE /////

	public boolean isBodyMoving() {
		return m_body.getCurrentAction() == LsAction.Move;
	}

	public MyDirection getBodyDirection() {
		return MyDirection.toAbsolute(m_body.m_currentLookAtDir, m_body.m_currentActionDir);
	}

	public double getBodyProgress() {
		return m_body.getActionProgress();
	}

}
