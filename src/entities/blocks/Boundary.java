/**
 * @author Aidan Jimenez
 */
package entities.blocks;

import game.gamecontroller.GameController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import projectiles.Ball;
import projectiles.Projectiles;

public class Boundary extends Block {

	public Boundary(Color color, int xPosition, int yPosition, int width, int height) {
		super(color, xPosition, yPosition, width, height);
	}

	@Override
	public void handleCollision(Projectiles ball, GameController gameController) {
		Shape intersection = Shape.intersect(ball.getBall(), getCollisionBox());
		if (!intersection.getBoundsInLocal().isEmpty()) {

			// reset position
			ball.setX(gameController.getPlayerController().getPlayer().getX() + 10);
			ball.setY(gameController.getPlayerController().getPlayer().getY() - 10);

			manageCollision(gameController);
		}
	}

	@Override
	public void manageCollision(GameController gameController) {
		gameController.handleShield();
	}
}
