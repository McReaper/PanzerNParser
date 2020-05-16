package info3.game.model.upgrades;

import info3.game.model.Inventory;
import info3.game.model.MaterialType;
import info3.game.model.Tank;
import info3.game.model.Weapon;

public class UpgradeTankShotsCapacity extends Upgrade {


	private static final String NAME = "Tank loader capacity";
	private static final int MINERALS_COST = 15;
	private static final int ELECTRONICALS_COST = 10;
	private static final int AMMO_BOOST_WEAP0 = 3; // +3
	private static final int AMMO_BOOST_WEAP1 = 2; // +2
	private static final int AMMO_BOOST_WEAP2 = 1; // +1
	private static final double COST_FACTOR = 1.0;
	
	public UpgradeTankShotsCapacity(Tank tank) {
		super(tank, null);
	}
	
	@Override
	public void improve() throws IllegalAccessException {
		Inventory inv = m_tank.getInventory();
		int mineral_cost = getCostMine();
		int electronical_cost = getCostElec();
		if (isAvaibleFor(mineral_cost, electronical_cost)) {
			inv.used(MaterialType.MINERAL, mineral_cost, MaterialType.ELECTRONIC, electronical_cost);
			int index = 0;
			for(Weapon weap : m_tank.getTurret().getWeapons()) {
				int boost = 0;
				switch (index) {
					case 0:
						boost = AMMO_BOOST_WEAP0;
						break;
					case 1:
						boost = AMMO_BOOST_WEAP1;
						break;
					case 2:
						boost = AMMO_BOOST_WEAP2;
						break;
				}
				m_tank.improveMaxAmmo(weap, boost);
				index++;
			}
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
		return "<html><p style='color:black;text-align:center'>Increase the tank <b>loader capacity</b> to be able to fire more shells.</p></html>";
	}
	
	@Override
	public boolean noMoreAvaible() {
		return false;
	}

}
