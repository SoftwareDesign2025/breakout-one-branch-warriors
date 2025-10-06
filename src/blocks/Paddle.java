package blocks;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Paddle extends Block {
	private static final int MOVE_SPEED = 15;

//	public Paddle(int xPosition, int yPosition, int width, int height) {
//		super(xPosition, yPosition, width, height);
//	}

	/*
	 * Paddle constructor using a color fill
	 */
	public Paddle(Color color, int xPosition, int yPosition, int width, int height) {
		super(color, xPosition, yPosition, width, height );
	}

	/*
	 * Paddle constructor using an image fill
	 */
	public Paddle(int xPosition, int yPosition, int width, int height, Image image) {
		super(xPosition, yPosition, width, height, image);
	}

	/*
	 * Provides paddle movement which the user can use to control
	 */
	public void moveHorizontally(boolean goRight) {
		if (goRight)
			this.setX(this.getX() - MOVE_SPEED);

		else {
			this.setX(this.getX() + MOVE_SPEED);
		}
	}

}
