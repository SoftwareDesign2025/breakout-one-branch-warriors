//Author: Benji Altmann

package entities.blocks;

import game.gamecontroller.GameController;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import projectiles.Ball;
import projectiles.Projectiles;

public class PlayerShip extends Player {
	
	private static final String IMAGE_LOCATION = "resources/Ship.png";
	
	/**
	 * Paddle constructor using a color fill
	 * 
	 * @param color
	 * @param xPosition
	 * @param yPosition
	 * @param width
	 * @param height
	 * @param boardWidth
	 */
	public PlayerShip(Color color, int xPosition, int yPosition, int width, int height, int boardWidth) {
		super(color, xPosition, yPosition, width, height, boardWidth);
	}

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
		this.playerWidth = width;
		this.velocity = new Point2D(0, 0);
		state = MoveState.STOPPED;
	}
	
	
	public void handleCollision(Projectiles projectiles, GameController gameController) {
		Shape intersection = Shape.intersect(projectiles.getBall(), getCollisionBox());
		if (!intersection.getBoundsInLocal().isEmpty()) {
			gameController.getPlayerController().subtractLife();
		}
	}

	@Override
	public void manageCollision(GameController gameController) {
		// TODO Auto-generated method stub
		// could be used to play audio
		
	}

}
