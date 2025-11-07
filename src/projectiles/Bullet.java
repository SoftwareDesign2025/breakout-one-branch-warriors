package projectiles;

import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import entities.Entity;
import entities.blocks.Player.MoveState;
import interfaces.IMoveable;

import javafx.scene.Node;

import javafx.scene.shape.Shape;

public class Bullet extends Projectiles implements IMoveable {

	private static final int CORNER_RADIUS = 100;
	private static final String BALL_IMAGE = "resources/Boolet3.png";

	private static final int RADIUS = 40;

	private static final Point2D STARTING_VELOCITY = new Point2D(0, 250);

	public Bullet(int startX, int startY, Point2D velocity, int ballRadius, Color color) {
		super(startX, startY, ballRadius * 2, ballRadius * 2);

		this.projectileRadius = ballRadius;
		this.velocity = velocity;
		this.rect.setArcHeight(CORNER_RADIUS);
		this.rect.setArcWidth(CORNER_RADIUS);
		setColor(color);

		this.view.setLayoutX(startX);
		this.view.setLayoutY(startY);

	}

	public Bullet(int startX, int startY, Point2D velocity, int ballRadius) {
		super(startX, startY, ballRadius * 2, ballRadius * 2, BALL_IMAGE);
		this.velocity = velocity;
		this.projectileRadius = ballRadius;

		setColor(Color.TRANSPARENT);

		view.setLayoutX(startX);
		view.setLayoutY(startY);
	}

	public Bullet(int startX, int startY) {
		super(startX, startY, RADIUS / 2, RADIUS, BALL_IMAGE);
		this.velocity = STARTING_VELOCITY;
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
		// double deltaX = velocity.getX();
		double deltaY = velocity.getY();

		previousLocation = new Point2D(view.getLayoutX(), view.getLayoutY());
		// double newX = (view.getLayoutX() + (deltaX * FRICTION_FACTOR) * elapsedTime);
		double newY = (view.getLayoutY() - deltaY * elapsedTime);

		// Update the circle's position
		// view.setLayoutX(newX);
		view.setLayoutY(newY);
	}

}
