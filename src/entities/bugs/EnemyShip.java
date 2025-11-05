//Author: Carter Puckett

package entities.bugs;

import entities.blocks.Player;
import game.gamecontroller.GameController;
import javafx.geometry.Point2D;
import projectiles.Projectiles;

public class EnemyShip extends Bug{
	private static final int POINTS = 50;
	private static final int STARTING_DURABILITY = 1;
	private static final Point2D STARTING_VELOCITY = new Point2D(0,0);
	private static final int HOVER_HEIGHT = 150;
	private static final String IMAGE = "resources/Green7.png";

	//private PlayerShip playerShip;
	private boolean isMoving = false;
	private Point2D playerLocation;
	private double movementSpeed = 100;
	private boolean hasReachedHoverHeight = false;
	private Player playerShip;

	/**
	 * constructor without sprite
	 * @param xPosition
	 * @param yPosition
	 * @param width
	 * @param height
	 * @param paddle
	 */
	public EnemyShip(int xPosition, int yPosition, int size, Player playerShip) {
		super(xPosition, yPosition, size, size, IMAGE, POINTS, STARTING_DURABILITY, STARTING_VELOCITY);
		this.playerShip = playerShip;
	}

	//The enemy ship will move to a designated point above the player. Once it reaches the height of that point,
	//it switches to a drop state where it moves at a constant speed downwards, now ignoring the location of the player

	/**
	 * moves the enemy ship
	 */
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

	/**
	 * enemy ship starts moving
	 */
	public void initializeMovement() {
		if (!isMoving) {
			isMoving = true;
		}
	}
	
	/**
	 * returns true if the enemy ship has reached the hover height
	 * @return
	 */
	public boolean checkReachedHoverHeight() {	
		return (getY() >= playerShip.getY() - HOVER_HEIGHT);
	}

	/**
	 * moves enemy ship towards hover location
	 */
	private void moveToHover() {
		Point2D myPosition = new Point2D(getX(), getY());
		getHoverLocation();
		Point2D direction = playerLocation.subtract(myPosition);

		velocity = direction.normalize().multiply(movementSpeed);
	}
	
	/**
	 * moves at a constant speed downwards
	 */
	private void moveToDrop() {
		Point2D myPosition = new Point2D(getX(), getY());
		velocity = new Point2D(0, movementSpeed / 2);
	}
	
	/**
	 * gets the location above the player that the enemy ship will hover at until reaching the hover height
	 */
	private void getHoverLocation() {
		playerLocation = new Point2D(playerShip.getX() + 25 , playerShip.getY() - HOVER_HEIGHT);
	}

	@Override
	public void handleCollision(Projectiles projectile, GameController gameController) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void manageCollision(GameController gameController) {
		// TODO Auto-generated method stub
		
	}
}
