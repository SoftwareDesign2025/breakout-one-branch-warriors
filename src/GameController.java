//Author: Carter Puckett and Aidan Spoerndle 

import entities.blocks.Boundary;
import entities.blocks.Brick;
import layouts.BrickLayout;
import entities.blocks.Paddle;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import Ball.Ball;
import interfaces.IMoveable;

import java.util.*;

public class GameController {
	
	private final int FINAL_LEVEL_NUMBER = 3;
	private final int STARTING_LEVEL_NUMBER = 1;
	private final int PLAYER_LIVES_AT_LEVEL_START = 3;
	public static final int SHIELD_CHANCE = 24;

	public static final int NUM_BALLS = 1;
	public static final int BLOCK_SIZE = 50;

	private List<Ball> myBalls = new ArrayList<>();
	private List<IMoveable> moveables =  new ArrayList<>();
	
	private PlayerController playerController;
	private HighScoreController highScoreController;
	private BrickLayout brickLayout;
	private AnimationController animationController;
	private UIController uiController;
	private Paddle paddle;
	private Boundary boundary;
	
	private List<Ball> balls = new ArrayList<>();
	private List<Brick> myBricks = new ArrayList<>();
	
	private String gameType; //breakout or galaga
	private boolean isGameLost;
	private int level;
	private int screenWidth;
	private int screenHeight;
	private double elapsedTime = 0;
	private Group ui;
	private Group animation;
	private boolean isShieldActive = false;
	
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
		createPlayer();
		createBall();
	}
	
	/**
	 * handles everything that happens in each frame (each step)
	 */
	public void step(double elapsedTime) {
		this.elapsedTime = elapsedTime;
		if (playerController.isPlayerDead()) {
			gameEnded();
		}
		
		if (brickLayout.blocksLeft() == 0) {
			progressLevel();
		}
		
		uiController.updateUI(playerController.getLives(), playerController.getScore(), playerController.getHighScore(), level);
		
		animationController.step(elapsedTime);
	}
	
	/**
	 * handles things that happen once the game has ended
	 */
	private void gameEnded() {
		uiController.showGameOverMessage();
		isGameLost = true;
	}
	
	/**
	 * progresses the game to the next level
	 */
	private void progressLevel() {
		if (level >= FINAL_LEVEL_NUMBER) {
			level = STARTING_LEVEL_NUMBER;
		}
		
		level++;
		playerController.setLives(PLAYER_LIVES_AT_LEVEL_START);
		
		constructLevel();
	}
	
	/**
	 * builds brick layout of the level
	 */
	private void constructLevel() {
		brickLayout = new BrickLayout(screenHeight, screenWidth, level);
		
		for (int i = 0; i < myBricks.size(); i++) {
			animationController.removeFromRoot(myBricks.get(i).getView());
			brickLayout.removeBrick(myBricks.get(i));
		}
		
		myBricks = brickLayout.getMyBlocks();

		myBricks.forEach(block -> animationController.addToRoot(block.getView()));
		playerController.setLives(3);
//		animationController.setBallToPaddle();
	}
	
	/**
	 * Checks health of the brick to remove from scene
	 * 
	 * @param brick
	 */
	public void checkBrickHealth(Brick brick) {
		if (!brick.isBroken()) {
			brick.removeDurability();
		} else {
			animationController.removeFromRoot(brick.getView());;
			myBricks.remove(brick);
		}
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
	 * @return
	 */
	public boolean getIsShieldActive() {
		return isShieldActive;
	}
	
	public Group getAnimationRoot() {
		return animation;
	}

	public void handleKeyInput(KeyCode code) {
		playerController.handleKeyInput(code, elapsedTime);

	}

	public void handleKeyRelease(KeyCode keyCode) {
	}
	
	private void createBall() {
		Ball ball = new Ball(screenWidth / 2, screenHeight- 120, new Point2D(50, -250), 10);
		balls.add(ball);
		moveables.add(ball);
		animationController.addToMoveables(ball);
		animationController.addToRoot(ball.getView());
	}
	
	private void createLevel() {
		brickLayout = new BrickLayout(screenWidth, screenHeight, level);
		myBricks = brickLayout.getMyBlocks();
		myBricks.forEach(block -> animationController.addToRoot(block.getView()));
		boundary = new Boundary(Color.TRANSPARENT, 0, screenHeight- 10, screenWidth, 20);
		animationController.addToRoot(boundary.getView());
	}
	
	private void createUI() {
		uiController = new UIController();
		ui = uiController.createGroupForUI(screenWidth, screenHeight);
		animation = animationController.createRootForAnimation(screenWidth, screenHeight);
		animationController.addToRoot(ui);
	}
	
	private void createPlayer() {
		paddle = new Paddle( screenWidth/ 2 - BLOCK_SIZE, screenHeight- 100, BLOCK_SIZE * 2, BLOCK_SIZE / 2, screenWidth);
		playerController = new PlayerController(paddle);
		animationController.addToRoot(paddle.getView());
	}
}
