//Author: Carter Puckett, Aidan Jimenez

package entities.bugs;

import entities.blocks.Player;
import game.gamecontroller.GameController;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import projectiles.Projectiles;

public class Bee extends Bug{

	private static final int POINTS = 10;
	private static final int STARTING_DURABILITY = 1;
	private static final Point2D STARTING_VELOCITY = new Point2D(0,0);
	private static final String IMAGE = "resources/Bee7.png";

	//private PlayerShip playerShip;
	private boolean isMoving = false;
	private Point2D playerLocation;
	private double movementSpeed = 90;


	/**
	 * constructor with sprite
	 * @param xPosition
	 * @param yPosition
	 * @param width
	 * @param height
	 * @param image
	 */
	public Bee(int xPosition, int yPosition, int size, Player playerShip) {
		super(xPosition, yPosition, size, size, IMAGE, POINTS, STARTING_DURABILITY, STARTING_VELOCITY);
		this.playerShip = playerShip;
	}

	//The bee will find the location of the player, store it, and then move to that location until it either
	//hits the player or the bottom of the screen.

	/**
	 * moves the bee 
	 */
	public void move(double elapsedTime) {
		if (isMoving) {

			double deltaX = velocity.getX();
			double deltaY = velocity.getY();

			double newX = (view.getLayoutX() + deltaX * elapsedTime);
			double newY = (view.getLayoutY() + deltaY * elapsedTime);

			view.setLayoutX(newX);
			view.setLayoutY(newY);

			if(getY() > 600) {
				setY(-50);
			}
			if(getX() > 900) {
				setX(-50);
			}
			if(getX() < 0) {
				setX(950);
			}
		}
	}

	/**
	 * stores the location of the player and starts movement
	 */
	public void initializeMovement() {
		if (!isMoving) {
			isMoving = true;
			storePlayerLocation();
			setVelocity();
		}
	}

	/**
	 * sets the velocity of the bee based on the stored player location
	 */
	private void setVelocity() {
		Point2D myPosition = new Point2D(getX(), getY());
		Point2D direction = playerLocation.subtract(myPosition);

		velocity = direction.normalize().multiply(movementSpeed);
	}
	
	/**
	 * stores the current location of the player
	 */
	private void storePlayerLocation() {
		playerLocation =  new Point2D(playerShip.getX(), playerShip.getY());
	}

}
