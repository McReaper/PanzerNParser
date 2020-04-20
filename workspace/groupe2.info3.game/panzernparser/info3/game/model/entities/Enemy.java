package info3.game.model.entities;

import info3.game.model.Material.MaterialType;

public class Enemy extends MovingEntity {

	public static final int ENEMY_HEALTH = 100;
	// Temps utilisé pour aller d'une case à l'autre, donc, plus cette valeur est
	// petite, plus on va vite :
	public static final double ENEMY_TIMETOTRAVEL = 1; // ex: (1 / ENEMY_TIMETOTRAVEL) = nb de cases en 1 déplacement.

	boolean m_triggered; // indique si l'ennemi a détecté le joueur ou non.
	Droppable m_drops;

	public Enemy(int x, int y, int width, int height) {
		super(x, y, width, height, ENEMY_HEALTH, ENEMY_TIMETOTRAVEL);
		m_triggered = false; // Valeur par défaut
		m_drops = new Droppable(this.m_x, this.m_y, 1, 1, 1, MaterialType.ELECTRONIC);
	}

	@Override
	public void step(long elapsed) {
		m_elapseTime += elapsed;
		if (m_elapseTime > 1000) {
			System.out.println("Enemy step !");
			m_elapseTime = m_elapseTime - 1000;
		}
	}


}
