package info3.game.model;

import info3.game.automaton.Automaton;
import info3.game.model.entities.Entity;
import info3.game.model.entities.TankBody;
import info3.game.model.entities.Turret;

/**
 * Classe qui va servir de jointure entre le chassis et le canon 
 * particulièrement lors des moves.
 */
public class Tank {
	public final static int TANK_BODY_WIDTH = 3;
	public final static int TANK_BODY_HEIGHT = 3;
	public final static int TANK_TURRET_WIDTH = 1;
	public final static int TANK_TURRET_HEIGHT = 3;
	
	public static final int TANK_HEALTH = 100;
	public static final int TANK_SPEED = 100;
	
	
	//Upgrade m_upgrade[];
	//Explosion m_explosion;
	
	int m_level;
	TankBody m_body;
	Turret m_turret;
	public Tank(Entity entityBody, Entity entityTurret){
		m_body = (TankBody) entityBody;
		m_turret= (Turret) entityTurret;
		if (m_turret.getX() != m_body.getX() || m_turret.getY() != m_body.getY())
			m_turret.setPosition(m_body.getX(), m_body.getY());
 	}
	
	public void step () {
		if (m_body.moved()) {
			m_turret.setPosition(m_body.getX(), m_body.getY());
			m_body.setMoved(false);
		}
		if (m_turret.moved()) {
			m_body.setPosition(m_turret.getX(), m_turret.getY());
			m_turret.setMoved(false);
		}
	}
}
