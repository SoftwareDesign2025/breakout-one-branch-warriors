
import blocks.Block;
import blocks.Paddle;
import blocks.Brick;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * 
 * @author Shannon Duvall
 * 
 *         This program animates two squares. The top is the "mover" and
 *         the bottom is the "grower".
 * 
 */

public class AnimationController {

	public static final String BOUNCER_IMAGE = "resources/ball.gif";
	public static final Paint MOVER_COLOR = Color.ALICEBLUE;
	public static final Paint HIGHLIGHT = Color.OLIVEDRAB;
	public static final int MOVER_SIZE = 50;
	public static final int MOVER_SPEED = 15;
	public static final int NUM_BOUNCERS = 1;
	public static final int NUM_BRICKS = 54;

	private List<Bouncer> myBouncers;
	private List<Block> myBlocks = new ArrayList<>();
	private Paddle paddle;
	//  private Brick brick;
	private int width;
	private int height;

	public Group createRootForAnimation(int windowWidth, int windowHeight) {
		width = windowWidth;
		height = windowHeight;

		Group root = new Group();

		List<Block> myBlocks = createBrickLayout();

		//Creates a new paddle in the center of the screen
		paddle = new Paddle(width / 2 - MOVER_SIZE, height / 2 + 100, MOVER_SIZE * 2, MOVER_SIZE / 2);
		paddle.setFill(MOVER_COLOR);
		myBlocks.add(paddle);

		myBlocks.forEach(block -> root.getChildren().add(block.getRect()));

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
			if(xPos == width) {
				xPos = 0;
				yPos -= MOVER_SIZE / 2;
			}
			if(yPos < 0) {
				break;
			}
			Brick brick = new Brick(xPos, yPos, MOVER_SIZE * 2, MOVER_SIZE / 2);
			brick.setFill(rectColor);
			myBlocks.add(brick);
			System.out.println("xPos: " + xPos);
			System.out.println("yPos: " + yPos);
			System.out.println("width: " + width);
			xPos += MOVER_SIZE * 2;
			currentHue += 5;
		}

		return myBlocks;
	}

	public void step(double elapsedTime) {
		// update "actors" attributes
		// for (Bouncer b : myBouncers) {
		// b.move(elapsedTime);
		// }
		// myMover.setRotate(myMover.getRotate() + 1);

		// check for collisions
		// with shapes, can check precisely
		// NOTE: Could be best to use a sphere to measure when something is hit since
		// its more accurate.
		// Shape intersection = Shape.intersect(myMover, myGrower);
		// if (intersection.getBoundsInLocal().getWidth() != -1) {
		// myMover.setFill(HIGHLIGHT);
		// } else {
		// myMover.setFill(MOVER_COLOR);
		// }

		// with images can only check bounding box
		// boolean hit = false;
		// for (Bouncer b : myBouncers) {
		// if (myMover.getBoundsInParent().intersects(b.getView().getBoundsInParent()))
		// {
		// myMover.setFill(HIGHLIGHT);
		// b.bounceOffBlock();
		// hit = true;
		// }else {
		// myMover.setFill(MOVER_COLOR);
	}

	// }
	// if (!hit) {
	// myGrower.setFill(GROWER_COLOR);
	// }

	// bounce off all the walls
	// for (Bouncer b : myBouncers) {
	// b.bounce(width, height);
	// }
	// }

	// public void moverMovesVertically(boolean goUp) {
	// if (goUp)
	// myMover.setY(myMover.getY() - MOVER_SPEED);
	//
	// else {
	// myMover.setY(myMover.getY() + MOVER_SPEED);
	// }
	// }

	public void moverMovesHorizontally(boolean goRight) {
		paddle.moveHorizontally(goRight);
	}

	// public void handleMouseInput(double x, double y) {
	// if (myGrower.contains(x, y)) {
	// myGrower.setScaleX(myGrower.getScaleX() * GROWER_RATE);
	// myGrower.setScaleY(myGrower.getScaleY() * GROWER_RATE);
	//
	// }
	// }
}
