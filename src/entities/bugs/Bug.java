//Author: Carter Puckett

package entities.bugs;

import Projectiles.Ball;
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
	
	public Bug(int xPosition, int yPosition, int width, int height, int points, int durability, Point2D velocity) {
		super(xPosition, yPosition, width, height);
		this.points = points;
		this.durability = durability;
		this.velocity = velocity;
	}
	
	public Bug(int xPosition, int yPosition, int width, int height, String image, int points, int durability, Point2D velocity) {
		super(xPosition, yPosition, width, height, image);
		this.points = points;
		this.durability = durability;
		this.velocity = velocity;
	}
	
	@Override
	public void handleCollision(Ball ball, GameController gameController) {
		Shape intersection = Shape.intersect(ball.getBall(), getCollisionBox());
		
		if (!intersection.getBoundsInLocal().isEmpty()) {
			durability--;
			manageCollision(gameController);
		}
	}
	
	public void manageCollision(GameController gameController) {
		
	}
	
	public int getPoints() {
		return points;
	}
	
	public int getDurability() {
		return durability;
	}
	
	@Override
	public abstract void move(double elapsedTime);
	
	public abstract void initializeMovement();
}
