package info3.game.model;

import info3.game.model.entities.Drone;

public class UpgradeDroneUsage extends Upgrade {

	private static final String NAME = "Drone using-time";
	private static final int MINERALS_COST = 10;
	private static final int ELECTRONICALS_COST = 3;
	private static final double DURATION_BOOST = 0.10; //10%
	private static final double COST_FACTOR = 0.8;
	
	public UpgradeDroneUsage(Tank tank, Drone drone) {
		super(tank, drone);
	}
	
	@Override
	public void improve() throws IllegalAccessException {
		if (isAvaible()) {
			Inventory inv = m_tank.getInventory();
			int mineral_cost = (int) (MINERALS_COST + (MINERALS_COST * m_level * COST_FACTOR));
			int electronical_cost = (int) (ELECTRONICALS_COST + (ELECTRONICALS_COST * m_level * COST_FACTOR));
			inv.used(MaterialType.MINERAL, mineral_cost, MaterialType.ELECTRONIC, electronical_cost);
			m_drone.setMaxHealth((int)(m_drone.getMaxHealth() + (m_drone.getMaxHealth()*DURATION_BOOST)));
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
