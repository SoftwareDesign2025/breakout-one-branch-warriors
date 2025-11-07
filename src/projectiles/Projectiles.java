package projectiles;

import java.util.Random;

import entities.*;
import entities.blocks.Player.MoveState;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

//
public abstract class Projectiles extends Entity {
	// instance variables
	protected Random random = new Random();
	protected Point2D velocity;
	protected Point2D position;
	protected Point2D previousLocation;
	protected int projectileRadius;
	public boolean inCollision;

	// Constructors
	public Projectiles(int xPosition, int yPosition, int width, int height) {
		super(xPosition, yPosition, width, height);
	}

	public Projectiles(int xPosition, int yPosition, int width, int height, String image) {
		super(xPosition, yPosition, width, height, image);
	}

	// Input: NONE
	// Output: Shape
	// Purpose: return the Shape of the projectile
	public Shape getProjectile() {
		return rect;
	}

	public void bounce(boolean b, MoveState moveState) {
		// left empty to satisfy collisions
	}

	public void bounce(boolean b, double hitForceMultiplier) {
		// left empty to satisfy collisions
	}

	public boolean step() {
		return view.getLayoutY() < 100;
	}

	/**
	 * Stops the projectile
	 */
	public void stop() {
		velocity = new Point2D(0, 0);
	}
	// GETTERS AND SETTERS //

	/**
	 * Getter for the view of the projectile object
	 * 
	 * @return Node
	 */
	public Node getView() {
		return view;
	}

	/**
	 * Getter for the Shape object of the projectile object
	 * 
	 * @return Node
	 */

	/**
	 * Getter for the projectileRadius of the circle
	 * 
	 * @return double
	 */
	public double getRadius() {
		return projectileRadius;
	}

	/**
	 * Getter for the projectile position
	 * 
	 * @return Point2D
	 */
	public Point2D getPosition() {
		return position;
	}

	/**
	 * Setter for the projectile color
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		rect.setFill(color);
	}

	/**
	 * Setter for the X position of the projectile
	 * 
	 * @param x
	 */
	/**
	 * Setter for the Y position of the projectile
	 * 
	 * @param y
	 */
	public void setY(double y) {
		view.setLayoutY(y);
	}

}
