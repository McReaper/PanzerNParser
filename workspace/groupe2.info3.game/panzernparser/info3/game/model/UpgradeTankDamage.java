package info3.game.model;

public class UpgradeTankDamage extends Upgrade {

	private static final int MINERALS_COST = 10;
	private static final int ELECTRONICALS_COST = 5;
	private static final int DAMAGE_BOOST = 10;
	private static final double COST_FACTOR = 1.0;

	public UpgradeTankDamage(Tank tank) {
		super(tank, null);
	}

	@Override
	public void improve() throws IllegalAccessException {
		Inventory inv = m_tank.getInventory();
		int mineral_cost = (int) (MINERALS_COST + (MINERALS_COST * m_level * COST_FACTOR));
		int electronical_cost = (int) (ELECTRONICALS_COST + (ELECTRONICALS_COST * m_level * COST_FACTOR));
		if (isAvaibleFor(mineral_cost, electronical_cost)) {
			inv.used(MaterialType.MINERAL, mineral_cost, MaterialType.ELECTRONIC, electronical_cost);
			m_tank.setDamage(m_tank.getDamage() + DAMAGE_BOOST);
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
