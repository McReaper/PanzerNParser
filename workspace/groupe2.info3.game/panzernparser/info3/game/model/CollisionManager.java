package info3.game.model;

import java.util.LinkedList;

import info3.game.automaton.MyCategory;
import info3.game.automaton.action.LsAction;
import info3.game.model.entities.Entity;
import info3.game.model.entities.EntityFactory.MyEntities;
import info3.game.model.entities.MovingEntity;
import info3.game.model.entities.Shot;
import info3.game.model.entities.TankBody;
import info3.game.model.entities.Turret;

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
	}

	public void controlCollisionsShot(MyEntities s) {
		if (s != MyEntities.ShotBig && s != MyEntities.ShotFast && s != MyEntities.ShotSlow)
			return;
		// Regardons si les shots touches quelque chose
		LinkedList<Entity> shots;
		shots = Model.getModel().getEntities(s);
		for (Entity entShot : shots) {

			// verifie si le shot est encore encore en vie
			if (entShot.getCurrentAction() != LsAction.Explode) {
				// regarde si le shot traverse un enemy
				for (int i = entShot.getX(); i < entShot.getX() + entShot.getWidth(); i++) {
					for (int j = entShot.getY(); j < entShot.getY() + entShot.getHeight(); j++) {
						LinkedList<Entity> entities = Model.getModel().getGrid().getEntityCell(i, j);
						for (Entity entity : entities) {
							if (entity != entShot && entity != ((Shot) entShot).getOwner()) {
								if (!(((Shot) entShot).getOwner() instanceof TankBody) || !(entity instanceof Turret)) {
									if (entity.getCategory() == MyCategory.AT || entity.getCategory() == MyCategory.A
											|| entity.getCategory() == MyCategory.O) {
										entShot.collide(entity.getDamageDealt());
										entity.collide(entShot.getDamageDealt());
									}
								}
							}
						}
					}
				}
			}
		}
	}

}
