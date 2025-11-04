package entities.blocks;

import game.gamecontroller.GameController;
import javafx.geometry.Point2D;
import projectiles.Projectiles;

public class PlayerShip extends PlayerBlock{

	protected static final String IMAGE_LOCATION = "resources/Ship.png";

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

	@Override
	public void handleCollision(Projectiles ball, GameController gameController) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void manageCollision(GameController gameController) {
		// TODO Auto-generated method stub
		
	}

}
