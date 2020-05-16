package info3.game.model;

import java.util.LinkedList;

import info3.game.automaton.MyCategory;
import info3.game.automaton.action.LsAction;
import info3.game.model.entities.Entity;
import info3.game.model.entities.EntityFactory.MyEntities;
import info3.game.model.entities.Shot;

public class CollisionManager {

	public CollisionManager() {
	}

	public void controlCollisions() {
		controlCollisionsShotsEntity();

	}

	public void controlCollisionsShotsEntity() {
		controlCollisionsShot(MyEntities.ShotSlow);
		controlCollisionsShot(MyEntities.ShotFast);
		controlCollisionsShot(MyEntities.ShotBig);
		controlCollisionsShot(MyEntities.ShotEnemyBasic);
		controlCollisionsShot(MyEntities.ShotEnemyLevel2);
		controlCollisionsShot(MyEntities.ShotEnemyBoss);
	}

	public void controlCollisionsShot(MyEntities s) {
		if (s != MyEntities.ShotBig && s != MyEntities.ShotFast && s != MyEntities.ShotSlow
				&& s != MyEntities.ShotEnemyBasic && s != MyEntities.ShotEnemyLevel2 && s != MyEntities.ShotEnemyBoss)
			return;
		// Regardons si les shots touches quelque chose
		LinkedList<Entity> shots;
		shots = Model.getModel().getEntities(s);
		for (Entity entShot : shots) {
			LinkedList<Entity> hit = new LinkedList<Entity>();
			// verifie si le shot est encore encore en vie
			if (entShot.getCurrentAction() != LsAction.Explode) {
				// regarde si le shot traverse un enemy
				for (int i = entShot.getX(); i < entShot.getX() + entShot.getWidth(); i++) {
					for (int j = entShot.getY(); j < entShot.getY() + entShot.getHeight(); j++) {
						LinkedList<Entity> entities = Model.getModel().getGrid().getEntityCell(i, j);
						for (Entity entity : entities) {
							if (entity != entShot && entity != ((Shot) entShot).getOwner()) {
								if (entity.getCategory() == MyCategory.AT || entity.getCategory() == MyCategory.A
										|| entity.getCategory() == MyCategory.O) {
									if (entShot.GotPower() && entity.GotPower()) {
										hit.add(entity);
									}
								}
							}
						}
					}
				}
			}
			LinkedList<Entity> alreadyHit = new LinkedList<Entity>();
			for (Entity entity : hit) {
				if (!alreadyHit.contains(entity)) {
					entity.collide(entShot.getDamageDealt());
					alreadyHit.add(entity);
					((Shot) entShot).hasKilled(entity);
				}
			}
			if (hit.size() != 0) {
				entShot.collide(0);
			}
		}
	}

}
