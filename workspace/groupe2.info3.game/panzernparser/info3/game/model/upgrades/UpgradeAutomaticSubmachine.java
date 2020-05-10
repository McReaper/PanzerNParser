package info3.game.model.upgrades;

import info3.game.model.Inventory;
import info3.game.model.MaterialType;
import info3.game.model.Tank;

/**
 * Amélioration unique.
 */
public class UpgradeAutomaticSubmachine extends Upgrade {

	private static final String NAME = "Helping turret";
	private static final int MINERALS_COST = 150;
	private static final int ELECTRONICALS_COST = 150;
	
	public UpgradeAutomaticSubmachine(Tank tank) {
		super(tank, null);
	}

	@Override
	public void activate() throws IllegalAccessException {
		Inventory inv = m_tank.getInventory();
		int mineral_cost = getCostMine();
		int electronical_cost = getCostElec();
		if (isAvaibleFor(mineral_cost, electronical_cost)) {
			inv.used(MaterialType.MINERAL, mineral_cost, MaterialType.ELECTRONIC, electronical_cost);
			m_tank.ActivateAutTurret(true);
			m_level = 1;
		} else {
			throw new IllegalAccessException("Ressources insuffisantes dans l'inventaire.");
		}
		
	}

	@Override
	public void deactivate() throws IllegalAccessException {
		if (m_level != 1) {
			m_tank.ActivateAutTurret(false);
			m_level = 0;
		} else {
			throw new IllegalAccessException("Upgrade non-activée.");
		}
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
	

}
