package blocks;

import javafx.scene.paint.Color;

public class DoubleDamageBrick extends Brick{
	
	public DoubleDamageBrick (int xPosition, int yPosition, int width, int height, double hitForceMultiplier, int points, int lives, Color rectColor ) {
		super(xPosition, yPosition, width, height, hitForceMultiplier, points, lives, Color.CHOCOLATE);
	}
	
	/**
	 * Removes a durability from the block
	 */
	public void removeDurability() {
		alterColor();
		durability -= 2;
	}

	protected void alterColor() {
		Color currentColor = (Color) rect.getFill();

		double hue = currentColor.getHue();
		double saturation = currentColor.getSaturation();
		double brightness = currentColor.getBrightness();

		double newHue = (hue - COLOR_CHANGE_FACTOR * 2) % 360;

		Color newColor = Color.hsb(newHue, saturation, brightness);

		rect.setFill(newColor);
	}
}
