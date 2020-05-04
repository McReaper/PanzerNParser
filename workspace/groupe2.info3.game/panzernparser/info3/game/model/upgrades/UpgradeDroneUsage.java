package info3.game.model.upgrades;

import info3.game.model.Inventory;
import info3.game.model.MaterialType;
import info3.game.model.Tank;
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
		Inventory inv = m_tank.getInventory();
		int mineral_cost = getCostMine();
		int electronical_cost = getCostElec();
		if (isAvaibleFor(mineral_cost, electronical_cost)) {
			inv.used(MaterialType.MINERAL, mineral_cost, MaterialType.ELECTRONIC, electronical_cost);
			m_drone.setMaxHealth((int)(m_drone.getMaxHealth() + (m_drone.getMaxHealth()*DURATION_BOOST)));
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
		return "<html><p style='color:black;text-align:center'>Increases the drone <b>usage duration</b>.</p></html>";
	}

}
