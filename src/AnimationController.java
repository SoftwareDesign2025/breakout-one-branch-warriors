import blocks.Block;
import blocks.Paddle;
import blocks.Brick;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;


public class AnimationController {

	public static final String PADDLE_IMAGE = "resources/paddle.gif";
	public static final Paint MOVER_COLOR = Color.ALICEBLUE;
	public static final Paint HIGHLIGHT = Color.OLIVEDRAB;
	public static final int MOVER_SIZE = 50;
	public static final int MOVER_SPEED = 15;
	public static final int NUM_BOUNCERS = 1;
	public static final int NUM_BRICKS = 54;

	private List<Ball> myBouncers = new ArrayList<>();
	private List<Block> myBlocks = new ArrayList<>();

	private Paddle paddle;

	private int width;
	private int height;

	public Group createRootForAnimation(int windowWidth, int windowHeight) {
		width = windowWidth;
		height = windowHeight;

		Group root = new Group();

		Ball ball = new Ball();
		myBouncers.add(ball);

		myBlocks = createBrickLayout();

		try {
			Image image = new Image(new FileInputStream(PADDLE_IMAGE));

			paddle = new Paddle(width / 2 - MOVER_SIZE, height / 2 + 100, MOVER_SIZE * 2, MOVER_SIZE / 2, image);
			myBlocks.add(paddle);

			myBlocks.forEach(block -> root.getChildren().add(block.getView()));
		} catch (FileNotFoundException e) {
		}

		root.getChildren().add(ball.getView());

		return root;
	}

	private List<Block> createBrickLayout(){
		float currentHue = 180f; 
		final float saturation = 0.9f; 
		final float brightness = 1.0f;
		List<Block> myBlocks = new ArrayList<>();

		int xPos = 0;
		int yPos = height / 2 - 100;

		for(int i = 0; i < NUM_BRICKS; i++) {
			Color rectColor = Color.hsb(currentHue, saturation, brightness);
			if(xPos >= width) {
				xPos = 0;
				yPos -= MOVER_SIZE / 2;
			}
			if(yPos < 0) {
				break;
			}
			Brick brick = new Brick(xPos, yPos, MOVER_SIZE * 2, MOVER_SIZE / 2);
			brick.setFill(rectColor);
			myBlocks.add(brick);
			xPos += MOVER_SIZE * 2;
			currentHue += 5;
		}

		return myBlocks;
	}


	public void step(double elapsedTime) {
		//		 update "actors" attributes
		for (Ball b : myBouncers) {
			b.move(elapsedTime);
		}
		// bounce off all the walls
		for (Ball b : myBouncers) {
			b.bounceOffWall(width, height);
		}
		// check collisions with all blocks
		for (Ball ball : myBouncers) {
			for (Block block : myBlocks) {
				Shape intersection = Shape.intersect(ball.getBall(), block.getCollisionBox());
				if(!intersection.getBoundsInLocal().isEmpty()){
					double intersectionWidth = intersection.getBoundsInLocal().getWidth();
					double intersectionHeight = intersection.getBoundsInLocal().getHeight();
					if (intersectionWidth > intersectionHeight) {
						ball.bounce(false, 1.015); // isReflectingXAxis is false
					} else {
						ball.bounce(true, 1.015); // isReflectingXAxis is true
					}

				}

			}
		}

	}

	public void moverMovesHorizontally(boolean goRight) {
		paddle.moveHorizontally(goRight);
	}
}
