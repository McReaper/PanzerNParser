package info3.game.model;

import info3.game.model.entities.Drone;

/**
 * Amélioration unique.
 */
public class UpgradeDroneVision extends Upgrade {

	private static final int MINERALS_COST = 0;
	private static final int ELECTRONICALS_COST = 10;
	
	public UpgradeDroneVision(Tank tank, Drone drone) {
		super(tank, drone);
	}

	@Override
	public void activate() throws IllegalAccessException {
		if (isAvaible()) {
			Inventory inv = m_tank.getInventory();
			inv.used(MaterialType.MINERAL, MINERALS_COST, MaterialType.ELECTRONIC, ELECTRONICALS_COST);
			m_drone.setStuff(true);
			m_level = 1;
		} else {
			throw new IllegalAccessException("Ressources insuffisantes dans l'inventaire.");
		}
	}

	@Override
	public boolean isAvaible() throws IllegalAccessException {
		Inventory inv = m_tank.getInventory();
		return (inv.possesses(MaterialType.MINERAL, MINERALS_COST) && inv.possesses(MaterialType.ELECTRONIC, ELECTRONICALS_COST));
	}

}
