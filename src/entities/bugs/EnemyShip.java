//Author: Carter Puckett

package entities.bugs;

import entities.blocks.Paddle;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class EnemyShip extends Bug{
	private static final int POINTS = 50;
	private static final int STARTING_DURABILITY = 1;
	private static final Point2D STARTING_VELOCITY = new Point2D(0,0);
	private static final int HOVER_HEIGHT = 150;

	//PlayerShip playerShip;
	private Paddle paddle; // will be switched with playership
	private boolean isMoving = false;
	private Point2D playerLocation;
	private double movementSpeed = 100;
	private boolean hasReachedHoverHeight = false;

	public EnemyShip(int xPosition, int yPosition, int width, int height, Paddle paddle) {
		super(xPosition, yPosition, width, height, POINTS, STARTING_DURABILITY, STARTING_VELOCITY);
		rect.setFill(Color.BLUE);
		this.paddle = paddle;
	}

	public EnemyShip(int xPosition, int yPosition, int width, int height, String image, Paddle paddle) {
		super(xPosition, yPosition, width, height, image, POINTS, STARTING_DURABILITY, STARTING_VELOCITY);
		this.paddle = paddle;
	}

	//The bee will find the location of the player, store it, and then move to that location until it either
	//hits the player or the bottom of the screen.

	public void move(double elapsedTime) {
		if (isMoving) {
			hasReachedHoverHeight = checkReachedHoverHeight();
			
			if (!hasReachedHoverHeight) {
				moveToHover();
			} else {
				moveToDrop();
			}

			double deltaX = velocity.getX();
			double deltaY = velocity.getY();

			double newX = (view.getLayoutX() + deltaX * elapsedTime);
			double newY = (view.getLayoutY() + deltaY * elapsedTime);

			view.setLayoutX(newX);
			view.setLayoutY(newY);
			
			
		}
	}

	@Override
	public void initializeMovement() {
		if (!isMoving) {
			isMoving = true;
		}
	}
	
	public boolean checkReachedHoverHeight() {	
		return (getY() >= paddle.getY() - HOVER_HEIGHT);
	}

	private void moveToHover() {
		Point2D myPosition = new Point2D(getX(), getY());
		getHoverLocation();
		Point2D direction = playerLocation.subtract(myPosition);

		velocity = direction.normalize().multiply(movementSpeed);
	}
	
	private void moveToDrop() {
		Point2D myPosition = new Point2D(getX(), getY());
		velocity = new Point2D(0, movementSpeed / 2);
	}
	
	private void getHoverLocation() {
		playerLocation = new Point2D(paddle.getX() + paddle.getPaddleWidth() / 2, paddle.getY() - HOVER_HEIGHT);
	}
}
