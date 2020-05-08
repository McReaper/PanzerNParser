package info3.game.model.upgrades;

import info3.game.model.Inventory;
import info3.game.model.MaterialType;
import info3.game.model.Tank;

public class UpgradeWeapon extends Upgrade {

	private static final String NAME = "Unlock new weapon";
	private static final int MINERALS_COST = 30;
	private static final int ELECTRONICALS_COST = 30;
	private static final double COST_FACTOR = 2.5;
	
	public UpgradeWeapon(Tank tank) {
		super(tank, null);
	}
	
	@Override
	public void improve() throws IllegalAccessException {
		Inventory inv = m_tank.getInventory();
		int mineral_cost = getCostMine();
		int electronical_cost = getCostElec();
		if (isAvaibleFor(mineral_cost, electronical_cost)) {
			inv.used(MaterialType.MINERAL, mineral_cost, MaterialType.ELECTRONIC, electronical_cost);
			m_tank.unlockNewWeapon();
			m_level++;
		} else {
			throw new IllegalAccessException("Ressources insuffisantes dans l'inventaire.");
		}
	}

	@Override
	public boolean isAvaible() {
		if (!m_tank.isNewWeaponAvaible()) return false;
		return super.isAvaible();
	}
	
	@Override
	protected boolean isAvaibleFor(int mineral_cost, int electronical_cost) {
		if (!m_tank.isNewWeaponAvaible()) return false;
		return super.isAvaibleFor(mineral_cost, electronical_cost);
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
		return "<html><p style='color:black;text-align:center'>Unlock a <b>new type of weapon</b> for the tank,<br> to help you deal with these stupid enemies</p></html>";
	}
}
