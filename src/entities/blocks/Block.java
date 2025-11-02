/**
 * @author Aidan Jimenez
 */
package entities.blocks;

import game.GameController;
import Projectiles.Ball;
import entities.Entity;
import interfaces.Collidable;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public abstract class Block extends Entity implements Collidable{
	protected ImageView sprite;  

	// Can be used to make curved corners
	protected static final int CORNER_RADIUS = 10;

	/**
	 * Block constructor creating a basic block
	 * @param xPostition
	 * @param yPosition
	 * @param width
	 * @param height 
	 */
	public Block(int xPosition, int yPosition, int width, int height) {
		super(xPosition,yPosition, width, height);
	}

	/**
	 * Block constructor creating a block with a color fill
	 * @param Color
	 * @param xPostition
	 * @param yPosition
	 * @param width
	 * @param height 
	 */
	public Block(Color color, int xPosition, int yPosition, int width, int height) {
		super(xPosition,yPosition, width, height);
		rect.setFill(color);
	}

	/**
	 * Block constructor creating a block with an image an collision box 
	 * @param xPostition
	 * @param yPosition
	 * @param width
	 * @param height 
	 * @param image 
	 */
	public Block(int xPosition, int yPosition, int width, int height, String image) {
		super(xPosition,yPosition, width, height, image);
		rect.setArcHeight(CORNER_RADIUS);
		rect.setArcWidth(CORNER_RADIUS);
		rect.setFill(Color.TRANSPARENT);
	}

	/**
	 * Sets the fill color of the block with a black stroke
	 * @param color
	 */
	public void setFill(Paint color) {
		rect.setFill(color);
		//rect.setStroke(Color.BLACK);
	}

	public abstract void handleCollision(Ball ball, GameController gameController); 

	public abstract void manageCollision(GameController gameController); 
		

	@Override
	public boolean checkCollision(Ball ball) {
		Shape intersection = Shape.intersect(ball.getBall(), getCollisionBox());

		return intersection.getBoundsInLocal().isEmpty();
	}
}
