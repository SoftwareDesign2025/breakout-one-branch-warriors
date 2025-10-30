/**
 * @author Aidan Jimenez
 */



import entities.blocks.Paddle;
import interfaces.Collidable;
import entities.Entity;
import entities.blocks.Boundary;
import entities.blocks.Brick;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import Ball.Ball;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import layouts.BrickLayout;

public class AnimationController {

	public static final int MOVER_SPEED = 15;
	public static final int NUM_BALLS = 1;
	public static final int BLOCK_SIZE = 50;

	private List<Ball> myBalls = new ArrayList<>();
	private List<Brick> myBlocks = new ArrayList<>();

	private Paddle paddle;
	private Boundary boundary;
	private PlayerController playerController;
	private BrickLayout brickLayout;
	private Group root;

	private int width;
	private int height;

	private boolean gameEnded = false;

	private Group ui;
	private UIController uiController;

	/**
	 * Creates the scene required for the game to start adding all assets
	 * 
	 * @return Group
	 */
	public Group createRootForAnimation(int windowWidth, int windowHeight) {

		width = windowWidth;
		height = windowHeight;

		root = new Group();

		// StackPane scene = new StackPane(ui, root);

		boundary = new Boundary(Color.TRANSPARENT, 0, height - 10, width, 20);
		this.playerController = new PlayerController(paddle);

		Ball ball = new Ball(width / 2, height - 120, new Point2D(50, -250), 10);
		myBalls.add(ball);

		brickLayout = new BrickLayout(height, width, 1);
		myBlocks = brickLayout.getMyBlocks();


		paddle = new Paddle( width / 2 - BLOCK_SIZE, height - 100, BLOCK_SIZE * 2, BLOCK_SIZE / 2, width );

			myBlocks.forEach(block -> addToRoot(block.getView()));
		} catch (FileNotFoundException e) {
		}

		addToRoot(paddle.getView());
		addToRoot(ball.getView());
		addToRoot(boundary.getView());
		addToRoot(ui);

		return root;
	}
	
	public void addToRoot(Node node) {
		root.getChildren().add(node);
	}
	
	public void removeFromRoot(Node node) {
		root.getChildren().remove(node);
	}

	public void setBallToPaddle() {
		for (Ball ball : myBalls) {
			ball.setX(paddle.getX() + BLOCK_SIZE);
			ball.setY(paddle.getY() - 10);
		}
	}
	
	public void stopPaddle() {
		paddle.stop();
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
					playerController.addScoreToHighScores();
					//nextLevel(width, height);
				}
			}
			

			// store results of collision instead of have them happen right away

			for (Ball ball : myBalls) {
				ball.bounceOffWall(width, height);
			}

			/* waiting for collision handling
			
			for (Ball ball : myBalls) {
				checkCollisionWithPaddle(ball, paddle);
			}

			for (Ball ball : myBalls) {
				checkCollisionWithBoundary(ball, boundary);
			}

			for (Entity brick : myBlocks) {
				checkCollisionWithBricks((Brick) brick);
			}
			
			*/ 
		} else {
			for (Ball ball : myBalls) {
				ball.stop();
			}

			if (!gameEnded) {
				gameEnded = true;
				playerController.addScoreToHighScores();
				//uiController.showGameOverMessage();
				// highScore.setText("highscore: " +
				// highScoreController.splitScore(highScoreController.getHighScores()[0]));
			}
		}

	}
	
	public void stopAnimation() {
		stopBalls();
		stopPaddle();
	}
	
	private void stopBalls() {
		for (Ball ball : myBalls) {
			ball.stop();
		}
	}
	
	/*MOVE TO COLLISION HANDLING

	/**
	 * Checks for collisions with bricks
	 * 
	 * @param brick
	 
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

				//checkBrickHealth(brick);

				playerController.addBrickValueToScore(brick.getPoints());
				// score.setText(playerController.getScore() + " points");

				gameController.chanceToActivateShieldOnBrickHit();
			}
		}
	}

	/**
	 * Checks for collisions with the player paddle
	 * 
	 * @param ball
	 * @param paddle
	 
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

			if (!isShieldActive) {
				playerController.subtractLife();
				// lives.setText(playerController.getLives() + " lives");
			}

			// reset position
			ball.setX(paddle.getX() + BLOCK_SIZE);
			ball.setY(paddle.getY() - 10);
			// Time.sleep();

			isShieldActive = false;
			boundary.setFill(Color.TRANSPARENT);
		}
	}

	*/

	
}
