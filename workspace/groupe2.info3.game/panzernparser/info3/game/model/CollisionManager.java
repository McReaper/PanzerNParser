package info3.game.model;

import java.util.LinkedList;

import info3.game.model.entities.Entity;
import info3.game.model.entities.EntityFactory.MyEntities;
import info3.game.model.entities.Shot;

public class CollisionManager {
	
	public CollisionManager() {
		System.out.println("Création de la classe CollisionManager");
		}
	
	public void controlCollisionsShotsEntity() {
		//Regardons si les shots touches qqc
		LinkedList<Entity> shots;
		LinkedList<Entity> enemy;
		LinkedList<Entity> tankPlayer;
		
		shots = Model.getModel().getEntities(MyEntities.Shot);
		enemy = Model.getModel().getEntities(MyEntities.Enemy);
		tankPlayer = Model.getModel().getEntities(MyEntities.TankBody);
		for (Entity entShot : shots) {
			//regarde si le shot ne traverse pas un enemy
			for (Entity entEnemy : enemy) {
				/*
				 * si c'est l'entité qui l'a tiré il ne peut pas collider avec
				 * pour éviter les problèmes d'explosion alors qu'il est meme pas sorti du canon
				 */
				if (!entEnemy.equals(((Shot)entShot).getOwner())) {
					
					if (entEnemy.isInMeCase(entShot.getX(), entShot.getY())){
						entEnemy.collide();
						entShot.collide();	
//						System.out.println("x = " +entShot.getX()+" y = "+ entShot.getY() +" widht = " + entShot.getWidth() );
//						System.out.println("je rencontre un enemy");
					}
				}
				
			}
			
			for (Entity entPlayer : tankPlayer) {
				/*
				 * si c'est l'entité qui l'a tiré il ne peut pas collider avec
				 * pour éviter les problèmes d'explosion alors qu'il est meme pas sorti du canon
				 */
				if (!entPlayer.equals(((Shot)entShot).getOwner())) {
					
					if (entPlayer.isInMeCase(entShot.getX(), entShot.getY())){
						entPlayer.collide();
						entShot.collide();	
//						System.out.println("x = " +entShot.getX()+" y = "+ entShot.getY() +" widht = " + entShot.getWidth() );
//						System.out.println("je rencontre un enemy");
					}
				}
				
			}
		}
	}
}
