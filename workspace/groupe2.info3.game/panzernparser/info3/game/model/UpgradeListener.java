package info3.game.model;

public interface UpgradeListener {
	/**
	 * Une méthode pour activer une amélioration unique. Utiliser improve() pour des
	 * améliorations sans limites.
	 * @throws IllegalAccess si l'amélioration visée n'est pas unique.
	 */
	public void activate() throws IllegalAccessException;

	/**
	 * Une méthode pour désactiver une amélioration unique. Utile quand une
	 * amélioration à une durée limitée.
	 * @throws IllegalAccess si l'amélioration visée n'est pas unique.
	 */
	public void deactivate() throws IllegalAccessException;

	/**
	 * Vérifie si l'amélioration visée est disponible à l'achat. En vérifiant
	 * l'inventaire du joueur par exemple.
	 * @throws IllegalAccess si l'amélioration visée n'est pas unique.
	 */
	public boolean isAvaible() throws IllegalAccessException;

	/**
	 * Améliore une statistique, peut etre appelé à plusieurs reprises tant que
	 * isAvaible() est vrai.
	 * @throws IllegalAccess si l'amélioration visée n'est pas unique.
	 */
	public void improve() throws IllegalAccessException;
}
