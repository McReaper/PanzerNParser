package info3.game.model.upgrades;

import info3.game.model.Inventory;
import info3.game.model.MaterialType;
import info3.game.model.Tank;

public class UpgradeTankLife extends Upgrade {

	private static final String NAME = "Higher health";
	private static final int MINERALS_COST = 15;
	private static final int ELECTRONICALS_COST = 7;
	private static final int LIFE_BOOST = 20;
	private static final double COST_FACTOR = 1.0;

	public UpgradeTankLife(Tank tank) {
		super(tank, null);
	}

	@Override
	public void improve() throws IllegalAccessException {
		Inventory inv = m_tank.getInventory();
		int mineral_cost = getCostMine();
		int electronical_cost = getCostElec();
		if (isAvaibleFor(mineral_cost, electronical_cost)) {
			inv.used(MaterialType.MINERAL, mineral_cost, MaterialType.ELECTRONIC, electronical_cost);
			m_tank.setMaxLife(m_tank.getMaxLife() + LIFE_BOOST);
			m_tank.takeDamage(-LIFE_BOOST); // Heal le tank.
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
		return "<html><p style='color:black;text-align:center'>Increases <b>the max-health of your tank</b> for the rest of the game</p></html>";
	}

	@Override
	public boolean noMoreAvaible() {
		return false;
	}
}
