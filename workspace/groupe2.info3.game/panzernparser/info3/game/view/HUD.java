package info3.game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

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

public class HUD {
	
	public static final int MINIMAL_WIDTH = 2 * 120;
	public static final int MINIMAL_HEIGHT = 400;
	

	JPanel m_West;
	JPanel m_East;

	JLabel m_mineralsLabel;
	JLabel m_toolsLabel;
	JLabel m_weaponLabel;
	JProgressBar m_health;
	JProgressBar m_drone;
	
	JLabel m_score;
	JLabel m_level;
	JLabel m_time;
	JLabel m_ammo;
	CompassWidget m_compass;
	JPanel m_upgrade;

	public HUD() {
		//La font des futurs labels
		Font fontWest = new Font(null, 0, 15);
		
		m_West = new JPanel();
		m_West.setBackground(Color.DARK_GRAY);
		m_West.setPreferredSize(new Dimension(120, 150));
		GridLayout GLWest = new GridLayout(2, 0);
		m_West.setLayout(GLWest);

		JPanel MinToolsWeapon = new JPanel();
		BoxLayout BLMintoolsweapon = new BoxLayout(MinToolsWeapon, BoxLayout.Y_AXIS);
		MinToolsWeapon.setLayout(BLMintoolsweapon);
		MinToolsWeapon.setBackground(Color.DARK_GRAY);// ce

		JLabel mineralsImage = new JLabel(new ImageIcon("sprites/Trou.png"));
		JLabel toolsImage = new JLabel(new ImageIcon("sprites/Trou.png"));
		JLabel weaponImage = new JLabel(new ImageIcon("sprites/Trou.png"));

		m_mineralsLabel = new JLabel("Minerals :");
		m_mineralsLabel.setForeground(Color.BLACK);
		m_mineralsLabel.setFont(fontWest);
		m_toolsLabel = new JLabel("Tools :");
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
		weaponImage.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		MinToolsWeapon.add(Box.createVerticalGlue());
		MinToolsWeapon.add(m_mineralsLabel);
		MinToolsWeapon.add(mineralsImage);
		MinToolsWeapon.add(Box.createVerticalGlue());
		MinToolsWeapon.add(m_toolsLabel);
		MinToolsWeapon.add(toolsImage);
		MinToolsWeapon.add(Box.createVerticalGlue());
		MinToolsWeapon.add(m_weaponLabel);
		MinToolsWeapon.add(weaponImage);
		MinToolsWeapon.add(Box.createVerticalGlue());

//		Init de m_HPStamina et de son BL		
		JPanel HPStamina = new JPanel();
		BoxLayout BLWestSorth = new BoxLayout(HPStamina, BoxLayout.Y_AXIS);
		HPStamina.setLayout(BLWestSorth);
		HPStamina.setBackground(Color.DARK_GRAY);

//		m_HPStamina avec les deux barres d'HP et DP
//		m_HPStamina.setPreferredSize(new Dimension(100, 100));
		HPStamina.add(Box.createVerticalGlue());
		LineBorder progressBarBorder = (LineBorder)BorderFactory.createLineBorder(Color.BLACK, 3);
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
		
		//ATH droit
		
		//La font des futurs labels
		Font font = new Font(null, 0, 20);
		
		m_East = new JPanel();
		m_East.setBackground(Color.DARK_GRAY);
		m_East.setPreferredSize(new Dimension(120,150));
		BoxLayout BLEast = new BoxLayout(m_East, BoxLayout.Y_AXIS);
		m_East.setLayout(BLEast);
		
		//La boussole
		m_compass = new CompassWidget();
		m_compass.setBackground(new Color(18,16,38));
		m_compass.setForeground(Color.BLACK);
		Dimension CompassSize = new Dimension(120,120);
		m_compass.setMinimumSize(CompassSize);
		m_compass.setMaximumSize(CompassSize);
		m_compass.setPreferredSize(CompassSize);
		
		m_East.add(m_compass);
		
		//Zone des stats
		JPanel Stats = new JPanel();
		Stats.setBackground(Color.DARK_GRAY);
		Dimension statsDimension = new Dimension(120,130);
		Stats.setMaximumSize(statsDimension);
		Stats.setPreferredSize(statsDimension);
		
		//Border pour les stats
		Border blackLineBorder = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder statsBorder = BorderFactory.createTitledBorder(blackLineBorder,"Stats");
		statsBorder.setTitleColor(Color.BLACK);
		statsBorder.setTitleJustification(TitledBorder.CENTER);
		Stats.setBorder(statsBorder);
		
		
		
		//Label du niveau
		m_level = new JLabel("Level : 45");
		m_level.setForeground(Color.BLACK);
		m_level.setFont(font);
		m_level.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		
		Stats.add(m_level);
		
		//Label des points
		m_score = new JLabel("450 pts");
		m_score.setForeground(Color.BLACK);
		m_score.setFont(font);
		m_score.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		
		Stats.add(m_score);
		
		//Cadrant de l'horloge
		JPanel timePanel = new JPanel();
		timePanel.setBackground(Color.BLACK);
		
		//Border de l'horloge
		Border timePanelBorder = BorderFactory.createLineBorder(Color.GRAY);
		timePanel.setBorder(timePanelBorder);
		
		//Label Temps
		m_time = new JLabel("4 : 35");
		m_time.setForeground(Color.RED);
		m_time.setFont(font);
		m_time.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		
		timePanel.add(m_time);
		Stats.add(timePanel);
		m_East.add(Stats);
		
		//Espace munition
		JPanel ammoPanel = new JPanel();
		ammoPanel.setBackground(Color.DARK_GRAY);

		//Ajout d'une border à ammo
		Border ammoBorder = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder ammoTitledBorder = BorderFactory.createTitledBorder(ammoBorder,"Ammo");
		ammoTitledBorder.setTitleColor(Color.BLACK);
		ammoTitledBorder.setTitleJustification(TitledBorder.CENTER);
		ammoPanel.setBorder(ammoTitledBorder);

		//Label des munitions
		m_ammo = new JLabel("5/10");
		m_ammo.setFont(font);
		m_ammo.setForeground(Color.BLACK);
		
		ammoPanel.add(m_ammo);
		ammoPanel.setMaximumSize(new Dimension(120,30));
		m_East.add(ammoPanel);
		
		//Zone des boutons d'upgrade
		m_upgrade = new JPanel();
		m_upgrade.setBackground(Color.DARK_GRAY);
		
		//Ajout d'une border à upgrade
		Border upgradeBorder = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder upgradeTitledBorder = BorderFactory.createTitledBorder(upgradeBorder,"Upgrades");
		upgradeTitledBorder.setTitleColor(Color.BLACK);
		upgradeTitledBorder.setTitleJustification(TitledBorder.CENTER);
		m_upgrade.setBorder(upgradeTitledBorder);
		
		//Création d'un premier bouton
		JButton upgradeButton1 = new JButton("Upgrade 1");
		upgradeButton1.setForeground(Color.BLACK);
		upgradeButton1.setBackground(new Color(29,73,25));
		
		//Ajout d'une border au bouton
		Border inset = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		LineBorder buttonLineBorder = (LineBorder)BorderFactory.createLineBorder(new Color(52,109,46));
		Border buttonBorder = BorderFactory.createCompoundBorder(buttonLineBorder, inset);
		upgradeButton1.setBorder(buttonBorder);
		
		m_upgrade.add(upgradeButton1);
		m_East.add(m_upgrade);
	}
}
