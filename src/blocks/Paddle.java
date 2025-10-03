package blocks;

public class Paddle extends Block {
	private static final int MOVE_SPEED = 15;

	public Paddle(int xPosition, int yPosition, int width, int height) {
		super(xPosition, yPosition, width, height);
	}

	public void moveHorizontally(boolean goRight) {
		if (goRight)
			rect.setX(rect.getX() - MOVE_SPEED);

		else {
			rect.setX(rect.getX() + MOVE_SPEED);
		}
	}

}
