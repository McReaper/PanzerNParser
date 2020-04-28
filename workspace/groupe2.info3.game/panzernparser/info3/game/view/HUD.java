package info3.game.view;

import java.awt.BorderLayout;
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

	JPanel West;
	JPanel East;
	JPanel MinToolsWeapon;
	JLabel Minerals;
	JLabel minerals;
	JLabel Tools;
	JLabel tools;
	JLabel Weapon;
	JLabel weapon;
	JPanel HPStamina;
	JProgressBar health;
	JProgressBar drone;

	public HUD() {
		West = new JPanel();
		West.setBackground(Color.DARK_GRAY);
		West.setPreferredSize(new Dimension(150, 150));
		GridLayout GLWest = new GridLayout(2, 0);
		West.setLayout(GLWest);

		MinToolsWeapon = new JPanel();
		BoxLayout BLMinToolsWeapon = new BoxLayout(MinToolsWeapon, BoxLayout.Y_AXIS);
		MinToolsWeapon.setLayout(BLMinToolsWeapon);
		MinToolsWeapon.setBackground(Color.DARK_GRAY);// ce

		Minerals = new JLabel(new ImageIcon("sprites/Trou.png"));
		Tools = new JLabel(new ImageIcon("sprites/Trou.png"));
		Weapon = new JLabel(new ImageIcon("sprites/Trou.png"));

		minerals = new JLabel("Minerals : ");
		tools = new JLabel("Tools : ");
		weapon = new JLabel("Current Weapon : ");

		Minerals.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		Tools.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		Weapon.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		minerals.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		tools.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		weapon.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		MinToolsWeapon.add(Box.createVerticalGlue());
		MinToolsWeapon.add(minerals);
		MinToolsWeapon.add(Minerals);
		MinToolsWeapon.add(Box.createVerticalGlue());
		MinToolsWeapon.add(tools);
		MinToolsWeapon.add(Tools);
		MinToolsWeapon.add(Box.createVerticalGlue());
		MinToolsWeapon.add(weapon);
		MinToolsWeapon.add(Weapon);
		MinToolsWeapon.add(Box.createVerticalGlue());

//		Init de HPStamina et de son BL		
		HPStamina = new JPanel();
		BoxLayout BLWestSorth = new BoxLayout(HPStamina, BoxLayout.Y_AXIS);
		HPStamina.setLayout(BLWestSorth);
		HPStamina.setBackground(Color.DARK_GRAY);

//		HPStamina avec les deux barres d'HP et DP
//		HPStamina.setPreferredSize(new Dimension(100, 100));
		HPStamina.add(new Box.Filler(new Dimension(0, 0), new Dimension(15, 15), new Dimension(15, 15)));
		health = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
		health.setForeground(Color.RED);
		health.setMaximumSize(new Dimension(50, 200));
		health.setValue(100);
		HPStamina.add(health);
		HPStamina.add(new Box.Filler(new Dimension(0, 0), new Dimension(15, 15), new Dimension(15, 15)));
		drone = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
		drone.setForeground(Color.YELLOW);
		drone.setMaximumSize(new Dimension(50, 200));
		drone.setValue(50);
		HPStamina.add(drone);
		HPStamina.add(new Box.Filler(new Dimension(0, 0), new Dimension(15, 15), new Dimension(15, 15)));
		West.add(HPStamina);
		West.add(MinToolsWeapon);
	}
}
