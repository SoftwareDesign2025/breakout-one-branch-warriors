/**
 * @author Aidan Jimenez
 */
package blocks;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Paddle extends Block {
	private Point2D velocity;
	private static int MOVE_SPEED = 15;
	private static final double MAX_VELOCITY = 45;
	private static final double MIN_VELOCITY = -MAX_VELOCITY;
	private boolean moved = false;
	private int boardWidth;
	private int paddleWidth;

	public enum MoveState {
		STOPPED, LEFT, RIGHT
	}

	MoveState state;

	/**
	 * Paddle constructor using a color fill
	 * 
	 * @param color
	 * @param xPosition
	 * @param yPosition
	 * @param width
	 * @param height
	 * @param boardWidth
	 */
	public Paddle(Color color, int xPosition, int yPosition, int width, int height, int boardWidth) {
		super(color, xPosition, yPosition, width, height);
		this.boardWidth = boardWidth;
		this.paddleWidth = width;
		this.velocity = new Point2D(0, 0);
		state = MoveState.STOPPED;
	}

	/**
	 * Paddle constructor using an image fill
	 * 
	 * @param xPosition
	 * @param yPosition
	 * @param width
	 * @param height
	 * @param boardWidth
	 * @param image
	 */
	public Paddle(int xPosition, int yPosition, int width, int height, int boardWidth, Image image) {
		super(xPosition, yPosition, width, height, image);
		this.boardWidth = boardWidth;
		this.paddleWidth = width;
		this.velocity = new Point2D(0, 0);
		state = MoveState.STOPPED;
	}

	/**
	 * Provides paddle movement which the user can use to control
	 * 
	 * @param goRight
	 */
	public void moveHorizontally(boolean goRight) {
		if (!moved) {
			moved = true;
		}

		double currentVelocityX = velocity.getX();

		if (goRight && this.getView().getLayoutX() + paddleWidth < boardWidth) {
			double deltaX = checkVelocity();
			velocity = new Point2D(currentVelocityX * deltaX, 0);

			this.setX(this.getX() + deltaX);
			state = MoveState.RIGHT;

		} else if (!goRight && this.getView().getLayoutX() > 0) {
			double deltaX = checkVelocity();
			velocity = new Point2D(currentVelocityX * -deltaX, 0);

			this.setX(this.getX() - deltaX);
			state = MoveState.LEFT;
		}
	}

	/**
	 * Limits the velocity of the paddle so it can be at a controllable speed
	 * 
	 * @return double
	 */
	private double checkVelocity() {
		double deltaX = velocity.getX();

		if (deltaX == 0) {
			return MOVE_SPEED;
		}
		if (deltaX > MAX_VELOCITY) {
			deltaX = MAX_VELOCITY;
		}
		if (deltaX < MIN_VELOCITY) {
			deltaX = MIN_VELOCITY;
		}
		return deltaX;
	}

	/**
	 * Returns the state that the paddle is in
	 * 
	 * @return MoveState
	 */
	public MoveState getState() {
		return state;
	}

	/**
	 * Returns if the paddle has been moved
	 * 
	 * @return MoveState
	 */
	public boolean hasBeenMoved() {
		return moved;
	}

	/*
	 * Stops the paddle from moving
	 */
	public void stop() {
		this.velocity = new Point2D(0, 0);
		state = MoveState.STOPPED;
	}

}
