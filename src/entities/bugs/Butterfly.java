/**
 * @author Carter Puckett
 * @author Aidan Jimenez
 */
package entities.bugs;

import game.gamecontroller.GameController;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import projectiles.Projectiles;

public class Butterfly extends Bug{
	private static final int POINTS = 20;
	private static final int STARTING_DURABILITY = 1;
	private static final Point2D STARTING_VELOCITY = new Point2D(0,0);
	private static final String IMAGE = "resources/Bug7.png";

	private boolean isMoving = false;
	private Point2D playerLocation;
	private double movementSpeedY = 50;
	private double waveFrequency = 0.25;
	private double amplitude = 100;
	private double startX;
	private double totalTime;

	/**
	 * constructor with sprite
	 * @param xPosition
	 * @param yPosition
	 * @param width
	 * @param height
	 * @param image
	 */
	public Butterfly(int xPosition, int yPosition, int size) {
		super(xPosition, yPosition, size, size, IMAGE, POINTS, STARTING_DURABILITY, STARTING_VELOCITY);
	}

	//The butterfly floats downward in a sine wave (back and forth) pattern.

	/**
	 * moves the butterfly
	 */
	public void move(double elapsedTime) {
		if (isMoving) {
			totalTime += elapsedTime;

			double newX = startX + Math.sin(totalTime * waveFrequency * Math.PI * 2) * amplitude;;
			double newY = getY() + elapsedTime * movementSpeedY;

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
	 * butterfly begins moving
	 */
	public void initializeMovement() {
		if (!isMoving) {
			isMoving = true;
			startX = getX();
			totalTime = 0;
		}
	}

}
