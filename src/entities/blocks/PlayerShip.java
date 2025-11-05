package entities.blocks;

import java.util.ArrayList;
import java.util.List;

import entities.bugs.Bug;
import game.gamecontroller.GameController;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;
import projectiles.Projectiles;

public class PlayerShip extends PlayerBlock{

	protected static final String IMAGE_LOCATION = "resources/Ship.png";
	private Bug prevBug = null;
	/**
	 * Paddle constructor using an image fill
	 * 
	 * @param xPosition
	 * @param yPosition
	 * @param width
	 * @param height
	 * @param boardWidth
	 * @param image
	 */
	public PlayerShip(int xPosition, int yPosition, int width, int height, int boardWidth) {
		super(xPosition, yPosition, width, height, boardWidth, IMAGE_LOCATION);
	}

	//@Override
	public void handleCollision(Bug bug, GameController gameController) {
		if (checkCollisionBug(bug)) {
			System.out.println("Bug intersected");
			manageCollision(gameController);
		}
	}

	@Override
	public void manageCollision(GameController gameController) {
			
			//gameController.getPlayerController().subtractLife();
			
			
	}
	
	public boolean checkCollisionBug(Bug bug) {
		Shape intersection = Shape.intersect(bug.getCollisionBox(), getCollisionBox());
		return !intersection.getBoundsInLocal().isEmpty();
	}

	@Override
	public void handleCollision(Projectiles ball, GameController gameController) {
		// TODO Auto-generated method stub
		
	}

	
}
