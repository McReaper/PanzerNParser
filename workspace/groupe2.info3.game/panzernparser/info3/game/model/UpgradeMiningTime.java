package info3.game.model;

public class UpgradeMiningTime extends Upgrade {


	private static final String NAME = "Lower mining time";
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
		int mineral_cost = getCostMine();
		int electronical_cost = getCostElec();
		if (isAvaibleFor(mineral_cost, electronical_cost)) {
			inv.used(MaterialType.MINERAL, mineral_cost, MaterialType.ELECTRONIC, electronical_cost);
			m_tank.setMiningTime((long)(m_tank.getMiningTime() - m_tank.getMiningTime()*MINING_TIME_REDUCE_FACTOR));
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
		return "<html><p style='color:black;text-align:center'></p></html>";
	}
}
