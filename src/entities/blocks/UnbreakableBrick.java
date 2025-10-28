/**
 * @author Aidan Jimenez
 */
package entities.blocks;

import javafx.scene.paint.Color;

public class UnbreakableBrick extends Brick {

	public UnbreakableBrick(int xPosition, int yPosition, int width, int height, double hitForceMultiplier) {
		super(xPosition, yPosition, width, height, hitForceMultiplier, 0, Integer.MAX_VALUE, Color.BLANCHEDALMOND);
	}

	public void removeDurability() {
		return;
	}
}
