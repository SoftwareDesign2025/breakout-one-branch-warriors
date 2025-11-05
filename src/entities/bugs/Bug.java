//Author: Carter Puckett

package entities.bugs;

import projectiles.Ball;
import projectiles.Projectiles;
import entities.Entity;
import entities.blocks.PlayerShip;
import game.gamecontroller.GameController;
import interfaces.Collidable;
import interfaces.IMoveable;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class Bug extends Entity implements  IMoveable, Collidable{
	
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
	public Bug(int xPosition, int yPosition,int size, int points, int durability, Point2D velocity) {
		super(xPosition, yPosition, size, size);
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
		this.rect.setFill(Color.BLACK);
	}
	
	/**
	 * handles collision with projectile
	 */
	@Override
	public void handleCollision(Projectiles projectile, GameController gameController) {
		if (checkCollision(projectile)) {
			manageCollision(gameController);
		}
	}
	
	/**
	 * controls what happens on collision
	 */
	@Override
	public void manageCollision(GameController gameController) {
		gameController.getPlayerController().addBrickValueToScore(getPoints());
		gameController.breakItem(this);
		durability--;
	}
	
	/**
	 * checks for collision with projectile
	 */
	@Override
	public boolean checkCollision(Projectiles projectile) {
		Shape intersection = Shape.intersect(projectile.getBall(), getCollisionBox());

		return !intersection.getBoundsInLocal().isEmpty();
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
