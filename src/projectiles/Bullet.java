package projectiles;

import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;


import entities.Entity;
import entities.blocks.Paddle.MoveState;
import interfaces.IMoveable;

import javafx.scene.Node;

import javafx.scene.shape.Shape;

public class Bullet extends Entity implements IMoveable {
	private Random random = new Random();
	private Point2D velocity;
	private Point2D position;
	private Point2D previousLocation;
	private int ballRadius;
	public boolean inCollision;
		

	//private static final double HORIZONTAL_KICK = 0.7;
	private static final double FRICTION_FACTOR = 0.99;
	//private static final double WOBBLE_FACTOR = 0.5;
	//private static final double BASE_KICK_FACTOR = 1.5;
	//private static final double KICK_MULTIPLIER_MAX = 1.3;
	private static final int CORNER_RADIUS = 100;
	private static final String BALL_IMAGE = "resources/breakout_ball_fix.png";

	private static final int RADIUS_DIVIDER = 3;

	private static final int MAX_VELOCITY_Y = 450;
	private static final int MIN_VELOCITY_Y = -MAX_VELOCITY_Y;
	private static final int MAX_VELOCITY_X = 90;
	private static final int MIN_VELOCITY_X = -MAX_VELOCITY_X;
	private double startPosition;

	public Bullet(int startX, int startY, Point2D velocity, int ballRadius, Color color) {
		super(startX, startY, ballRadius * 2, ballRadius *2);

		this.ballRadius = ballRadius;
		this.velocity = velocity;
		this.rect.setArcHeight(CORNER_RADIUS);
		this.rect.setArcWidth(CORNER_RADIUS);
		setColor(color);

		this.view.setLayoutX(startX);
		this.view.setLayoutY(startY);
		startPosition = position.getY();
	}

	public Bullet(int startX, int startY, Point2D velocity, int ballRadius) {
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
		//double deltaX = velocity.getX();
		double deltaY = velocity.getY();
		

		previousLocation = new Point2D(view.getLayoutX(), view.getLayoutY());
		//double newX = (view.getLayoutX() + (deltaX * FRICTION_FACTOR) * elapsedTime);
		double newY = (view.getLayoutY() + deltaY * elapsedTime);

		// Update the circle's position
		//view.setLayoutX(newX);
		view.setLayoutY(newY);
	}
	
	public boolean step() {
		return view.getLayoutY() < 100;
	}

	/**
	 * Stops the ball
	 */
	public void stop() {
		velocity = new Point2D(0, 0);
	}
//	private double checkDeltaY() {
//		double deltaY = velocity.getY();
//		if (deltaY > MAX_VELOCITY_Y) {
//			deltaY = MAX_VELOCITY_Y;
//		}
//		if (deltaY < MIN_VELOCITY_Y) {
//			deltaY = MIN_VELOCITY_Y;
//		}
//		return deltaY;
//	}
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
//		public void setX(double x) {
//			view.setLayoutX(x);
//		}

		/**
		 * Setter for the Y position of the ball
		 * 
		 * @param y
		 */
		public void setY(double y) {
			view.setLayoutY(y);
		}


}
