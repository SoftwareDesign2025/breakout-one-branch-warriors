package blocks;

import Ball.Ball;
import interfaces.Collidable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Boundary extends Block implements Collidable {
	
	public Boundary(Color color, int xPosition, int yPosition, int width, int height) {
		super(color, xPosition, yPosition, width, height);
	}

	public void handleCollision(Ball ball) {
		/*
		 * Shape intersection = Shape.intersect(ball.getBall(),
		 * boundary.getCollisionBox());
		 * 
		 * if (!intersection.getBoundsInLocal().isEmpty()) { // remove life
		 * 
		 * if(!isShieldActive) { playerController.subtractLife();
		 * lives.setText(playerController.getLives() + " lives"); }
		 * 
		 * 
		 * // reset position ball.setX(paddle.getX() + BLOCK_SIZE);
		 * ball.setY(paddle.getY() - 10); // Time.sleep();
		 * 
		 * isShieldActive = false; boundary.setFill(Color.TRANSPARENT); }
		 */		
	}
}
