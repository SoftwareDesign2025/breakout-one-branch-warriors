/**
 * @author Aidan Jimenez
 */
package entities.blocks;

import Ball.Ball;
import Testing.GameController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Boundary extends Block {

	public Boundary(Color color, int xPosition, int yPosition, int width, int height) {
		super(color, xPosition, yPosition, width, height);
	}

	@Override
	public void handleCollision(Ball ball, GameController gameController) {
		Shape intersection = Shape.intersect(ball.getBall(), getCollisionBox());
		if (!intersection.getBoundsInLocal().isEmpty()) {

			if(!gameController.isShieldActive()) {
				gameController.getPlayerController().subtractLife();
				//lives.setText(playerController.getLives() + " lives");  		Update number of lives text
			}
			// reset position
			ball.setX(gameController.getPaddle().getX() + 10);
			ball.setY(gameController.getPaddle().getY() - 10);

			gameController.removeShield();
			//boundary.setFill(Color.TRANSPARENT);		 Change color of boundary after shield is "used"
		}
	}
}
