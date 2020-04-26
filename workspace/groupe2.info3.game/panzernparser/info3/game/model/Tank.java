package info3.game.model;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.automaton.condition.MyDir;
import info3.game.model.entities.Entity;
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
	
	
	
	//Upgrade m_upgrade[];
	//Explosion m_explosion;
	
	int m_level;
	public TankBody m_body;
	Turret m_turret;
	public Tank(Entity entityBody, Entity entityTurret){
		m_body = (TankBody) entityBody;
		m_turret= (Turret) entityTurret;
		if (m_turret.getX() != m_body.getX() || m_turret.getY() != m_body.getY())
			m_turret.setPosition(m_body.getX(), m_body.getY());
 	}
	
	public void step () {
		m_turret.setControl(m_body.hasControl);
		if (m_turret.getX() != m_body.getX() || m_turret.getY() != m_body.getY()) {
			m_turret.setPosition(m_body.getX(), m_body.getY());
		}
		MyDirection bodyAbsoluteDir = MyDirection.toAbsolute(m_body.m_currentLookAtDir, m_body.m_currentActionDir);
		m_turret.setBodyDirection(bodyAbsoluteDir);
		m_turret.setBodyMoving(m_body.getCurrentAction()== LsAction.Move);
		m_turret.setBodyProgress(m_body.getActionProgress());
	}
}
