/**
 * @author Aidan Jimenez
 */
package entities.blocks;

import game.GameController;
import Projectiles.Ball;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import projectiles.Ball;

public class Brick extends Block {
	protected static final String BRICK_IMAGE = "resources/green_brick.png";
	protected static final int COLOR_CHANGE_FACTOR = 15;
	private static final int BASE_MULTIPLIER = 2;
	private static final int BASE_POINTS = 10;
	private static final int BASE_DURABILITY = 1;

	protected double hitForceMultiplier;
	protected int points;
	protected int durability;

	/**
	 * Base Constructor for the brick
	 * 
	 * @param xPosition
	 * @param yPosition
	 * @param width
	 * @param height
	 */
	public Brick(int xPosition, int yPosition, int width, int height) {
		super(xPosition, yPosition, width, height);
		this.hitForceMultiplier = BASE_MULTIPLIER;
		this.points = BASE_POINTS;
		this.durability = BASE_DURABILITY;
	}

	/**
	 * Constructor for the brick
	 * 
	 * @param xPosition
	 * @param yPosition
	 * @param width
	 * @param height
	 * @param hitForceMultiplier
	 * @param points
	 * @param durability
	 * @param color
	 */
	public Brick(int xPosition, int yPosition, int width, int height, double hitForceMultiplier, int points,
			int durability, Color color) {
		super(xPosition, yPosition, width, height);
		this.hitForceMultiplier = hitForceMultiplier;
		this.points = points;
		this.durability = durability;
		this.rect.setFill(color);
		this.rect.setStroke(Color.BLACK);
	}

	public Brick(int xPosition, int yPosition, int width, int height, double hitForceMultiplier, int points,
			int durability) {
		super(xPosition, yPosition, width, height, BRICK_IMAGE);
		this.hitForceMultiplier = hitForceMultiplier;
		this.points = points;
		this.durability = durability;
	}

	/**
	 * Removes a durability from the block
	 */
	public void removeDurability() {
		alterColor();
		durability -= 1;
	}

	/**
	 * Checks if the durability is at 0 so it can be considered broken
	 * 
	 * @return boolean
	 */
	public boolean isBroken() {
		return durability <= 1;
	}

	/**
	 * Getter for the hit force that the brick has
	 * 
	 * @return boolean
	 */
	public double getHitForceMultiplier() {
		return hitForceMultiplier;
	}

	public int getPoints() {
		return points;
	}


	@Override
	public void handleCollision(Ball ball, GameController gameController) {
		Shape intersection = Shape.intersect(ball.getBall(), getCollisionBox());
		if (!intersection.getBoundsInLocal().isEmpty()) {
			double intersectionWidth = intersection.getBoundsInLocal().getWidth();
			double intersectionHeight = intersection.getBoundsInLocal().getHeight();

			if (intersectionWidth > intersectionHeight) {
				ball.bounce(false, getHitForceMultiplier()); // isReflectingXAxis is false
			} else {
				ball.bounce(true, getHitForceMultiplier()); // isReflectingXAxis is true
			}
			
			manageCollision(gameController);
		}
	}

	
	public void manageCollision(GameController gameController) {
			checkBrickHealth(gameController);
			gameController.getPlayerController().addBrickValueToScore(getPoints());
			gameController.chanceToActivateShieldOnBrickHit();
	}

	// TODO: try to fix collision
//	@Override
//	public void handleCollision(Ball ball, GameController gameController) {
//		Shape intersection = Shape.intersect(ball.getBall(), getCollisionBox());
//		if (!intersection.getBoundsInLocal().isEmpty()) {
//			Bounds ballBounds = ball.getBall().getBoundsInParent();
//			Bounds wallBounds = getCollisionBox().getBoundsInParent();
//
//			// Calculate overlap from each direction
//			double overlapLeft = ballBounds.getMaxX() - wallBounds.getMinX();
//			double overlapRight = wallBounds.getMaxX() - ballBounds.getMinX();
//			double overlapTop = ballBounds.getMaxY() - wallBounds.getMinY();
//			double overlapBottom = wallBounds.getMaxY() - ballBounds.getMinY();
//
//			// Find minimum overlap (this is the collision axis)
//			double minOverlapX = Math.min(overlapLeft, overlapRight);
//			double minOverlapY = Math.min(overlapTop, overlapBottom);
//
//			ball.bounce(minOverlapX, minOverlapY, 1.0);
//
//			checkBrickHealth(gameController);
//
//			gameController.getPlayerController().addBrickValueToScore(getPoints());
//
//			gameController.chanceToActivateShieldOnBrickHit();
//		}
//
//	}

	/**
	 * Changes the color to reflect that is has lost durability
	 */
	protected void alterColor() {
		Color currentColor = (Color) rect.getFill();

		double hue = currentColor.getHue();
		double saturation = currentColor.getSaturation();
		double brightness = currentColor.getBrightness();

		double newHue = (hue - COLOR_CHANGE_FACTOR) % 360;

		Color newColor = Color.hsb(newHue, saturation, brightness);

		rect.setFill(newColor);
	}

	/**
	 * Checks health of the brick to remove from scene
	 *
	 * @param brick
	 */
	private void checkBrickHealth(GameController gameController) {
		if (!isBroken()) {
			removeDurability();
		} else {
			gameController.breakBrick(this);
		}
	}

}
