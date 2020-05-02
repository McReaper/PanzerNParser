package info3.game.model;

import info3.game.model.entities.Drone;

/**
 * Amélioration unique.
 */
public class UpgradeDroneVision extends Upgrade {


	private static final String NAME = "Drone dynamic elevation";
	private static final int MINERALS_COST = 0;
	private static final int ELECTRONICALS_COST = 10;
	
	public UpgradeDroneVision(Tank tank, Drone drone) {
		super(tank, drone);
	}

	@Override
	public void activate() throws IllegalAccessException {
		Inventory inv = m_tank.getInventory();
		int mineral_cost = getCostMine();
		int electronical_cost = getCostElec();
		if (isAvaibleFor(mineral_cost, electronical_cost)) {
			inv.used(MaterialType.MINERAL, mineral_cost, MaterialType.ELECTRONIC, electronical_cost);
			m_drone.setStuff(true);
			m_level = 1;
		} else {
			throw new IllegalAccessException("Ressources insuffisantes dans l'inventaire.");
		}
	}
	
	@Override
	public void deactivate() throws IllegalAccessException {
		if (m_level != 1) {
			m_drone.setStuff(false);
			m_level = 0;
		} else {
			throw new IllegalAccessException("Upgrade non-activée.");
		}
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
	public String getEntity() {
		return NAME;
	}
	
	@Override
	public String getDescription() {
		return "<html><p style='color:black;text-align:center'>Activate the drone's ability to <b>go up and down in the sky</b>, allowing you to <b>see more</b> resources or enemies.</p></html>";
	}

}
