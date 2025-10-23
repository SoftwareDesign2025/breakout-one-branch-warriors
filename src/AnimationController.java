
/**
 * @author Aidan Jimenez
 */
import blocks.Paddle;
import blocks.Boundary;
import blocks.Brick;
import blocks.BrickLayout;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class AnimationController {

	public static final String PADDLE_IMAGE = "resources/paddle.gif";
	public static final int MOVER_SPEED = 15;
	public static final int NUM_BALLS = 1;
	public static final int SHIELD_CHANCE = 24;
	public static final int BLOCK_SIZE = 50;

	private List<Ball> myBalls = new ArrayList<>();
	private List<Brick> myBlocks = new ArrayList<>();

	private Paddle paddle;
	private Boundary boundary;
	private PlayerController playerController;
	private HighScoreController highScoreController;
	private BrickLayout brickLayout;
	private Group root;

	private int width;
	private int height;

	private Text lives;
	private Text score;
	private Text highScore;
	private boolean gameEnded = false;
	
	private boolean isShieldActive = false;

	/**
	 * Creates the scene required for the game to start adding all assets
	 * 
	 * @return Group
	 */
	public Group createRootForAnimation(int windowWidth, int windowHeight) {
		width = windowWidth;
		height = windowHeight;

		root = new Group();
		boundary = new Boundary(Color.TRANSPARENT, 0, height - 10, width, 20);
		highScoreController = new HighScoreController();
		playerController = new PlayerController(highScoreController, paddle);

		lives = new Text(10, 20, playerController.getLives() + " lives");
		score = new Text(60, 20, playerController.getScore() + " points");
		highScore = new Text(120, 20, "highscore: " + highScoreController.splitScore(highScoreController.getHighScores()[0]));

		Ball ball = new Ball(width / 2, height - 120, new Point2D(50, -250), 10, Color.RED);
		myBalls.add(ball);

		brickLayout = new BrickLayout(height, width);
		myBlocks = brickLayout.getMyBlocks();

		try {
			Image image = new Image(new FileInputStream(PADDLE_IMAGE));

			paddle = new Paddle(width / 2 - BLOCK_SIZE, height - 100, BLOCK_SIZE * 2, BLOCK_SIZE / 2, width, image);

			myBlocks.forEach(block -> root.getChildren().add(block.getView()));
		} catch (FileNotFoundException e) {
		}

		root.getChildren().add(paddle.getView());
		root.getChildren().add(ball.getView());
		root.getChildren().add(boundary.getView());
		root.getChildren().add(lives);
		root.getChildren().add(score);
		root.getChildren().add(highScore);

		return root;
	}


	/**
	 * Makes a new step in the animation checking for collisions as objects in the
	 * scene move around
	 * 
	 * @param elapsedTime
	 */
	public void step(double elapsedTime) {
		if (!playerController.isPlayerDead()) {
			for (Ball ball : myBalls) {
				if (paddle.hasBeenMoved()) {
					ball.move(elapsedTime);
				} else {
					ball.setX(paddle.getX() + BLOCK_SIZE);
				}

				if (brickLayout.blocksLeft() == 0) {
					ball.stop();
				}
			}

			//store results of collision instead of have them happen right away
			
			for (Ball ball : myBalls) {
				ball.bounceOffWall(width, height);
			}

			for (Ball ball : myBalls) {
				checkCollisionWithPaddle(ball, paddle);
			}

			for (Ball ball : myBalls) {
				checkCollisionWithBoundary(ball, boundary);
			}

			for (Brick brick : myBlocks) {
				checkCollisionWithBricks(brick);
			}
		} else {
			for (Ball ball : myBalls) {
				ball.stop();
			}
			
			if (!gameEnded) {
				gameEnded = true;
				playerController.addScoreToHighScores();
				highScore.setText("highscore: " + highScoreController.splitScore(highScoreController.getHighScores()[0]));
			}
		}

	}

	/**
	 * Checks for collisions with bricks
	 * 
	 * @param brick
	 */
	private void checkCollisionWithBricks(Brick brick) {
		for (Ball ball : myBalls) {

			Shape intersection = Shape.intersect(ball.getBall(), brick.getCollisionBox());

			if (!intersection.getBoundsInLocal().isEmpty()) {
				double intersectionWidth = intersection.getBoundsInLocal().getWidth();
				double intersectionHeight = intersection.getBoundsInLocal().getHeight();

				if (intersectionWidth > intersectionHeight) {
					ball.bounce(false, brick.getHitForceMultiplier()); // isReflectingXAxis is false
				} else {
					ball.bounce(true, brick.getHitForceMultiplier()); // isReflectingXAxis is true
				}

				checkBrickHealth(brick);
				
				playerController.addBrickValueToScore(brick.getPoints());
				score.setText(playerController.getScore() + " points");
				
				chanceToActivateShieldOnBrickHit();
			}
		}
	}

	/**
	 * Checks for collisions with the player paddle
	 * 
	 * @param ball
	 * @param paddle
	 */
	private void checkCollisionWithPaddle(Ball ball, Paddle paddle) {
		Shape intersection = Shape.intersect(ball.getBall(), paddle.getCollisionBox());

		if (!intersection.getBoundsInLocal().isEmpty()) {

			double intersectionWidth = intersection.getBoundsInLocal().getWidth();
			double intersectionHeight = intersection.getBoundsInLocal().getHeight();

			if (intersectionWidth > intersectionHeight) {
				ball.bounce(false, paddle.getState()); // isReflectingXAxis is false
			} else {
				ball.bounce(true, paddle.getState()); // isReflectingXAxis is true
			}
		}
	}

	private void checkCollisionWithBoundary(Ball ball, Boundary boundary) {
		Shape intersection = Shape.intersect(ball.getBall(), boundary.getCollisionBox());

		if (!intersection.getBoundsInLocal().isEmpty()) {
			// remove life
			
			if(!isShieldActive) {
				playerController.subtractLife();
				lives.setText(playerController.getLives() + " lives");
			}
			

			// reset position
			ball.setX(paddle.getX() + BLOCK_SIZE);
			ball.setY(paddle.getY() - 10);
			// Time.sleep();
			
			isShieldActive = false;
			boundary.setFill(Color.TRANSPARENT);
		}
	}

	/**
	 * Checks health of the brick to remove from scene
	 * 
	 * @param brick
	 */
	// TODO: move to same class as the create brick layout
	private void checkBrickHealth(Brick brick) {
		if (!brick.isBroken()) {
			brick.removeDurability();
		} else {
			root.getChildren().remove(brick.getView());
			myBlocks.remove(brick);
		}

	}

	/**
	 * Will animate the paddle moving across the scene
	 * 
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
	
	public void chanceToActivateShieldOnBrickHit() {
		int randomNum = (int) (Math.random() * SHIELD_CHANCE) + 1;
		
		if (randomNum == SHIELD_CHANCE) {
			isShieldActive = true;
			boundary.setFill(Color.BLUE);
		}
	}
}
