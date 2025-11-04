//Author: Benji Altmann

package entities.blocks;

import entities.blocks.Player.MoveState;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public abstract class Player extends Block {
	protected Point2D velocity;
	protected static int MOVE_SPEED = 600;
	protected static final double MAX_VELOCITY = 950;
	protected static final double MIN_VELOCITY = -MAX_VELOCITY;
	protected boolean moved = false;
	protected int screenWidth;
	protected int playerWidth;
	
	public enum MoveState {
		STOPPED, LEFT, RIGHT
	}

	MoveState state;
	
	public Player(Color color, int xPosition, int yPosition, int width, int height, int boardWidth) {
		super(color, xPosition, yPosition, width, height);
		this.screenWidth = boardWidth;
		this.playerWidth = width;
		this.velocity = new Point2D(0, 0);
		state = MoveState.STOPPED;
		this.rect.setArcHeight(CORNER_RADIUS);
		this.rect.setArcWidth(CORNER_RADIUS);
	}
	
	public Player(int xPosition, int yPosition, int width, int height, int screenWidth, String imageLocation) {
		super(xPosition,yPosition, width, height, imageLocation);
		this.screenWidth = screenWidth;
		this.playerWidth = width;
		this.velocity = new Point2D(0, 0);
		state = MoveState.STOPPED;
	}
	
	/**
	 * Provides paddle movement which the user can use to control
	 * 
	 * @param goRight
	 * @param elapsedTime 
	 */
	public void moveHorizontally(boolean goRight, double elapsedTime) {
		if (!moved) {
			moved = true;
		}

		double currentVelocityX = velocity.getX();

		if (goRight && this.getView().getLayoutX() + playerWidth < screenWidth) {
			double deltaX = checkVelocity();
			velocity = new Point2D(currentVelocityX * deltaX * elapsedTime, 0);

			this.setX(this.getX() + deltaX * elapsedTime);
			state = MoveState.RIGHT;

		} else if (!goRight && this.getView().getLayoutX() > 0) {
			double deltaX = checkVelocity();
			velocity = new Point2D(currentVelocityX * -deltaX * elapsedTime, 0);

			this.setX(this.getX() - deltaX * elapsedTime);
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
		state = MoveState.STOPPED;
	}
	
	public int getPlayerWidth() {
		return playerWidth;
	}
	

}
