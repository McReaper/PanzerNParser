package info3.game.model.upgrades;

import info3.game.model.Tank;

/**
 * Amélioration unique.
 */
public class UpgradeAutomaticSubmachine extends Upgrade {

	private static final String NAME = "Helping turret";
//	private static final int MINERALS_COST = 10;
//	private static final int ELECTRONICALS_COST = 5;
	
	public UpgradeAutomaticSubmachine(Tank tank) {
		super(tank, null);
	}

	@Override
	public boolean isAvaible() {
		if (m_level > 0) return false;
		return super.isAvaible();
	}
	
	@Override
	public int getCostElec() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCostMine() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDescription() {
		return "<html><p style='color:black;text-align:center'>An <b>automatic turret</b> will appear <br>and help you defeat the enemies.</p></html>";
	}

}
