package info3.game.model;

import info3.game.model.entities.Drone;

public abstract class Upgrade {

	protected Tank m_tank;
	protected Drone m_drone;
	protected int m_level;

	public Upgrade(Tank tank, Drone drone) {
		m_tank = tank;
		m_drone = drone;
		m_level = 0;
	}

	/**
	 * Une méthode pour activer une amélioration unique. Utiliser improve() pour des
	 * améliorations sans limites.
	 * 
	 * @throws IllegalAccess si l'amélioration visée n'est pas unique.
	 */
	public void activate() throws IllegalAccessException {
		throw new IllegalAccessException("Vous ne pouvez pas appelé cette méthode sur cette upgrade.");
	}

	/**
	 * Une méthode pour désactiver une amélioration unique. Utile quand une
	 * amélioration à une durée limitée.
	 * 
	 * @throws IllegalAccess si l'amélioration visée n'est pas unique.
	 */
	public void deactivate() throws IllegalAccessException {
		throw new IllegalAccessException("Vous ne pouvez pas appelé cette méthode sur cette upgrade.");
	}

	/**
	 * Vérifie si l'amélioration visée est disponible à l'achat. En vérifiant
	 * l'inventaire du joueur par exemple.
	 * 
	 * @throws IllegalAccess si l'amélioration visée n'est pas unique.
	 */
	public boolean isAvaible() throws IllegalAccessException {
		return false;
	}

	/**
	 * méthode utile pour éviter une redondance des calculs.
	 */
	protected boolean isAvaibleFor(int mineral_cost, int electronical_cost) {
		Inventory inv = m_tank.getInventory();
		return (inv.possesses(MaterialType.MINERAL, mineral_cost)
				&& inv.possesses(MaterialType.ELECTRONIC, electronical_cost));
	}

	/**
	 * Améliore une statistique, peut etre appelé à plusieurs reprises tant que
	 * isAvaible() est vrai.
	 * 
	 * @throws IllegalAccess si l'amélioration visée n'est pas unique.
	 */
	public void improve() throws IllegalAccessException {
		throw new IllegalAccessException("Vous ne pouvez pas appelé cette méthode sur cette upgrade.");
	}

	public int getLevel() {
		return m_level;
	}

}
