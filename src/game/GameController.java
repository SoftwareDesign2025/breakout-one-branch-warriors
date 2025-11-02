//Author: Carter Puckett, Aidan Spoerndle, Aidan Jimenez 
package game;

import entities.blocks.Boundary;
import entities.blocks.Brick;
import layouts.BrickLayout;
import entities.blocks.Paddle;
import interfaces.Collidable;
import interfaces.IMoveable;
import Projectiles.Ball;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import java.util.*;

public class GameController {

	private final int FINAL_LEVEL_NUMBER = 3;
	private final int STARTING_LEVEL_NUMBER = 1;
	private final int PLAYER_LIVES_AT_LEVEL_START = 3;
	public static final int SHIELD_CHANCE = 24;

	public static final int NUM_BALLS = 1;
	public static final int BLOCK_SIZE = 50;

	private List<IMoveable> moveables = new ArrayList<>();

	private PlayerController playerController;
	private BrickLayout brickLayout;
	private AnimationController animationController;
	private UIController uiController;
	private Paddle paddle;
	private Boundary boundary;

	private List<Ball> balls = new ArrayList<>();
	private List<Brick> myBricks = new ArrayList<>();
	private List<Collidable> myCollidables = new ArrayList<>();

	private String gameType; // breakout or galaga
	private boolean isGameLost;
	private int level;
	private int screenWidth;
	private int screenHeight;
	private double elapsedTime = 0;
	private Group ui;
	private Group animation;
	private boolean isShieldActive = false;

	private boolean paused = false;

	public GameController(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;

		animationController = new AnimationController();

		initializeGame();
	}

	private void initializeGame() {
		level = STARTING_LEVEL_NUMBER;
		isGameLost = false;

		createUI();
		createLevel();
		createBoundary();
		createPlayer();
		createBall();
	}

	/**
	 * handles everything that happens in each frame (each step)
	 */
	public void step(double elapsedTime) {
		if (!paused) {

			this.elapsedTime = elapsedTime;
			if (playerController.isPlayerDead()) {
				gameEnded();
			}

			if (brickLayout.blocksLeft() == 0) {
				progressLevel();
			}

			uiController.updateUI(playerController.getLives(), playerController.getScore(),
					playerController.getHighScore(), level);

			animationController.step(elapsedTime);

			// check through for collisions -> add to list
			// iterate through second list do the reactions

			List<Collidable> collided = new ArrayList<>();
			// Handle all collisions, will have to add any potential collidables to list
			// "myCollidables"
			for (Ball ball : balls) {
				ball.bounceOffWall(screenWidth, screenHeight);
				for (Collidable collidable : myCollidables) {
					if (!collidable.checkCollision(ball)) {
						collided.add(collidable);

					}
				}
			}

			// Only handle the collision with the ball for the first collision and manage
			// the rest
			for (Ball ball : balls) {
				for (int i = 0; i < collided.size(); i++) {
					if (i == 0) {
						collided.get(i).handleCollision(ball, this);
					} else {
						collided.get(i).manageCollision(this);
					}
				}
			}
		}
	}

	/**
	 * handles things that happen once the game has ended
	 */
	private void gameEnded() {
		uiController.showGameOverMessage();

		cleanBalls();

		isGameLost = true;
	}

	/**
	 * progresses the game to the next level
	 */
	private void progressLevel() {
		if (level >= FINAL_LEVEL_NUMBER) {
			level = STARTING_LEVEL_NUMBER;
		} else {
			level++;
		}


		playerController.setLives(PLAYER_LIVES_AT_LEVEL_START);
		constructLevel();

	}

	/**
	 * gives a 1 in SHIELD_CHANCE + 1 chance of activating the shield
	 */
	public void chanceToActivateShieldOnBrickHit() {
		int randomNum = (int) (Math.random() * SHIELD_CHANCE) + 1;

		if (randomNum == SHIELD_CHANCE) {
			isShieldActive = true;
			boundary.setFill(Color.BLUE);
		}
	}

	/**
	 * returns true if the shield is active
	 * 
	 * @return
	 */
	public boolean getIsShieldActive() {
		return isShieldActive;
	}

	public Paddle getPaddle() {
		return paddle;
	}

	public void removeShield() {
		isShieldActive = false;
		boundary.setFill(Color.TRANSPARENT);
	}

	public PlayerController getPlayerController() {
		return playerController;
	}

	public List<Brick> getMyBricks() {
		return myBricks;
	}

	public AnimationController getAnimationController() {
		return animationController;
	}

	public Group getAnimationRoot() {
		return animation;
	}

	public void handleKeyInput(KeyCode code) {
		if (code == KeyCode.ESCAPE) {
			this.paused = !paused;
			handlePause();
		} else if (code == KeyCode.F1) {
			progressLevel();
		}
		playerController.handleKeyInput(code, elapsedTime);

	}

	public void handleKeyRelease(KeyCode code) {
		playerController.handleKeyRelease(code);
	}

	public void breakBrick(Brick brick) {
		myCollidables.remove(brick);
		brickLayout.removeBrick(brick);
		animationController.removeFromRoot(brick.getView());
	}

	private void handlePause() {
		if (paused) {
			uiController.showPauseMessage();
		} else {
			uiController.hidePauseMessage();
		}
	}

	private void createBall() {
		Ball ball = new Ball(screenWidth / 2, screenHeight - 120);
		balls.add(ball);
		moveables.add(ball);
		animationController.addToMoveables(ball);
		animationController.addToRoot(ball.getView());
	}

	private void cleanBalls() {
		// Remove from scene
		balls.forEach(ball -> {
			animationController.removeFromRoot(ball.getView());
			animationController.removeFromMoveables(ball);
		});

		// List
		int listSize = balls.size();
		for (int i = 0; i < listSize; i++) {
			balls.remove(i);
		}
	}

	/**
	 * builds brick layout of the level
	 */
	private void constructLevel() {
		cleanBricks();
		cleanBalls();

		createLevel();
		createBall();
	}

	private void createLevel() {
		brickLayout = new BrickLayout(screenWidth, screenHeight, level);
		myBricks = brickLayout.getMyBlocks();

		myBricks.forEach(block -> myCollidables.add(block));
		myBricks.forEach(block -> animationController.addToRoot(block.getView()));
	}

	private void cleanBricks() {
		int listSize = myBricks.size();
		for (int i = 0; i < listSize; i++) {
			animationController.removeFromRoot(myBricks.get(i).getView());
			myCollidables.remove(myBricks.get(i));
		}
	}

	private void createBoundary() {
		boundary = new Boundary(Color.TRANSPARENT, 0, screenHeight - 10, screenWidth, 20);
		animationController.addToRoot(boundary.getView());
		myCollidables.add(boundary);
	}

	private void createUI() {
		uiController = new UIController();
		ui = uiController.createGroupForUI(screenWidth, screenHeight);
		animation = animationController.createRootForAnimation(screenWidth, screenHeight);
		animationController.addToRoot(ui);
	}

	private void createPlayer() {
		paddle = new Paddle(screenWidth / 2 - BLOCK_SIZE, screenHeight - 100, BLOCK_SIZE * 2, BLOCK_SIZE / 2,
				screenWidth);
		playerController = new PlayerController(paddle);
		animationController.addToRoot(paddle.getView());
		myCollidables.add(paddle);
	}

}
