package info3.game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class HUD {

	JPanel m_West;
	JPanel m_East;

	JPanel m_MinToolsWeapon;
	JLabel m_mineralsLabel;
	JLabel m_mineralsImage;
	JLabel m_toolsLabel;
	JLabel m_toolsImage;
	JLabel m_weaponLabel;
	JLabel m_weaponImage;
	JPanel m_HPStamina;
	JProgressBar m_health;
	JProgressBar m_drone;

	public HUD() {
		m_West = new JPanel();
		m_West.setBackground(Color.DARK_GRAY);
		m_West.setPreferredSize(new Dimension(150, 150));
		GridLayout GLm_West = new GridLayout(2, 0);
		m_West.setLayout(GLm_West);

		m_MinToolsWeapon = new JPanel();
		BoxLayout BLm_Minm_toolsm_weapon = new BoxLayout(m_MinToolsWeapon, BoxLayout.Y_AXIS);
		m_MinToolsWeapon.setLayout(BLm_Minm_toolsm_weapon);
		m_MinToolsWeapon.setBackground(Color.DARK_GRAY);// ce

		m_mineralsImage = new JLabel(new ImageIcon("sprites/Trou.png"));
		m_toolsImage = new JLabel(new ImageIcon("sprites/Trou.png"));
		m_weaponImage = new JLabel(new ImageIcon("sprites/Trou.png"));

		m_mineralsLabel = new JLabel("Minerals : ");
		m_toolsLabel = new JLabel("Tools : ");
		m_weaponLabel = new JLabel("Current Weapon : ");

		m_mineralsLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		m_toolsLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		m_weaponLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		m_mineralsImage.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		m_toolsImage.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		m_weaponImage.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		m_MinToolsWeapon.add(Box.createVerticalGlue());
		m_MinToolsWeapon.add(m_mineralsLabel);
		m_MinToolsWeapon.add(m_mineralsImage);
		m_MinToolsWeapon.add(Box.createVerticalGlue());
		m_MinToolsWeapon.add(m_toolsLabel);
		m_MinToolsWeapon.add(m_toolsImage);
		m_MinToolsWeapon.add(Box.createVerticalGlue());
		m_MinToolsWeapon.add(m_weaponLabel);
		m_MinToolsWeapon.add(m_weaponImage);
		m_MinToolsWeapon.add(Box.createVerticalGlue());

//		Init de m_HPStamina et de son BL		
		m_HPStamina = new JPanel();
		BoxLayout BLm_WestSorth = new BoxLayout(m_HPStamina, BoxLayout.Y_AXIS);
		m_HPStamina.setLayout(BLm_WestSorth);
		m_HPStamina.setBackground(Color.DARK_GRAY);

//		m_HPStamina avec les deux barres d'HP et DP
//		m_HPStamina.setPreferredSize(new Dimension(100, 100));
		m_HPStamina.add(Box.createVerticalGlue());
		m_health = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
		m_health.setForeground(Color.RED);
		m_health.setMaximumSize(new Dimension(50, 200));
		m_health.setValue(100);
		m_HPStamina.add(m_health);
		m_HPStamina.add(Box.createVerticalGlue());
		m_drone = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
		m_drone.setForeground(Color.YELLOW);
		m_drone.setMaximumSize(new Dimension(50, 200));
		m_drone.setValue(50);
		m_HPStamina.add(m_drone);
		m_HPStamina.add(Box.createVerticalGlue());
		m_West.add(m_HPStamina);
		m_West.add(m_MinToolsWeapon);

		m_East = new JPanel();
	}
}
