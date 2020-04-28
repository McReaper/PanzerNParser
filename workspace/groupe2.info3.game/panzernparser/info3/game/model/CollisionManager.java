package info3.game.model;

import java.util.LinkedList;

import info3.game.model.entities.Entity;
import info3.game.model.entities.EntityFactory.MyEntities;

public class CollisionManager {
	
	public CollisionManager() {
		System.out.println("Création de la classe CollisionManager");
		}
	
	public void controlCollisionsShotsEntity() {
		//Regardons si les shots touches qqc
		LinkedList<Entity> shots;
		LinkedList<Entity> enemy;
		shots = Model.getModel().getEntities(MyEntities.Shot);
		enemy = Model.getModel().getEntities(MyEntities.Enemy);
		for (Entity entShot : shots) {
			for (Entity entEnemy : enemy) {
				if (entEnemy.isInMeCase(entShot.getX(), entShot.getY())){
					entEnemy.collide();
					entShot.collide();	
					System.out.println("x = " +entShot.getX()+" y = "+ entShot.getY() +" widht = " + entShot.getWidth() );
					System.out.println("je rencontre un enemy");
				}
			}
		}
	}
}
