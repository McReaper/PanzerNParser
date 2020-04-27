package info3.game.model.entities;

import java.util.LinkedList;

import info3.game.automaton.MyDirection;
import info3.game.model.Model.Coords;

/**
 * Classe de test pour les cases sélectionnées en partant d'une entité dans une
 * direction donnée et un rayon donné.
 * 
 * @author malodv
 *
 */
public class EntityTest {

//	static void detectionDiagTest() {
//		int entity_length = 3;
//		Entity enemy = new Enemy(0, 0, entity_length, entity_length-1, null, null); // commenté l'automate dans Entity
//		System.out.println("Ennemi créé.");
//		int dist = 5;
//		LinkedList<Coords> cells = enemy.getCellsInDiagonalDir(MyDirection.NORTHWEST, dist);
////		cells.addAll(enemy.getCellsInDiagonalDir(MyDirection.NORTHWEST, dist));
////		cells.addAll(enemy.getCellsInDiagonalDir(MyDirection.NORTHEAST, dist));
////		cells.addAll(enemy.getCellsInDiagonalDir(MyDirection.SOUTHEAST, dist));
//		char[][] tab = new char[entity_length * 2 + dist * 2 + 1][entity_length * 2 + dist * 2 + 1];
//		for (Coords coord : cells) {
//			int ind_x = (int) (coord.X + Math.max(entity_length, dist) + Math.min(entity_length, dist));
//			int ind_y = (int) (coord.Y + Math.max(entity_length, dist) + Math.min(entity_length, dist));
//			tab[ind_x][ind_y] = '#';
//		}
//		for (int y = 0; y < entity_length * 2 + dist * 2; y++) {
//			System.out.println();
//			for (int x = 0; x < entity_length * 2 + dist * 2; x++) {
//				if (tab[x][y] == '#') {
//					System.out.print(tab[x][y] + " ");
//				} else {
//					System.out.print("- ");
//				}
//			}
//		}
//		System.out.println();
//	}
//
//	static void detectionOrthTest() {
//		int entity_length = 1;
//		Entity enemy = new Enemy(0, 0, entity_length, entity_length, null, null); // commenté l'automate dans Entity
//		System.out.println("Ennemi créé.");
//		int dist = 10;
//		LinkedList<Coords> cells = enemy.getCellsInOrthogonalDir(MyDirection.NORTH, dist);
//		cells.addAll(enemy.getCellsInOrthogonalDir(MyDirection.WEST, dist));
////		cells.addAll(enemy.getCellsInOrthogonalDir(MyDirection.EAST, dist));
//		cells.addAll(enemy.getCellsInOrthogonalDir(MyDirection.SOUTH, dist));
//		char[][] tab = new char[entity_length * 2 + dist * 2 + 1][ entity_length * 2 + dist * 2 + 1];
//		for (Coords coord : cells) {
//			int ind_x = (int) (coord.X + Math.max(entity_length, dist) + Math.min(entity_length, dist));
//			int ind_y = (int) (coord.Y + Math.max(entity_length, dist) + Math.min(entity_length, dist));
//			tab[ind_x][ind_y] = '#';
//		}
//		for (int y = 0; y < entity_length * 2 + dist * 2 ; y++) {
//			System.out.println();
//			for (int x = 0; x < entity_length * 2 + dist * 2; x++) {
//				if (tab[x][y] == '#') {
//					System.out.print(tab[x][y] + " ");
//				} else {
//					System.out.print("- ");
//				}
//			}
//		}
//		System.out.println();
//	}

	static void detectionConeTest() {
		int entity_length = 8;
		Entity enemy = new Enemy(0, 0, entity_length, entity_length - 5, null); // commenté l'automate dans Entity
		System.out.println("Ennemi créé.");
		int dist = 10;
		MyDirection dir = MyDirection.NORTHWEST;
		LinkedList<Coords> cells = enemy.getDetectionCone(dir, dist);
		char[][] tab = new char[entity_length * 2 + dist * 2 + 1][entity_length * 2 + dist * 2 + 1];
		for (Coords coord : cells) {
			int ind_x = (int) (coord.X + Math.max(entity_length, dist) + Math.min(entity_length, dist));
			int ind_y = (int) (coord.Y + Math.max(entity_length, dist) + Math.min(entity_length, dist));
			tab[ind_x][ind_y] = '#';
		}
		for (int y = 0; y < entity_length * 2 + dist * 2; y++) {
			System.out.println();
			for (int x = 0; x < entity_length * 2 + dist * 2; x++) {
				if (tab[x][y] == '#') {
					System.out.print(tab[x][y] + " ");
				} else {
					System.out.print("- ");
				}
			}
		}
		System.out.println();
	}

//	static void rectangleTriangleTest() {
//		// Remarque : le triangle rectangle considère les points de sa diagonale en
//		// dehors
//		RectangleTriangle tri = new RectangleTriangle(0, 3, 5, 3, 0, 8);
//		LinkedList<Coords> coords_to_test = new LinkedList<Coords>();
//		coords_to_test.add(new Coords(0, 3)); // A -> dehors
//		coords_to_test.add(new Coords(5, 3)); // B -> dehors
//		coords_to_test.add(new Coords(0, 8)); // C -> dehors
//		coords_to_test.add(new Coords(1.5, 4.5)); // Un point en plein centre
//		coords_to_test.add(new Coords(3, 3)); // Une coordonée sur un coté (dehors)
//		coords_to_test.add(new Coords(2.5, 2.5)); // Un point en dehors du triangle
//		coords_to_test.add(new Coords(-0.5, 5.5)); // Un point en dehors
//		coords_to_test.add(new Coords(0.5, 3.5)); // Un point dedans
//		coords_to_test.add(new Coords(2.5, 5.5)); // Un point sur la diagonale (en dehors)
//		coords_to_test.add(new Coords(1.5, 6.5)); // Un point sur la diagonale (en dehors)
//		for (Coords coord : coords_to_test) {
//			if (tri.isInMe(coord.X, coord.Y)) {
//				System.out.println("Is in triangle : (" + coord.X + ";" + coord.Y + ")");
//			} else {
//				System.out.println("Is not in triangle : (" + coord.X + ";" + coord.Y + ")");
//			}
//		}
//	}

	public static void main(String[] args) {
		// detectionDiagTest();
		// detectionOrthTest();
		// rectangleTriangleTest();
		detectionConeTest();
	}
}
