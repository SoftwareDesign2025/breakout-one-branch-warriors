package entities.bugs;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Butterfly extends Bug{
	private static final int POINTS = 20;
	private static final int STARTING_DURABILITY = 1;
	private static final Point2D STARTING_VELOCITY = new Point2D(0,0);

	private boolean isMoving = false;
	private Point2D playerLocation;
	private double movementSpeedY = 50;
	private double waveFrequency = 0.25;
	private double amplitude = 100;
	private double startX;
	private double totalTime;
	
	/**
	 * constructor without sprite
	 * @param xPosition
	 * @param yPosition
	 * @param width
	 * @param height
	 */
	public Butterfly(int xPosition, int yPosition, int width, int height) {
		super(xPosition, yPosition, width, height, POINTS, STARTING_DURABILITY, STARTING_VELOCITY);
		rect.setFill(Color.GREEN);
	}

	/**
	 * constructor with sprite
	 * @param xPosition
	 * @param yPosition
	 * @param width
	 * @param height
	 * @param image
	 */
	public Butterfly(int xPosition, int yPosition, int width, int height, String image) {
		super(xPosition, yPosition, width, height, image, POINTS, STARTING_DURABILITY, STARTING_VELOCITY);
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
