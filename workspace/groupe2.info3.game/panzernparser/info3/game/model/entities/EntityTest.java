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
		Entity enemy = new Enemy(0, 0, 1, 1, null, null); // commenté l'automate dans Entity
		System.out.println("Ennemi créé.");
		LinkedList<Coords> cells = enemy.getCellsInDiagonalDir(MyDirection.SOUTHEAST, 4);
		for (Coords coord : cells) {
			System.out.println("(" + coord.X + ";" + coord.Y + ")");
		}
	}
}
