package info3.game.model;

import java.rmi.UnexpectedException;
import java.util.LinkedList;

import info3.game.automaton.LsKey;
import info3.game.automaton.MyCategory;
import info3.game.model.entities.Clue;
import info3.game.model.entities.Drone;
import info3.game.model.entities.Droppable;
import info3.game.model.entities.Enemy;
import info3.game.model.entities.Entity;
import info3.game.model.entities.EntityFactory.MyEntities;
import info3.game.model.entities.Ground;
import info3.game.model.entities.Marker;
import info3.game.model.entities.Shot;
import info3.game.model.entities.TankBody;
import info3.game.model.entities.Turret;

public class Model {

	public static Model m_model;

	public static final int PLAYER_TANK = 1;
	public static final int PLAYER_DRONE = 2;
	// Controller m_controller; //pour envoyer des information utiles.
	Grid m_grid;
	public Tank m_tank;
	LinkedList<Entity> m_entities;
	public LinkedList<LsKey> m_keyPressed;
	public int m_player;// 1 pour le tank et 2 pour le drone
	Marker[] m_markers;
	Clue m_clue;

	public Model() {
		// Génère la liste des automates
		m_model = this;
		m_markers = new Marker[3];
		m_keyPressed = new LinkedList<LsKey>();
		m_entities = new LinkedList<Entity>();
		// permet la recupération du body et du turret du tank
		int indexOfTankBody = -1;
		int indexOfTurret = -1;
		// Génère la grille du jeu qui va créer a son tour toutes les entités et mettre
		// la liste des entités à jour. La grille doit connaitre ses patterns lors de sa
		// création, le model doit donc lui donner.

		// Version de test ci-dessous :
		try {
			m_grid = new Grid(this);
		} catch (UnexpectedException e) {
			e.printStackTrace();
			System.err.println("Impossible de créer la grille !");
			// La il faudrait sortir du programme, en appelant le controller, pour arrêter
			// la musique et les autres exécutions auxiliaires en cours.
		}
		int i = 0;
		for (Entity entity : m_entities) {
			if (entity instanceof TankBody) {
				indexOfTankBody = i;
			}
			if (entity instanceof Turret) {
				indexOfTurret = i;
			}
			i++;
		}

		if (indexOfTankBody != -1 && indexOfTurret != -1) {
			m_tank = new Tank(m_entities.get(indexOfTankBody), m_entities.get(indexOfTurret));
			m_player = PLAYER_TANK;
		} else {
			System.out.println("ERROR : Pas de création du TankBody ET du tankChassis");
		}

	}

	public void addMarker(Marker marker) {
		/* Pour les tests, on utilise 3 comme limite de marker */

		if (marker != null) {
			for (int i = 0; i < 3; i++) {
				if (markerIsEmpty(m_markers[i])) {
					m_markers[i] = marker;
					return;
				}
			}
			m_markers[0] = marker;
		}
	}

	public boolean markerIsEmpty(Marker marker) {
		return marker == null;
	}

	public void addClue(Clue c) {
		if (c != null)
			m_clue = c;
	}

	public Clue getClue() {
		return m_clue;
	}
	
	public void cleanClue() {
		m_clue = null;
	}

	public void step(long elapsed) {
		// Effectue un pas de simulation sur chaque entités
		for (Entity entity : m_entities) {
			entity.step(elapsed);
		}
		m_tank.step();

	}

	public static Model getModel() {
		return m_model;
	}

	public Grid getGrid() {
		return m_grid;
	}

	public LinkedList<Entity> getEntities() {
		return m_entities;
	}

	public void addKeyPressed(LsKey temp) {
		if (!m_keyPressed.contains(temp))
			m_keyPressed.add(temp);
		return;
	}

	public void removeKeyPressed(LsKey temp) {
		if (m_keyPressed.contains(temp))
			m_keyPressed.remove(temp);
		return;
	}

	public void addEntity(Entity e) {
		m_entities.add(e);
	}

	public static class Coords {

		public double X, Y;

		public Coords(double x, double y) {
			X = x;
			Y = y;
		}

		@Override
		public boolean equals(Object obj) {
			return (((Coords) obj).X == this.X && ((Coords) obj).Y == this.Y);
		}

		public Coords translate(double offX, double offY) {
			X += offX;
			Y += offY;
			return null;
		}
	}

	public boolean isInRadius(LinkedList<Coords> radius, Entity entity) {
		for (Coords coord : radius) {
			if (entity.isInMe(coord.X, coord.Y))
				return true;
		}
		return false;
	}

	public Entity closestEntity(LinkedList<Entity> entities, int x, int y) {
		Entity closest = entities.get(0);
		double min_dist = Math.pow(closest.getX() - x, 2) + Math.pow(closest.getY() - y, 2);
		for (Entity curr : entities) {
			double dist = Math.pow(curr.getX() - x, 2) + Math.pow(curr.getY() - y, 2);
			if (dist < min_dist) {
				closest = curr;
			}
		}
		return closest;
	}

	/*
	 * TODO : a modifier en fonction du HashMap de Sami Cette fonction crée une
	 * nouvelle liste d'entitées à partir d ela liste d'entité presentes dans la
	 * game et de la catégorie demandée
	 */
	public LinkedList<Entity> getCatEntity(MyEntities cat) {
		LinkedList<Entity> newList = new LinkedList<Entity>();
		switch (cat) {
			case Drone:
				for (Entity entity : m_entities) {
					if (entity instanceof Drone) {
						newList.add(entity);
					}
				}
				return newList;
			case Enemy:
				for (Entity entity : m_entities) {
					if (entity instanceof Enemy) {
						newList.add(entity);
					}
				}
				return newList;
			case Droppable:
				for (Entity entity : m_entities) {
					if (entity instanceof Droppable) {
						newList.add(entity);
					}
				}
				return newList;
			case Ground:
				for (Entity entity : m_entities) {
					if (entity instanceof Ground) {
						newList.add(entity);
					}
				}
				return newList;
			case Marker:
				for (Entity entity : m_entities) {
					if (entity instanceof Marker) {
						newList.add(entity);
					}
				}
				return newList;
			case Shot:
				for (Entity entity : m_entities) {
					if (entity instanceof Shot) {
						newList.add(entity);
					}
				}
				return newList;
			case TankBody:
				for (Entity entity : m_entities) {
					if (entity instanceof TankBody) {
						newList.add(entity);
					}
				}
				return newList;
			case Turret:
				for (Entity entity : m_entities) {
					if (entity instanceof Turret) {
						newList.add(entity);
					}
				}
				return newList;
			case Vein:
				for (Entity entity : m_entities) {
					if (entity instanceof Drone) {
						newList.add(entity);
					}
				}
				return newList;
			default:
				return null;
		}
	}

	public LinkedList<Entity> getCategoried(MyCategory type) {
		LinkedList<Entity> entities_to_return = new LinkedList<Entity>();
		for (Entity entity : getEntities()) {
			if (entity.getCategory() == type) {
				entities_to_return.add(entity);
			}
		}
		return entities_to_return;
	}

}
