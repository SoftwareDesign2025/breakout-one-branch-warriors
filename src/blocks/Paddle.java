package blocks;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Paddle extends Block {
	private static final int MOVE_SPEED = 15;
	private boolean moved = false;
	private int boardWidth;
	private int paddleWidth;

//	public Paddle(int xPosition, int yPosition, int width, int height) {
//		super(xPosition, yPosition, width, height);
//	}

	/*
	 * Paddle constructor using a color fill
	 */
	public Paddle(Color color, int xPosition, int yPosition, int width, int height, int boardWidth) {
		super(color, xPosition, yPosition, width, height );
		this.boardWidth = boardWidth;
		this.paddleWidth = width;
	}

	/*
	 * Paddle constructor using an image fill
	 */
	public Paddle(int xPosition, int yPosition, int width, int height, int  boardWidth,Image image) {
		super(xPosition, yPosition, width, height, image);
		this.boardWidth = boardWidth;
		this.paddleWidth = width;
	}

	/*
	 * Provides paddle movement which the user can use to control
	 */
	public void moveHorizontally(boolean goRight) {
		if(!moved) {
			moved = true;
		}
		if (goRight && this.getView().getLayoutX() + paddleWidth < boardWidth) {
		    this.setX(this.getX() + MOVE_SPEED);
		} else if (!goRight && this.getView().getLayoutX() > 0) {
		    this.setX(this.getX() - MOVE_SPEED);
		}
	}

	public boolean hasBeenMoved() {
		return moved;
	}

}
