package entities.bugs;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Butterfly extends Bug{
	private static final int POINTS = 10;
	private static final int STARTING_DURABILITY = 3;
	private static final Point2D STARTING_VELOCITY = new Point2D(0,0);

	boolean isMoving = false;
	Point2D playerLocation;
	double movementSpeedY = 50;
	double waveFrequency = 0.25;
	double amplitude = 100;
	double startX;
	double totalTime;
	
	public Butterfly(int xPosition, int yPosition, int width, int height) {
		super(xPosition, yPosition, width, height, POINTS, STARTING_DURABILITY, STARTING_VELOCITY);
		rect.setFill(Color.GREEN);
	}

	public Butterfly(int xPosition, int yPosition, int width, int height, String image) {
		super(xPosition, yPosition, width, height, image, POINTS, STARTING_DURABILITY, STARTING_VELOCITY);
	}

	//The butterfly floats downward in a sine wave (back and forth) pattern.

	public void move(double elapsedTime) {
		if (isMoving) {
			totalTime += elapsedTime;

			double newX = startX + Math.sin(totalTime * waveFrequency * Math.PI * 2) * amplitude;;
			double newY = getY() + elapsedTime * movementSpeedY;

			view.setLayoutX(newX);
			view.setLayoutY(newY);
		}
	}

	public void initializeMovement() {
		if (!isMoving) {
			isMoving = true;
			startX = getX();
			totalTime = 0;
		}
	}

	//	private void storePlayerLocation() {
	//		playerLocation = new Point2D(playerShip.getX, playerShip.getY);
	//	}
}
