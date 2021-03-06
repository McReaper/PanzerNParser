package info3.game.model;

import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.entities.AutomaticTurret;
import info3.game.model.entities.EnemyBasic;
import info3.game.model.entities.EnemyBoss;
import info3.game.model.entities.EnemyLevel2;
import info3.game.model.entities.Entity;
import info3.game.model.entities.TankBody;
import info3.game.model.entities.Turret;

/**
 * Classe qui set de jointure entre le chassis et le canon particulièrement lors
 * des moves.
 */
public class Tank {

	public final static int TANK_WIDTH = 3;
	public final static int TANK_HEIGHT = 3;

	public static final int TANK_HEALTH = 100;
	public static final int TANK_SPEED = 270;
	
	public static final int TANK_FOV = 6;

	// Upgrade m_upgrade[];
	// Explosion m_explosion;
	// private int m_level;

	private TankBody m_body;
	private Turret m_turret;
	private AutomaticTurret m_autTurret;
	private Inventory m_inventory;
	private int m_health;
	private int m_maxHealth;

	public Tank(TankBody body, Turret turret, AutomaticTurret autTurret) {
		m_body = body;
		m_turret = turret;
		m_autTurret = autTurret;
		m_body.setTank(this);
		m_turret.setTank(this);
		m_autTurret.setTank(this);
		m_inventory = new Inventory();
		m_health = TANK_HEALTH;
		m_maxHealth = TANK_HEALTH;
	}

	public boolean hasControl() {
		return Model.getModel().isPlayingTank();
	}

	public void relocateParts() {
		if (m_turret.getX() != m_body.getX() || m_turret.getY() != m_body.getY()) {
			m_turret.setPosition(m_body.getX(), m_body.getY());
			m_autTurret.setPosition(m_body.getX(), m_body.getY());
		}
	}

	public void step() {
		if (this.hasControl()) {
			relocateParts();
		}
	}

	////////////////// GETTERS AND SETTERS/////////////////////

	public TankBody getBody() {
		return m_body;
	}

	public Turret getTurret() {
		return m_turret;
	}
	
	public AutomaticTurret getAutoTurret() {
		return m_autTurret;
	}
	
	public void ActivateAutTurret(boolean b) {
		m_autTurret.setIsActivate(b);
	}

	public Inventory getInventory() {
		return m_inventory;
	}

	public void doExplode() {
		m_turret.doExplode();
		m_body.doExplode();
		Model.getModel().setGameOver(true);
		Model.getModel().addSound("Game_Over");
	}

	public void setLife(int tankHealth) {
		m_health = tankHealth;
		if (m_health > m_maxHealth)
			m_health = m_maxHealth;
	}

	public int getLife() {
		return m_health;
	}

	public int getMaxLife() {
		return m_maxHealth;
	}

	public void setMaxLife(int maxLife) {
		m_maxHealth = maxLife;
	}

	public void takeDamage(int damages) {
		m_health -= damages;
	}

	public int getDamage() {
		return m_turret.getDamageDealt();
	}
	
	public void increaseDamageFactor(double damageBoost) {
		m_turret.increaseDamageFactor(damageBoost);
	}

	public long getSpeed() {
		return m_body.getSpeed();
	}

	public void setSpeed(long l) {
		m_body.setSpeed(l);
	}

	public boolean gotPower() {
		return m_health > 0;
	}

	public int getMaxAmmo() {
		return m_turret.getWeapon().getCapacity();
	}

	public void improveMaxAmmo(Weapon weap, int value) {
		m_turret.increaseMaxAmmo(weap, value);
	}

	public long getMiningTime() {
		return m_body.getMiningTime();
	}

	public void setMiningTime(long t) {
		m_body.setMiningTime(t);
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

	public void unlockNewWeapon() throws IllegalAccessException {
		m_turret.unlockNewWeapon();
	}

	public boolean isNewWeaponAvaible() {
		return m_turret.isWeaponUnlockable();
	}

	public void hasKilled(Entity e) {
		if (e instanceof EnemyBasic)
			Model.getModel().getScore().scoreEnemyBasic();
		else if (e instanceof EnemyLevel2)
			Model.getModel().getScore().scoreEnemyLevel2();
		else if (e instanceof EnemyBoss)
			Model.getModel().getScore().scoreEnemyBoss();
	}

}
