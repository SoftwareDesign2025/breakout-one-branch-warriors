/**
 * @author Aidan Jimenez & Benji Altman
*/
package Projectiles;

import java.util.Random;

import entities.Entity;
import entities.blocks.Paddle.MoveState;
import interfaces.IMoveable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Ball extends Entity implements IMoveable{

	private Random random = new Random();
	private Point2D velocity;
	private Point2D position;
	private Point2D previousLocation;
	private int ballRadius;
	public boolean inCollision;
		

	private static final double HORIZONTAL_KICK = 0.7;
	private static final double FRICTION_FACTOR = 0.99;
	private static final double WOBBLE_FACTOR = 0.5;
	private static final double BASE_KICK_FACTOR = 1.5;
	private static final double KICK_MULTIPLIER_MAX = 1.3;
	private static final int CORNER_RADIUS = 100;
	private static final String BALL_IMAGE = "resources/breakout_ball_fix.png";

	private static final int RADIUS_DIVIDER = 3;

	private static final int MAX_VELOCITY_Y = 450;
	private static final int MIN_VELOCITY_Y = -MAX_VELOCITY_Y;
	private static final int MAX_VELOCITY_X = 90;
	private static final int MIN_VELOCITY_X = -MAX_VELOCITY_X;

	/**
	 * Constructor for the ball
	 * 
	 * @param startX
	 * @param startY
	 * @param velocity
	 * @param ballRadius
	 * @param color
	 */
	public Ball(int startX, int startY, Point2D velocity, int ballRadius, Color color) {
		super(startX, startY, ballRadius * 2, ballRadius *2);

		this.ballRadius = ballRadius;
		this.velocity = velocity;
		this.rect.setArcHeight(CORNER_RADIUS);
		this.rect.setArcWidth(CORNER_RADIUS);
		setColor(color);

		this.view.setLayoutX(startX);
		this.view.setLayoutY(startY);
	}

	public Ball(int startX, int startY, Point2D velocity, int ballRadius) {
		super(startX, startY, ballRadius * 2, ballRadius * 2, BALL_IMAGE );
		this.velocity = velocity;
		this.ballRadius = ballRadius;
		
		setColor(Color.TRANSPARENT);

		view.setLayoutX(startX);
		view.setLayoutY(startY);
	}

	/**
	 * Updates the ball's position
	 * 
	 * @param elapsedTime
	 */
	public void move(double elapsedTime) {
		double deltaX = velocity.getX();
		double deltaY = velocity.getY();

		previousLocation = new Point2D(view.getLayoutX(), view.getLayoutY());
		double newX = (view.getLayoutX() + (deltaX * FRICTION_FACTOR) * elapsedTime);
		double newY = (view.getLayoutY() + (deltaY * FRICTION_FACTOR) * elapsedTime);

		// Update the circle's position
		view.setLayoutX(newX);
		view.setLayoutY(newY);
	}

	/**
	 * Stops the ball
	 */
	public void stop() {
		velocity = new Point2D(0, 0);
	}

	/**
	 * This method will change the ball velocity and cause it to bounce off the
	 * display wall
	 * 
	 * @param screenWidth
	 * @param screenHeight
	 */
	public void bounceOffWall(double screenWidth, double screenHeight) {
		// Get ball properties
		double x = view.getLayoutX();
		double y = view.getLayoutY();

		// Horizontal (X-axis) collision
		if (x - ballRadius < 0) {
			view.setLayoutX(ballRadius);
			velocity = new Point2D(-velocity.getX(), velocity.getY());
		} else if (x + ballRadius > screenWidth) {
			velocity = new Point2D(-velocity.getX(), velocity.getY());
		}

		// Vertical (Y-axis) collision
		if (y - ballRadius < 0) {
			velocity = new Point2D(velocity.getX(), -velocity.getY());
		} else if (y + ballRadius > screenHeight) {
			velocity = new Point2D(velocity.getX(), -velocity.getY());
		}
		if (y - ballRadius < 0 && x - ballRadius < 0) {
			view.setLayoutX(-ballRadius);
			view.setLayoutY(-ballRadius);
			velocity = new Point2D(velocity.getX(), velocity.getY());
		}
	}

	/**
	 * Bounces the ball off a surface with a modification factor. Ideal for walls or
	 * generic surfaces.
	 * 
	 * @param isReflectingXAxis
	 * @param modification
	 * 
	 */
	public void bounce(boolean isReflectingXAxis, double modification) {
		double currentDeltaX = checkDeltaX();
		double currentDeltaY = checkDeltaY();

		double newDeltaX;
		double newDeltaY;

		if (isReflectingXAxis) {
			// Bounce off a vertical surface
			newDeltaX = currentDeltaX * -modification;
			newDeltaY = addRandomWobble(currentDeltaY);
		} else {
			// Bounce off a horizontal surface
			newDeltaY = currentDeltaY * -modification;
			newDeltaX = addRandomWobble(currentDeltaX);
		}

		// Reposition ball slightly to prevent it from getting stuck in the wall
		resetBallPositionAfterCollision(isReflectingXAxis);
		velocity = new Point2D(newDeltaX, newDeltaY);
	}

	/**
	 * Bounces the ball off a paddle, applying a kick based on the paddle's
	 * movement.
	 * 
	 * @param isReflectingXAxis True if bouncing off a vertical surface.
	 * @param diviewionState    The current movement state of the paddle (LEFT,
	 *                          RIGHT, or NONE).
	 */
	public void bounce(boolean isReflectingXAxis, MoveState diviewionState) {
		double currentDeltaX = checkDeltaX();
		double currentDeltaY = checkDeltaY();

		double newDeltaX;
		double newDeltaY;

		if (isReflectingXAxis) {
			newDeltaX = -currentDeltaX;
			newDeltaY = addRandomWobble(currentDeltaY);
		} else {
			newDeltaY = -currentDeltaY;
			newDeltaX = calculatePaddleKick(currentDeltaX, diviewionState);
		}

		resetBallPositionAfterCollision(isReflectingXAxis);
		velocity = new Point2D(newDeltaX, newDeltaY);
	}

	/**
	 * Calculates the new horizontal speed based on paddle movement.
	 */
	private double calculatePaddleKick(double currentDeltaX, MoveState diviewionState) {
		double kickMultiplier = getRandomDouble(1.0, KICK_MULTIPLIER_MAX);
		double baseKick = HORIZONTAL_KICK * getRandomDouble(1.0, BASE_KICK_FACTOR);

		// Logic for left paddle movement
		if (diviewionState == MoveState.LEFT) {
			if (Math.abs(currentDeltaX) < HORIZONTAL_KICK) {
				return -baseKick;
			} else {
				return -Math.abs(currentDeltaX) * kickMultiplier;
			}
			// Logic for right paddle movement
		} else if (diviewionState == MoveState.RIGHT) {
			if (Math.abs(currentDeltaX) < HORIZONTAL_KICK) {
				return baseKick;
			} else {
				return Math.abs(currentDeltaX) * kickMultiplier;
			}
			// Logic for no paddle movement
		} else {
			if (Math.abs(currentDeltaX) < HORIZONTAL_KICK) {
				// Generating more randomness to allow the ball to either go left of right
				if (Math.random() > WOBBLE_FACTOR) {
					return HORIZONTAL_KICK;
				} else {
					return -HORIZONTAL_KICK;
				}
			} else {
				return currentDeltaX;
			}
		}
	}

	/**
	 * Adds a small wobble to a velocity.
	 * 
	 * @param speedComponent
	 */
	private double addRandomWobble(double speedComponent) {
		double wobble = getRandomDouble(-WOBBLE_FACTOR, WOBBLE_FACTOR);
		return speedComponent + wobble;
	}

	/**
	 * Moves the ball slightly out of the surface it collided with to prevent it
	 * from getting stuck.
	 * 
	 * @param isVerticalCollision
	 */
	private void resetBallPositionAfterCollision(boolean isVerticalCollision) {
		double offset = getRadius() / RADIUS_DIVIDER;
		if (isVerticalCollision) {
			view.setLayoutX(previousLocation.getX() - offset);
			view.setLayoutY(previousLocation.getY());
		} else {
			view.setLayoutX(previousLocation.getX());
			view.setLayoutY(previousLocation.getY() - offset);
		}
	}

	/**
	 * Checks the speed on the Y axis so that it can be limited and not exceed the
	 * max velocity that is set
	 * 
	 * @return double
	 */
	private double checkDeltaY() {
		double deltaY = velocity.getY();
		if (deltaY > MAX_VELOCITY_Y) {
			deltaY = MAX_VELOCITY_Y;
		}
		if (deltaY < MIN_VELOCITY_Y) {
			deltaY = MIN_VELOCITY_Y;
		}
		return deltaY;
	}

	/**
	 * Checks the speed on the X axis so that it can be limited and not exceed the
	 * max velocity that is set
	 * 
	 * @return double
	 */
	private double checkDeltaX() {
		double deltaX = velocity.getX();

		if (deltaX > MAX_VELOCITY_X) {
			deltaX = MAX_VELOCITY_X;
		}

		if (deltaX < MIN_VELOCITY_X) {
			deltaX = MIN_VELOCITY_X;
		}
		return deltaX;
	}

	/**
	 * Returns a random number to create some variety
	 * 
	 * @param rangeStart
	 * @param rangeEnd
	 * 
	 * @return double
	 */
	private double getRandomDouble(double rangeStart, double rangeEnd) {
		return rangeStart + random.nextDouble(rangeEnd - rangeStart) + 1;
	}

	// GETTERS AND SETTERS //

	/**
	 * Getter for the view of the ball object
	 * 
	 * @return Node
	 */
	public Node getView() {
		return view;
	}

	/**
	 * Getter for the Shape object of the ball object
	 * 
	 * @return Node
	 */
	public Shape getBall() {
		return rect;
	}

	/**
	 * Getter for the ballRadius of the circle
	 * 
	 * @return double
	 */
	public double getRadius() {
		return ballRadius;
	}

	/**
	 * Getter for the ball position
	 * 
	 * @return Point2D
	 */
	public Point2D getPosition() {
		return position;
	}

	/**
	 * Setter for the ball color
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		rect.setFill(color);
	}

	/**
	 * Setter for the X position of the ball
	 * 
	 * @param x
	 */
	public void setX(double x) {
		view.setLayoutX(x);
	}

	/**
	 * Setter for the Y position of the ball
	 * 
	 * @param y
	 */
	public void setY(double y) {
		view.setLayoutY(y);
	}
	/*
	 * NEED TO IMPLEMENT
	 */
	public boolean isCollidedWith() {
		return false;
	}
	
}
