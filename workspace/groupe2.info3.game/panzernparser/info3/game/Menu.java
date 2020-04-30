package info3.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Menu {

	GameMain m_gameMain;
	JPanelImaged m_mainMenu;
	Border m_buttonBorder;
	
	Menu(GameMain gameMain) {
		
		Border inset = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		LineBorder buttonLineBorder = (LineBorder)BorderFactory.createLineBorder(new Color(52,109,46),3);
		m_buttonBorder = BorderFactory.createCompoundBorder(buttonLineBorder, inset);
		
		m_gameMain = gameMain;
		m_mainMenu = new JPanelImaged();
		m_mainMenu.setImage("sprites/Menu.png");
		drawMainMenu();
	}
	
	public void drawMainMenu() {
		
		//Le layout en deux partie du menu
		GridLayout mainGrid = new GridLayout(2,1);
		m_mainMenu.setLayout(mainGrid);
		
		//Partie haute
		JPanel upperPart = new JPanel();
		upperPart.setOpaque(false);
		upperPart.setBackground(Color.DARK_GRAY);
		
		//Layout pour l'upperPart
		BoxLayout upperPartLayout = new BoxLayout(upperPart, BoxLayout.Y_AXIS);
		upperPart.setLayout(upperPartLayout);
		
		//Le bouton launch
		Font fontLaunch = new Font(null,0,30);
		JButton launch = new JButton("Lauch Game");
		Color buttonColor = new Color(22,63,23);
		launch.setBackground(buttonColor);
		launch.setForeground(Color.WHITE);
		launch.setFont(fontLaunch);
		launch.setBorder(m_buttonBorder);
		launch.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		launch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				m_gameMain.launch();
			}
			
		});
		
		//Remplissage upperPart
		upperPart.add(Box.createVerticalGlue());
		upperPart.add(launch);
		upperPart.add(Box.createRigidArea(new Dimension(10,15)));
		
		//Zone du bas
		JPanel underPart = new JPanel();
		underPart.setOpaque(false);
		underPart.setBackground(Color.DARK_GRAY);
		
		//Layout du bas
		BoxLayout underPartLayout = new BoxLayout(underPart, BoxLayout.Y_AXIS);
		underPart.setLayout(underPartLayout);
		
		//Bouton des config
		Font fontConfig = new Font(null,0,25);
		JButton config = new JButton("Configuration menu");
		config.setFont(fontConfig);
		config.setBorder(m_buttonBorder);
		config.setBackground(buttonColor);
		config.setForeground(Color.WHITE);
		config.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		
		//Remplissage bas
		underPart.add(Box.createRigidArea(new Dimension(10,15)));
		underPart.add(config);
		underPart.add(Box.createVerticalGlue());
		
		//Remplissage menu
		m_mainMenu.add(upperPart);
		m_mainMenu.add(underPart);
	}
	
	public JPanel getMainMenu() {
		return m_mainMenu;
	}
	
	public class JPanelImaged extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		Image m_image;
		
		public JPanelImaged() {
			super();
			this.setOpaque(false);
		}
		
		public void setImage(String path) {
			try {
				m_image = (BufferedImage)ImageIO.read(new File(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

		@Override
		public void paint(Graphics g) {
			int width =  this.getWidth();
			int height = m_image.getHeight(null)*width/m_image.getWidth(null);
			g.drawImage(m_image, 0, 0, width, height,null);
			g.setColor(new Color(67,67,67));
			g.fillRect(0, height-4, width, this.getHeight());
			super.paint(g);
		}
		
	}
	
}
