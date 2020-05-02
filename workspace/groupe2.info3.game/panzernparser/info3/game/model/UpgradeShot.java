package info3.game.model;

public class UpgradeShot extends Upgrade {

	private static final String NAME = "Unlock new weapon";
	private static final int MINERALS_COST = 20;
	private static final int ELECTRONICALS_COST = 20;
	private static final double COST_FACTOR = 2.5;
	
	public UpgradeShot(Tank tank) {
		super(tank, null);
	}
	
	@Override
	public void improve() throws IllegalAccessException {
		Inventory inv = m_tank.getInventory();
		int mineral_cost = (int) (MINERALS_COST + (MINERALS_COST * m_level * COST_FACTOR));
		int electronical_cost = (int) (ELECTRONICALS_COST + (ELECTRONICALS_COST * m_level * COST_FACTOR));
		if (isAvaibleFor(mineral_cost, electronical_cost)) {
			inv.used(MaterialType.MINERAL, mineral_cost, MaterialType.ELECTRONIC, electronical_cost);
			//m_tank.unlockWeapon ???; TODO
			m_level++;
		} else {
			throw new IllegalAccessException("Ressources insuffisantes dans l'inventaire.");
		}
	}

	@Override
	public boolean isAvaible() throws IllegalAccessException {
		Inventory inv = m_tank.getInventory();
		int mineral_cost = (int) (MINERALS_COST + (MINERALS_COST * m_level * COST_FACTOR));
		int electronical_cost = (int) (ELECTRONICALS_COST + (ELECTRONICALS_COST * m_level * COST_FACTOR));
		return (inv.possesses(MaterialType.MINERAL, mineral_cost) && inv.possesses(MaterialType.ELECTRONIC, electronical_cost));
	}
	
	@Override
	public int getCostElec() {
		return MINERALS_COST;
	}

	@Override
	public int getCostMine() {
		return ELECTRONICALS_COST;
	}

	@Override
	public String getName() {
		return NAME;
	}
}
