/**
 * @author Aidan Jimenez
 */
import blocks.Paddle;

import blocks.Brick;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;


public class AnimationController {

	public static final String PADDLE_IMAGE = "resources/paddle.gif";
	public static final int MOVER_SIZE = 50;
	public static final int MOVER_SPEED = 15;
	public static final int NUM_BOUNCERS = 1;
	public static final int NUM_BRICKS = 54;

	private List<Ball> myBouncers = new ArrayList<>();
	private List<Brick> myBlocks = new ArrayList<>();

	private Paddle paddle;
	private Group root;

	private int width;
	private int height;

	/**
	 * Creates the scene required for the game to start adding all assets
	 * @return Group
	 */
	public Group createRootForAnimation(int windowWidth, int windowHeight) {
		width = windowWidth;
		height = windowHeight;

		root = new Group();

		Ball ball = new Ball(width / 2, height - 120, new Point2D(50,-250), 10, Color.RED);
		myBouncers.add(ball);

		myBlocks = createBrickLayout();

		try {
			Image image = new Image(new FileInputStream(PADDLE_IMAGE));

			paddle = new Paddle(width / 2 - MOVER_SIZE, height - 100, MOVER_SIZE * 2, MOVER_SIZE / 2, width, image);

			myBlocks.forEach(block -> root.getChildren().add(block.getView()));
		} catch (FileNotFoundException e) {
		}
		
		

		root.getChildren().add(paddle.getView());
		root.getChildren().add(ball.getView());

		return root;
	}

	/**
	 * Generates the brick layout for the game
	 * @return List<Brick>
	 */
	// TODO: make a new class that can handle this logic
	private List<Brick> createBrickLayout(){
		float currentHue = 180f; 
		final float saturation = 1.0f; 
		final float brightness = 1.0f;
		int lives = 1;
		double powerFactor = 1.025;
		int points = 5;
		List<Brick> myBlocks = new ArrayList<>();

		int xPos = 0;
		int yPos = height / 2 - 100;

		for(int i = 0; i < NUM_BRICKS; i++) {
			if(xPos >= width) {
				xPos = 0;
				yPos -= MOVER_SIZE / 2;
				currentHue += 15;
				powerFactor += 0.010;
				points += 5;
				lives += 1;
			}
			if(yPos < MOVER_SIZE) {
				break;
			}
			Color rectColor = Color.hsb(currentHue, saturation, brightness);
			Brick brick = new Brick(xPos, yPos, MOVER_SIZE * 2, MOVER_SIZE / 2, powerFactor, points, lives, rectColor);
			myBlocks.add(brick);
			xPos += MOVER_SIZE * 2;
		}

		return myBlocks;
	}


	/**
	 * Makes a new step in the animation checking for collisions as objects in the scene move around
	 * @param elapsedTime
	 */
	public void step(double elapsedTime) {
		for (Ball ball : myBouncers) {
			if(paddle.hasBeenMoved()) {
				ball.move(elapsedTime);
			} else {
				ball.setX(paddle.getX() + MOVER_SIZE);
			}

			if(myBlocks.size() == 0) {
				ball.stop();
			}
		}

		for (Ball ball : myBouncers) {
			ball.bounceOffWall(width, height);
		}

		for (Ball ball : myBouncers) {
			checkCollisionWithPaddle(ball, paddle);
		}

		for(Brick brick : myBlocks) {
			checkCollisionWithBricks(brick);
		}

	}

	/**
	 * Checks for collisions with bricks
	 * @param brick
	 */
	private void checkCollisionWithBricks(Brick brick) {
		for (Ball ball : myBouncers) {

			Shape intersection = Shape.intersect(ball.getBall(), brick.getCollisionBox());

			if(!intersection.getBoundsInLocal().isEmpty()){
				double intersectionWidth = intersection.getBoundsInLocal().getWidth();
				double intersectionHeight = intersection.getBoundsInLocal().getHeight();

				if (intersectionWidth > intersectionHeight) {
					ball.bounce(false, brick.getHitForceMultiplier()); // isReflectingXAxis is false
				} else {
					ball.bounce(true, brick.getHitForceMultiplier()); // isReflectingXAxis is true
				}

				checkBrickHealth(brick);
			}
		}
	}


	/**
	 * Checks for collisions with the player paddle
	 * @param ball
	 * @param paddle 
	 */
	private void checkCollisionWithPaddle(Ball ball, Paddle paddle) {
		Shape intersection = Shape.intersect(ball.getBall(), paddle.getCollisionBox());

		if(!intersection.getBoundsInLocal().isEmpty()){

			double intersectionWidth = intersection.getBoundsInLocal().getWidth();
			double intersectionHeight = intersection.getBoundsInLocal().getHeight();

			if (intersectionWidth > intersectionHeight) {
				ball.bounce(false, paddle.getState()); // isReflectingXAxis is false
			} else {
				ball.bounce(true, paddle.getState()); // isReflectingXAxis is true
			}
		}
	}


	/**
	 * Checks health of the brick to remove from scene
	 * @param brick
	 */
	// TODO: move to same class as the create brick layout
	private void checkBrickHealth(Brick brick) {
		if(!brick.isBroken()) {
			brick.removeDurability();
		} else {
			root.getChildren().remove(brick.getView());
			myBlocks.remove(brick);
		}

	}


	/**
	 * Will animate the paddle moving across the scene 
	 * @param goRight
	 */
	// TODO: move to player controller
	public void paddleMovesRight(boolean goRight) {
		paddle.moveHorizontally(goRight);
	}

	/**
	 * Stops the paddle
	 */
	// TODO: move to player controller
	public void stopPaddle() {
		paddle.stop();
	}
}
