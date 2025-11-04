/**
 * @author Aidan Jimenez
 */
package entities.blocks;

import game.gamecontroller.GameController;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import projectiles.Projectiles;

public class Paddle extends PlayerBlock {
	protected static final String IMAGE_LOCATION = "resources/blue_paddle.png";
	
	
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
	public Paddle(Color color, int xPosition, int yPosition, int width, int height, int boardWidth) {
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
	 */
	public Paddle(int xPosition, int yPosition, int width, int height, int boardWidth) {
		super(xPosition, yPosition, width, height, boardWidth, IMAGE_LOCATION);
	}

	public void handleCollision(Projectiles projectile, GameController gameController) {
		Shape intersection = Shape.intersect(projectile.getBall(), getCollisionBox());
		if (!intersection.getBoundsInLocal().isEmpty()) {

			double intersectionWidth = intersection.getBoundsInLocal().getWidth();
			double intersectionHeight = intersection.getBoundsInLocal().getHeight();

			if (intersectionWidth > intersectionHeight) {
				projectile.bounce(false, getState()); // isReflectingXAxis is false
			} else {
				projectile.bounce(true, getState()); // isReflectingXAxis is true
			}
		}
	}

	public int getPaddleWidth() {
		return paddleWidth;
	}

	@Override
	public void manageCollision(GameController gameController) {
		// TODO Auto-generated method stub

	}

}
