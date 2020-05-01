package info3.game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Hashtable;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import info3.game.model.MaterialType;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.Tank;
import info3.game.model.Upgrade;
import info3.game.model.entities.Drone;
import info3.game.model.entities.Entity;
import info3.game.model.entities.EntityFactory.MyEntities;
import info3.game.model.entities.TankBody;
import info3.game.model.entities.Turret;

public class HUD {
	
	public static final int MINIMAL_WIDTH = 2 * 120;
	public static final int MINIMAL_HEIGHT = 600;
	

	View m_view;

	JPanel m_West;
	JPanel m_East;
	
	JPanel m_ammoPanel;
	JPanel m_viewModePanel;
	JLabel m_viewModeLabel;

	JLabel m_mineralsLabel;
	JLabel m_toolsLabel;
	JLabel m_weaponLabel;
	JLabel m_weaponImage;
	ImageIcon[] m_weaponArray;
	JProgressBar m_health;
	JProgressBar m_drone;
	TitledBorder m_ammoTitledBorder;

	JLabel m_score;
	JLabel m_level;
	JLabel m_time;
	JLabel m_ammo;
	CompassWidget m_compass;
	JPanel m_upgrade;
	Hashtable<Upgrade, JButton> m_availableUpgrades;

	public HUD(View view) {

		m_view = view;

		// La font des futurs labels
		Font fontWest = new Font(null, 0, 15);

		m_West = new JPanel();
		m_West.setBackground(Color.DARK_GRAY);
		m_West.setPreferredSize(new Dimension(120, 150));
		GridLayout GLWest = new GridLayout(2, 0);
		m_West.setLayout(GLWest);

		JPanel MinToolsWeapon = new JPanel();
		BoxLayout BLMintoolsweapon = new BoxLayout(MinToolsWeapon, BoxLayout.Y_AXIS);
		MinToolsWeapon.setLayout(BLMintoolsweapon);
		MinToolsWeapon.setBackground(Color.DARK_GRAY);

		m_weaponArray = new ImageIcon[6];

		m_weaponArray[0] = new ImageIcon(
				new ImageIcon("sprites/Weapon0.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
		m_weaponArray[1] = new ImageIcon(
				new ImageIcon("sprites/Weapon1.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
		m_weaponArray[2] = new ImageIcon(
				new ImageIcon("sprites/Weapon2.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
		m_weaponArray[3] = new ImageIcon(
				new ImageIcon("sprites/VueRessourceIcon.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
		m_weaponArray[4] = new ImageIcon(
				new ImageIcon("sprites/VueEnemyIcon.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
		m_weaponArray[5] = new ImageIcon(
				new ImageIcon("sprites/MarkerSingle.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
		
		ImageIcon mineralsIcone = new ImageIcon(
				new ImageIcon("sprites/Minerals.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
		ImageIcon toolsIcone = new ImageIcon(
				new ImageIcon("sprites/Electronics.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
		JLabel mineralsImage = new JLabel(mineralsIcone);
		JLabel toolsImage = new JLabel(toolsIcone);
		m_weaponImage = new JLabel(m_weaponArray[0]);

		m_mineralsLabel = new JLabel("Minerals :");
		m_mineralsLabel.setForeground(Color.BLACK);
		m_mineralsLabel.setFont(fontWest);
		m_toolsLabel = new JLabel("Electronics :");
		m_toolsLabel.setForeground(Color.BLACK);
		m_toolsLabel.setFont(fontWest);
		m_weaponLabel = new JLabel("Weapon :");
		m_weaponLabel.setForeground(Color.BLACK);
		m_weaponLabel.setFont(fontWest);

		m_mineralsLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		m_toolsLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		m_weaponLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		mineralsImage.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		toolsImage.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		m_weaponImage.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		MinToolsWeapon.add(Box.createVerticalGlue());
		MinToolsWeapon.add(m_mineralsLabel);
		MinToolsWeapon.add(mineralsImage);
		MinToolsWeapon.add(Box.createVerticalGlue());
		MinToolsWeapon.add(m_toolsLabel);
		MinToolsWeapon.add(toolsImage);
		MinToolsWeapon.add(Box.createVerticalGlue());
		MinToolsWeapon.add(m_weaponLabel);
		MinToolsWeapon.add(m_weaponImage);
		MinToolsWeapon.add(Box.createVerticalGlue());

//		Init de m_HPStamina et de son BL		
		JPanel HPStamina = new JPanel();
		BoxLayout BLWestSorth = new BoxLayout(HPStamina, BoxLayout.Y_AXIS);
		HPStamina.setLayout(BLWestSorth);
		HPStamina.setBackground(Color.DARK_GRAY);

//		m_HPStamina avec les deux barres d'HP et DP
//		m_HPStamina.setPreferredSize(new Dimension(100, 100));
		HPStamina.add(Box.createVerticalGlue());
		LineBorder progressBarBorder = (LineBorder) BorderFactory.createLineBorder(Color.BLACK, 3);
		m_health = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
		m_health.setBorder(progressBarBorder);
		m_health.setForeground(Color.RED);
		m_health.setBackground(Color.GRAY);
		m_health.setMaximumSize(new Dimension(50, 200));
		m_health.setValue(100);
		HPStamina.add(m_health);
		HPStamina.add(Box.createVerticalGlue());
		m_drone = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
		m_drone.setBorder(progressBarBorder);
		m_drone.setForeground(Color.YELLOW);
		m_drone.setBackground(Color.GRAY);
		m_drone.setMaximumSize(new Dimension(50, 200));
		m_drone.setValue(50);
		HPStamina.add(m_drone);
		HPStamina.add(Box.createVerticalGlue());
		m_West.add(HPStamina);
		m_West.add(MinToolsWeapon);

		// ATH droit

		// La font des futurs labels
		Font font = new Font(null, 0, 20);

		m_East = new JPanel();
		m_East.setBackground(Color.DARK_GRAY);
		m_East.setPreferredSize(new Dimension(120, 150));
		BoxLayout BLEast = new BoxLayout(m_East, BoxLayout.Y_AXIS);
		m_East.setLayout(BLEast);

		// La boussole
		m_compass = new CompassWidget();
		m_compass.setBackground(new Color(18, 16, 38));
		m_compass.setForeground(Color.BLACK);
		Dimension CompassSize = new Dimension(120, 120);
		m_compass.setMinimumSize(CompassSize);
		m_compass.setMaximumSize(CompassSize);
		m_compass.setPreferredSize(CompassSize);

		m_East.add(m_compass);

		// Zone des stats
		JPanel Stats = new JPanel();
		Stats.setBackground(Color.DARK_GRAY);
		Dimension statsDimension = new Dimension(120, 130);
		Stats.setMaximumSize(statsDimension);
		Stats.setPreferredSize(statsDimension);

		// Border pour les stats
		Border blackLineBorder = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder statsBorder = BorderFactory.createTitledBorder(blackLineBorder, "Stats");
		statsBorder.setTitleColor(Color.BLACK);
		statsBorder.setTitleJustification(TitledBorder.CENTER);
		Stats.setBorder(statsBorder);

		// Label du niveau
		m_level = new JLabel("Level : 45");
		m_level.setForeground(Color.BLACK);
		m_level.setFont(font);
		m_level.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		Stats.add(m_level);

		// Label des points
		m_score = new JLabel("450 pts");
		m_score.setForeground(Color.BLACK);
		m_score.setFont(font);
		m_score.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		Stats.add(m_score);

		// Cadrant de l'horloge
		JPanel timePanel = new JPanel();
		timePanel.setBackground(Color.BLACK);

		// Border de l'horloge
		Border timePanelBorder = BorderFactory.createLineBorder(Color.GRAY);
		timePanel.setBorder(timePanelBorder);

		// Label Temps
		m_time = new JLabel("4 : 35");
		m_time.setForeground(Color.RED);
		m_time.setFont(new Font("monospaced", 0, 20));
		m_time.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		timePanel.add(m_time);
		Stats.add(timePanel);
		m_East.add(Stats);

		// Espace munition
		m_ammoPanel = new JPanel();
		m_ammoPanel.setBackground(Color.DARK_GRAY);

		// Ajout d'une border à ammo
		Border ammoBorder = BorderFactory.createLineBorder(Color.BLACK);
		m_ammoTitledBorder = BorderFactory.createTitledBorder(ammoBorder, "Ammo");
		m_ammoTitledBorder.setTitleColor(Color.BLACK);
		m_ammoTitledBorder.setTitleJustification(TitledBorder.CENTER);
		m_ammoPanel.setBorder(m_ammoTitledBorder);

		// Label des munitions
		m_ammo = new JLabel("10/10");
		m_ammo.setFont(font);
		m_ammo.setForeground(Color.BLACK);

		m_ammoPanel.add(m_ammo);
		m_ammoPanel.setMaximumSize(new Dimension(120, 30));
		m_East.add(m_ammoPanel);

		// Zone des boutons d'upgrade
		m_upgrade = new JPanel();
		m_upgrade.setBackground(Color.DARK_GRAY);

		// Ajout d'une border à upgrade
		Border upgradeBorder = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder upgradeTitledBorder = BorderFactory.createTitledBorder(upgradeBorder, "Upgrades");
		upgradeTitledBorder.setTitleColor(Color.BLACK);
		upgradeTitledBorder.setTitleJustification(TitledBorder.CENTER);
		m_upgrade.setBorder(upgradeTitledBorder);
		
		//TODO implémenter un affichage dynamique pour les upgrade après avoir implémenté les upgrades
		m_availableUpgrades=new Hashtable<Upgrade, JButton>();
				
		// Création d'un premier bouton
		JButton upgradeButton1 = new JButton("HP Max +100");
		upgradeButton1.setForeground(Color.BLACK);
		upgradeButton1.setBackground(new Color(29, 73, 25));

		// Ajout d'une border au bouton
		Border inset = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		LineBorder buttonLineBorder = (LineBorder) BorderFactory.createLineBorder(new Color(52, 109, 46));
		Border buttonBorder = BorderFactory.createCompoundBorder(buttonLineBorder, inset);
		upgradeButton1.setBorder(buttonBorder);

		m_upgrade.add(upgradeButton1);
		m_East.add(m_upgrade);
		
		m_viewModePanel = new JPanel();
		Border viewModeBorder = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder viewModeTitledBorder = BorderFactory.createTitledBorder(viewModeBorder, "View Mode :");
		viewModeTitledBorder.setTitleColor(Color.BLACK);
		viewModeTitledBorder.setTitleJustification(TitledBorder.CENTER);
		m_viewModePanel.setBorder(viewModeTitledBorder);
		m_viewModePanel.setBackground(Color.DARK_GRAY);
		m_viewModeLabel = new JLabel(m_weaponArray[3]);
		m_viewModePanel.add(m_viewModeLabel);
		
	}

//TODO
	public void refreshHUD() {
		Model model = Model.getModel();
		Tank tank = model.getTank();
		Drone drone = model.getDrone();
		TankBody tankBody = tank.getBody();
		Turret tankTurret = tank.getTurret();
		Entity played = model.getPlayed();
		
		//Timer
		long time = model.getTime();
		long seconde = time / 1000;
		long minutes = seconde / 60;
		seconde = seconde % 60;
		String timeString = "";
		if (minutes <= 9) {
			timeString += "0" + minutes;
		} else {
			timeString += minutes;
		}
		if (time % 1000 < 500)
			timeString += ":";
		else
			timeString += " ";
		if (seconde <= 9) {
			timeString += "0" + seconde;
		} else {
			timeString += seconde;
		}
		
		//Boussole
		m_time.setText(timeString);
		float x = played.getX() + played.getWidth() / 2;
		float y = played.getY() + played.getHeight() / 2;

		m_compass.resetArrow();
		LinkedList<Entity> markers = model.getEntities(MyEntities.Marker);

		for (Entity mark : markers) {
			float xMark = mark.getX();
			float yMark = mark.getY();
			int throughToreW = throughToreW((int) x, (int) xMark);
			int throughToreH = throughToreH((int) y, (int) yMark);
			xMark += model.getGrid().getNbCellsX() * throughToreW;
			yMark += model.getGrid().getNbCellsY() * throughToreH;

			// TODO Si les drapeaux sont plus grand, prendre en compte leur taille
			double angle = (double) Math.atan(((double) (yMark - y)) / ((double) (xMark - x)));
			angle = Math.toDegrees(angle);
			if (xMark < x) {
				angle += 180;
			}
			m_compass.addArrow(null, (int) angle);
		}

		Model.getModel().getDrone();
		m_level.setText("Level : ".concat(Integer.toString(tankBody.getLevel())));
		
		//Barres HP et Drone
		m_health.setMaximum(tankBody.getMaxHealth());
		m_health.setValue(tankBody.getHealth());
		m_drone.setMaximum(drone.getMaxHealth());
		m_drone.setValue(drone.getHealth());

		//Minerals, Electronics, Weapons et Markers
		m_toolsLabel.setText("Electronics :".concat(Integer.toString(tank.getInventory().getQuantity(MaterialType.ELECTRONIC))));
		m_mineralsLabel
				.setText("Minerals :".concat(Integer.toString(tank.getInventory().getQuantity(MaterialType.MINERAL))));
		switch (model.getVisionType()) {
			case TANK:
				m_weaponLabel.setText("Weapon :");
				m_weaponImage.setIcon(m_weaponArray[tankTurret.getWeapon()]);
				m_ammoTitledBorder.setTitle("Ammo");
				m_ammo.setText("10 / 10");
				m_ammoPanel.setBorder(m_ammoTitledBorder);
				m_ammoPanel.repaint();
				m_East.remove(m_viewModePanel);
				m_East.add(m_upgrade);
				m_East.repaint();
								
				/*
				 * for each m_availableUpgrade as upgrade
				 * if upgrade.available() == true
				 * m_upgrade.add(m_availableUpgrade.get(upgrade));
				 */
				
				
				break;
			case RESSOURCES:
				m_weaponLabel.setText("Marker :");
				m_weaponImage.setIcon(m_weaponArray[5]);
				m_ammoTitledBorder.setTitle("Marker");
				m_ammo.setText(markers.size() + " / " + drone.MARKER_MAX);
				m_ammoPanel.setBorder(m_ammoTitledBorder);
				m_ammoPanel.repaint();
				
				m_East.remove(m_upgrade);
				m_viewModeLabel.setIcon(m_weaponArray[3]);
				m_East.add(m_viewModePanel);
				m_East.repaint();
				break;
			case ENEMIES:
				m_weaponLabel.setText("Marker :");
				m_weaponImage.setIcon(m_weaponArray[5]);
				m_ammoTitledBorder.setTitle("Marker");
				m_ammo.setText(markers.size() + " / " + drone.MARKER_MAX);
				m_ammoPanel.setBorder(m_ammoTitledBorder);
				m_ammoPanel.repaint();
				
				m_East.remove(m_upgrade);
				m_viewModeLabel.setIcon(m_weaponArray[4]);
				m_East.add(m_viewModePanel);
				m_East.repaint();
				break;
		}

	}

	public static final int DIRECT = 0;
	public static final int THROUGH_POSITIVE = 1;
	public static final int THROUGH_NEGATIVE = -1;

	int throughToreW(int x, int xMark) {
		int dstXdir = Math.abs(x - xMark);
		int dstXtore = Math.min(x, xMark) + Model.getModel().getGrid().getNbCellsX() - Math.max(x, xMark);
		if (dstXdir <= dstXtore) {
			return DIRECT;
		} else if (xMark < x) {
			return THROUGH_POSITIVE;
		} else {
			return THROUGH_NEGATIVE;
		}
	}

	int throughToreH(int y, int yMark) {
		int dstYdir = Math.abs(y - yMark);
		int dstYtore = Math.min(y, yMark) + Model.getModel().getGrid().getNbCellsY() - Math.max(y, yMark);
		if (dstYdir <= dstYtore) {
			return DIRECT;
		} else if (yMark < y) {
			return THROUGH_POSITIVE;
		} else {
			return THROUGH_NEGATIVE;
		}
	}

}
