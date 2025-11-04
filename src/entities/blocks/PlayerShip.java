package entities.blocks;

import javafx.geometry.Point2D;

public class PlayerShip extends Paddle {

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
		//TODO: refactor the way that the paddle is made
		super(xPosition, yPosition, width, height, boardWidth);
	}

}
