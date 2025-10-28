/**
 * @author Aidan Jimenez
 */
package entities.blocks;

import javafx.scene.paint.Color;

public class MultiBallBrick extends Brick{
	
	private static final Color BRICK_COLOR = Color.RED;
	private static final int POINTS = 30;
	private static final int LIVES = 1;
	
	public MultiBallBrick (int xPosition, int yPosition, int width, int height, double hitForceMultiplier ) {
		super(xPosition, yPosition, width, height, hitForceMultiplier, POINTS, LIVES, BRICK_COLOR);
	}
	
}
