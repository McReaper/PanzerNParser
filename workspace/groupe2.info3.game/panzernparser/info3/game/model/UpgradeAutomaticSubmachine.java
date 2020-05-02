package info3.game.model;

public class UpgradeAutomaticSubmachine extends Upgrade {

	private static final String NAME = "Helping turret";
	
	public UpgradeAutomaticSubmachine(Tank tank) {
		super(tank, null);
	}

	@Override
	public int getCostElec() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCostMine() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getEntity() {
		return NAME;
	}

	@Override
	public String getDescription() {
		return "<html><p style='color:black;text-align:center'>An <b>automatic turret</b> will appear and help you defeat the enemies.</p></html>";
	}

}
