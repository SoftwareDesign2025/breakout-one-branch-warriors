package blocks;

import javafx.scene.paint.Color;

public class UnbreakableBrick extends Brick {

	public UnbreakableBrick(int xPosition, int yPosition, int width, int height, double hitForceMultiplier, int points) {
		super(xPosition, yPosition, width, height, hitForceMultiplier, points, Integer.MAX_VALUE, Color.BLANCHEDALMOND);
	}
	
	public void removeDurability() {
		return;
	}

}
