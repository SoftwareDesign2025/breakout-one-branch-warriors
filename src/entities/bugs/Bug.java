//Author: Carter Puckett

package entities.bugs;

import projectiles.Ball;
import entities.Entity;
import game.GameController;
import interfaces.Collidable;
import interfaces.IMoveable;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

public abstract class Bug extends Entity implements Collidable, IMoveable{
	
	protected int points;
	protected int durability;
	protected Point2D velocity;
	protected Ball ball;
	
	/**
	 * constructor for bug without sprite
	 * @param xPosition
	 * @param yPosition
	 * @param width
	 * @param height
	 * @param points
	 * @param durability
	 * @param velocity
	 */
	public Bug(int xPosition, int yPosition, int width, int height, int points, int durability, Point2D velocity) {
		super(xPosition, yPosition, width, height);
		this.points = points;
		this.durability = durability;
		this.velocity = velocity;
	}
	
	/**
	 * constructor for bug with sprite
	 * @param xPosition
	 * @param yPosition
	 * @param width
	 * @param height
	 * @param image
	 * @param points
	 * @param durability
	 * @param velocity
	 */
	public Bug(int xPosition, int yPosition, int width, int height, String image, int points, int durability, Point2D velocity) {
		super(xPosition, yPosition, width, height, image);
		this.points = points;
		this.durability = durability;
		this.velocity = velocity;
	}
	
	/**
	 * handles collision with projectile
	 */
	public void handleCollision(Ball ball, GameController gameController) {
		if (checkCollision(ball)) {
			manageCollision(gameController);
		}
	}
	
	/**
	 * controls what happens on collision
	 */
	public void manageCollision(GameController gameController) {
		gameController.getPlayerController().addBrickValueToScore(getPoints());
		durability--;
	}
	
	/**
	 * checks for collision with projectile
	 */
	public boolean checkCollision(Ball ball) {
		Shape intersection = Shape.intersect(ball.getBall(), getCollisionBox());

		return intersection.getBoundsInLocal().isEmpty();
	}
	
	/**
	 * gets point value of bug
	 * @return
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * gets durability of bug
	 * @return
	 */
	public int getDurability() {
		return durability;
	}
	
	public abstract void move(double elapsedTime);
	
	public abstract void initializeMovement();
}
