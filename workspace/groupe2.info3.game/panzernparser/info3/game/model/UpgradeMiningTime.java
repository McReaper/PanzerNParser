package info3.game.model;

public class UpgradeMiningTime extends Upgrade {

	private static final int MINERALS_COST = 5;
	private static final int ELECTRONICALS_COST = 10;
	private static final double MINING_TIME_REDUCE_FACTOR = 0.10; //10% plus rapide
	private static final double COST_FACTOR = 1.0;
	
	public UpgradeMiningTime(Tank tank) {
		super(tank, null);
	}

	@Override
	public void improve() throws IllegalAccessException {
		Inventory inv = m_tank.getInventory();
		int mineral_cost = (int) (MINERALS_COST + (MINERALS_COST * m_level * COST_FACTOR));
		int electronical_cost = (int) (ELECTRONICALS_COST + (ELECTRONICALS_COST * m_level * COST_FACTOR));
		if (isAvaibleFor(mineral_cost, electronical_cost)) {
			inv.used(MaterialType.MINERAL, mineral_cost, MaterialType.ELECTRONIC, electronical_cost);
			m_tank.setMiningTime((long)(m_tank.getMiningTime() - m_tank.getMiningTime()*MINING_TIME_REDUCE_FACTOR));
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
}
