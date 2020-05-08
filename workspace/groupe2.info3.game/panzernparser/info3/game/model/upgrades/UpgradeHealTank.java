package info3.game.model.upgrades;

import info3.game.model.Inventory;
import info3.game.model.MaterialType;
import info3.game.model.Tank;
import info3.game.model.entities.Drone;

public class UpgradeHealTank extends Upgrade{
	private static final String NAME = "Heal Tank";
	private static final int MINERALS_COST_BEGIN = 15;
	private static final int ELECTRONICALS_COST_BEGIN = 15;

	private static final double COST_FACTOR = 1.5;
	
	private static final int MINERALS_COST_MAX = 75;
	private static final int ELECTRONICALS_COST_MAX = 75;
	
	int m_mineralCost;
	int m_electronicalCost;

	public UpgradeHealTank(Tank tank, Drone drone) {
		super(tank, drone);
		m_electronicalCost = ELECTRONICALS_COST_BEGIN;
		m_mineralCost = MINERALS_COST_BEGIN;
	}

	@Override
	public void improve() throws IllegalAccessException {
		Inventory inv = m_tank.getInventory();
		int mineral_cost = getCostMine();
		int electronical_cost = getCostElec();
		if (isAvaibleFor(mineral_cost, electronical_cost)) {
			inv.used(MaterialType.MINERAL, mineral_cost, MaterialType.ELECTRONIC, electronical_cost);
			m_tank.setLife(m_tank.getLife() + m_tank.getMaxLife()/2);
			increaseCost();
			//m_level++;
		} else {
			throw new IllegalAccessException("Ressources insuffisantes dans l'inventaire.");
		}
	}
	
	private void increaseCost() {
		m_electronicalCost *= COST_FACTOR;
		m_mineralCost *= COST_FACTOR;
		if (m_electronicalCost> MINERALS_COST_MAX)
			m_electronicalCost = MINERALS_COST_MAX;
		if (m_mineralCost> ELECTRONICALS_COST_MAX)
			m_mineralCost = ELECTRONICALS_COST_MAX;
	}

	@Override
	public int getCostElec() {
		return m_electronicalCost;
	}

	@Override
	public int getCostMine() {
		return m_mineralCost;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public String getDescription() {
		return "<html><p style='color:black;text-align:center'>Heal the tank <b>50% of its life</b></p></html>";
	}
}
