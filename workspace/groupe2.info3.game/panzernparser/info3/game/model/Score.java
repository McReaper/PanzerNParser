package info3.game.model;

public class Score {
	static final int SCORE_ENEMY_BASIC = 20;// tuer ennemi basic
	static final int SCORE_ENEMY_LEVEL2 = 40;// tuer ennemi mid
	static final int SCORE_ENEMY_BOSS = 180;// tuer ennemi high
	static final int SCORE_DROPPABLE = 5;// ramasser ressource
	static final int SCORE_VEIN = 20;// creuser veine
	static final int SCORE_WRECKTANK = 30;// détruire une épave
	// static final int SCORE_UPGRADE = ????;
	static final int SCORE_TIME = 10;// ttes les 30 s gain de 10 points
	static final int SCORE_END_MAP = 300;
	static final long TIME_PERIOD = 30000;// check periode de 30 s

	private int m_score;
	private long m_time;

	public Score() {
		m_time = System.currentTimeMillis();
		m_score = 0;
	}

	public void updateTime() {
		long now = System.currentTimeMillis();
		if ((now - m_time) >= TIME_PERIOD) {
			m_time = now;
			incrementScore(SCORE_TIME);
		}
	}

	public void scoreEnemyBasic() {
		incrementScore(SCORE_ENEMY_BASIC);
	}

	public void scoreEnemyLevel2() {
		incrementScore(SCORE_ENEMY_LEVEL2);
	}

	public void scoreEnemyBoss() {
		incrementScore(SCORE_ENEMY_BOSS);
	}

	public void scoreDroppable() {
		incrementScore(SCORE_DROPPABLE);
	}

	public void scoreVein() {
		incrementScore(SCORE_VEIN);
	}
	
	public void scoreWreckTank() {
		incrementScore(SCORE_WRECKTANK);
	}

	public void scoreEndMap() {
		incrementScore(SCORE_END_MAP);
	}

	private void incrementScore(int value) {
		m_score += value + (value * (double)(Model.getModel().getLevel())/10);
	}
	
	public int getScore() {
		return m_score;
	}
}
