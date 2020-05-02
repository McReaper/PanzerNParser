package info3.game.model;

import info3.game.model.entities.Drone;

public class UpgradeMarkersCount extends Upgrade {


	private static final String NAME = "Higher markers count";
	private static final int MINERALS_COST = 5;
	private static final int ELECTRONICALS_COST = 10;
	private static final int DRONE_MARKER_BOOST = 1;
	private static final double COST_FACTOR = 1;
	
	public UpgradeMarkersCount(Tank tank, Drone drone) {
		super(tank, drone);
	}

	@Override
	public void improve() throws IllegalAccessException {
		Inventory inv = m_tank.getInventory();
		int mineral_cost = getCostMine();
		int electronical_cost = getCostElec();
		if (isAvaibleFor(mineral_cost, electronical_cost)) {
			inv.used(MaterialType.MINERAL, mineral_cost, MaterialType.ELECTRONIC, electronical_cost);
			m_drone.setMaxMarkers(m_drone.getMaxMarkers() + DRONE_MARKER_BOOST);
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
	public String getEntity() {
		return NAME;
	}
	
	@Override
	public String getDescription() {
		return "<html><p style='color:black;text-align:center'>Increases <b>the limit of markers</b> placed by the drone</p></html>";
	}

}
