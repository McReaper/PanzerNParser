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
	public static void main(String[] args) {
		int entity_length = 3;
		Entity enemy = new Enemy(0, 0, entity_length, entity_length, null, null); // commenté l'automate dans Entity
		System.out.println("Ennemi créé.");
		int dist = 18;
		LinkedList<Coords> cells = enemy.getCellsInDiagonalDir(MyDirection.SOUTHEAST, dist);
		cells.addAll(enemy.getCellsInDiagonalDir(MyDirection.SOUTHWEST, dist));
		cells.addAll(enemy.getCellsInDiagonalDir(MyDirection.NORTHEAST, dist));
		cells.addAll(enemy.getCellsInDiagonalDir(MyDirection.NORTHWEST, dist));
		char[][] tab = new char[entity_length + dist*2 + 1][entity_length + dist*2 + 1];
		for (Coords coord : cells) {
			tab[(int) (coord.Y + dist)][(int) (coord.X + dist)] = '#';
		}
		for (int y = 0; y < entity_length + dist*2; y++) {
			System.out.println();
			for (int x = 0; x < entity_length + dist*2; x++) {
				if (tab[x][y] == '#') {
					System.out.print("" + tab[x][y] + " ");
				} else {
					System.out.print("- ");
				}
			}
		}
	}
}
