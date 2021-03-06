package info3.game.model.upgrades;

import info3.game.model.Inventory;
import info3.game.model.MaterialType;
import info3.game.model.Tank;

public class UpgradeTankDamage extends Upgrade {

	private static final String NAME = "Higher damage";
	private static final int MINERALS_COST = 35;
	private static final int ELECTRONICALS_COST = 25;
	private static final double DAMAGE_BOOST = 0.25; //25%
	private static final double COST_FACTOR = 1.0;

	public UpgradeTankDamage(Tank tank) {
		super(tank, null);
	}

	@Override
	public void improve() throws IllegalAccessException {
		Inventory inv = m_tank.getInventory();
		int mineral_cost = getCostMine();
		int electronical_cost = getCostElec();
		if (isAvaibleFor(mineral_cost, electronical_cost)) {
			inv.used(MaterialType.MINERAL, mineral_cost, MaterialType.ELECTRONIC, electronical_cost);
			m_tank.increaseDamageFactor(DAMAGE_BOOST);
			m_level++;
		} else {
			throw new IllegalAccessException("Ressources insuffisantes dans l'inventaire.");
		}
	}
	
	@Override
	public int getCostElec() {
		return (int) (ELECTRONICALS_COST + (ELECTRONICALS_COST * m_level * COST_FACTOR));
	}

	@Override
	public int getCostMine() {
		return (int) (MINERALS_COST + (MINERALS_COST * m_level * COST_FACTOR));
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDescription() {
		return "<html><p style='color:black;text-align:center'>Upgrade <b>your damage by 25%</b> <br>to destroy efficiently other units</p></html>";
	}
	
	@Override
	public boolean noMoreAvaible() {
		return false;
	}

}
