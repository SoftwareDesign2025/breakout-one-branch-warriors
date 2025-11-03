//Author: Carter Puckett

package entities.bugs;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Bee extends Bug{

	private static final int POINTS = 10;
	private static final int STARTING_DURABILITY = 1;
	private static final Point2D STARTING_VELOCITY = new Point2D(0,0);

	//PlayerShip playerShip;
	boolean isMoving = false;
	Point2D playerLocation;
	double movementSpeed = 90;

	public Bee(int xPosition, int yPosition, int width, int height) {
		super(xPosition, yPosition, width, height, POINTS, STARTING_DURABILITY, STARTING_VELOCITY);
		rect.setFill(Color.YELLOW);
	}

	public Bee(int xPosition, int yPosition, int width, int height, String image) {
		super(xPosition, yPosition, width, height, image, POINTS, STARTING_DURABILITY, STARTING_VELOCITY);
	}

	//The bee will find the location of the player, store it, and then move to that location until it either
	//hits the player or the bottom of the screen.

	public void move(double elapsedTime) {
		if (isMoving) {

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
			playerLocation = new Point2D(200,600);
			setVelocity();
		}
	}
	
	private void setVelocity() {
		Point2D myPosition = new Point2D(getX(), getY());
		Point2D direction = playerLocation.subtract(myPosition);
		
		velocity = direction.normalize().multiply(movementSpeed);
	}

	//	private void storePlayerLocation() {
	//		playerLocation = new Point2D(playerShip.getX, playerShip.getY);
	//	}
}
