/**
 * @author Aidan Jimenez
 */
package blocks;

import Ball.Ball;
import interfaces.Collidable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Brick extends Block implements Collidable {
	private static final int COLOR_CHANGE_FACTOR = 15;
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

	/**
	 * Changes the color to reflect that is has lost durability
	 */
	private void alterColor() {
		Color currentColor = (Color) rect.getFill();

		double hue = currentColor.getHue();
		double saturation = currentColor.getSaturation();
		double brightness = currentColor.getBrightness();

		double newHue = (hue - COLOR_CHANGE_FACTOR) % 360;

		Color newColor = Color.hsb(newHue, saturation, brightness);

		rect.setFill(newColor);
	}

	@Override
	public void handleCollision(Ball ball) {
		Shape intersection = Shape.intersect(ball.getBall(), getCollisionBox());

		if (!intersection.getBoundsInLocal().isEmpty()) {
			double intersectionWidth = intersection.getBoundsInLocal().getWidth();
			double intersectionHeight = intersection.getBoundsInLocal().getHeight();

			if (intersectionWidth > intersectionHeight) {
				ball.bounce(false, getHitForceMultiplier()); // isReflectingXAxis is false
			} else {
				ball.bounce(true, getHitForceMultiplier()); // isReflectingXAxis is true
			}

			//checkBrickHealth();

			//playerController.addBrickValueToScore(brick.getPoints());
			//score.setText(playerController.getScore() + " points");

			//chanceToActivateShieldOnBrickHit();
		}
	}
}
