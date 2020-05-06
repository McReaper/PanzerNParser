package info3.game.model.upgrades;

import info3.game.model.Tank;

/**
 * Amélioration unique.
 */
public class UpgradeAutomaticSubmachine extends Upgrade {
	Tank m_tank;
	

	private static final String NAME = "Helping turret";
	private static final int MINERALS_COST = 0;
	private static final int ELECTRONICALS_COST = 5;
	
	public UpgradeAutomaticSubmachine(Tank tank) {
		super(tank, null);
		m_tank = tank;
	}

	@Override
	public boolean isAvaible() {
		if (m_level > 0) return false;
		return super.isAvaible();
	}
	
	@Override
	public int getCostElec() {
		return ELECTRONICALS_COST;
	}

	@Override
	public int getCostMine() {
		return MINERALS_COST;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDescription() {
		return "<html><p style='color:black;text-align:center'>An <b>automatic turret</b> will appear <br>and help you defeat the enemies.</p></html>";
	}
	

	public void activate() throws IllegalAccessException {
		m_tank.showAutTurret(true);
		m_tank.ActivateAutTurret(true);
		
	}

	/**
	 * Une méthode pour désactiver une amélioration unique. Utile quand une
	 * amélioration à une durée limitée.
	 * 
	 * @throws IllegalAccess si l'amélioration visée n'est pas unique.
	 */
	public void deactivate() throws IllegalAccessException {
		m_tank.showAutTurret(false);
		m_tank.ActivateAutTurret(false);
	}

}
