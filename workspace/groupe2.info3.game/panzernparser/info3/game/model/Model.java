package info3.game.model;

import java.rmi.UnexpectedException;
import java.util.LinkedList;

import info3.game.automaton.LsKey;
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

	// Controller m_controller; //pour envoyer des information utiles.
	Grid m_grid;
	LinkedList<Entity> m_entities;
	public LinkedList<LsKey> m_keyPressed;

	public Model() {
		// Génère la liste des automates
		m_model = this;
		m_keyPressed = new LinkedList<LsKey>();
		m_entities = new LinkedList<Entity>();

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

	}

	public void step(long elapsed) {
		// Effectue un pas de simulation sur chaque entités
		for (Entity entity : m_entities) {
			entity.step(elapsed);
		}
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
	}
	
	public boolean isInRadius(LinkedList<Coords> radius, Entity entity) {
		for (Coords coord : radius) {
			if (entity.isInMe(coord.X, coord.Y))
				return true;
		}
		return false;
	}

	public Entity closestEntity(LinkedList<Entity> entities, int x, int y) {
		double min = 0;
		int indexMin = 0;
		if (!entities.isEmpty()) {
			if (entities.size() == 1) {
				return entities.get(0);
			} else {
				int i = 0;
				for (Entity entity : entities) {
					/*
					 * formule utilisée --> d = sqrt( pow((x2 -x1),2) + pow ((y2 - y1),2) )
					 */
					/*
					 * x2 -x1 au carré :
					 */
					double part1 = Math.pow(entity.getX() - x, 2);
					double part2 = Math.pow(entity.getY() - y, 2);
					double d = Math.sqrt(part1 + part2);
					if (i == 0) {
						min = d;
					} else if (d < min) {
						min = d;
						indexMin = i;
					}

				}
				return entities.get(indexMin);
			}
		}
		return null;
	}

	/*
	 * TODO : a modifier en fonction du HashMap de Sami
	 * Cette fonction crée une nouvelle liste d'entitées à partir d ela liste
	 * d'entité presentes dans la game et de la catégorie demandée
	 */
	public LinkedList<Entity> getCatEntity(MyEntities cat) {
		LinkedList<Entity> newList = new LinkedList<Entity>();
		switch( cat) {
			case Drone :
				for (Entity entity : m_entities) {
					if (entity instanceof Drone) {
						newList.add(entity);
					}
				}
				return newList;
			case Enemy :
				for (Entity entity : m_entities) {
					if (entity instanceof Enemy) {
						newList.add(entity);
					}
				}
				return newList;
			case Droppable :
				for (Entity entity : m_entities) {
					if (entity instanceof Droppable) {
						newList.add(entity);
					}
				}
				return newList;
			case Ground : 
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
			case Shot :
				for (Entity entity : m_entities) {
					if (entity instanceof Shot) {
						newList.add(entity);
					}
				}
				return newList;
			case TankBody :
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
			case Vein :
				for (Entity entity : m_entities) {
					if (entity instanceof Drone) {
						newList.add(entity);
					}
				}
				return newList;
				default : 
					return null;
			}
	}

}
