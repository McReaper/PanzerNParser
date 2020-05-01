package info3.game.model;

import java.util.LinkedList;

import info3.game.automaton.action.LsAction;
import info3.game.model.entities.Entity;
import info3.game.model.entities.EntityFactory.MyEntities;
import info3.game.model.entities.MovingEntity;
import info3.game.model.entities.Shot;

public class CollisionManager {

	public CollisionManager() {
	}

	public void controlCollisions() {
		controlCollisionsShotsEntity();

	}

	public void controlCollisionsShotsEntity() {
		// Regardons si les shots touches quelque chose
		LinkedList<Entity> shots;
		LinkedList<Entity> enemy;
		LinkedList<Entity> tankPlayer;
		LinkedList<Entity> walls;
		shots = Model.getModel().getEntities(MyEntities.Shot);
		enemy = Model.getModel().getEntities(MyEntities.Enemy);
		tankPlayer = Model.getModel().getEntities(MyEntities.TankBody);
		walls = Model.getModel().getEntities(MyEntities.Wall);

		for (Entity entShot : shots) {

			// verifie si le shot est encore encore en vie
			if (entShot.getCurrentAction() != LsAction.Explode) {
				// regarde si le shot traverse un enemy
				for (Entity entEnemy : enemy) {
					/*
					 * si c'est l'entité qui l'a tiré il ne peut pas collider avec pour éviter les
					 * problèmes d'explosion alors qu'il est meme pas sorti du canon
					 */
					if (!entEnemy.equals(((Shot) entShot).getOwner())) {

						if (entEnemy.isInMe(entShot.getX(), entShot.getY())) {
							((MovingEntity) entEnemy).collide(entShot.getDammageDealt());
							((MovingEntity) entShot).collide(entEnemy.getDammageDealt());
						}
					}

				}

				for (Entity entPlayer : tankPlayer) {
					/*
					 * si c'est l'entité qui l'a tiré il ne peut pas collider avec pour éviter les
					 * problèmes d'explosion alors qu'il est meme pas sorti du canon
					 */
					if (!entPlayer.equals(((Shot) entShot).getOwner())) {

						if (entPlayer.isInMe(entShot.getX(), entShot.getY())) {
							((MovingEntity) entPlayer).collide(entShot.getDammageDealt());
							((MovingEntity) entShot).collide(entPlayer.getDammageDealt());
						}
					}
				}

				for (Entity entWall : walls) {
					if (entWall.isInMe(entShot.getX(), entShot.getY())) {
						//((MovingEntity) entWall).collide(entShot.getDammageDealt());
						((MovingEntity) entShot).collide(entWall.getDammageDealt());
					}
				}

			}
		}
	}
	
	
}
