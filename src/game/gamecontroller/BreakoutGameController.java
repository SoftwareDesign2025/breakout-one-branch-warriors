//Author: Carter Puckett, Aidan Spoerndle, Aidan Jimenez  
package game.gamecontroller;

import entities.blocks.Boundary;
import layouts.BrickLayout;
import entities.blocks.Paddle;
import game.AnimationController;
import game.BreakOutPlayerController;
import game.PlayerController;
import game.UIController;
import interfaces.Collidable;
import projectiles.Ball;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.*;

public class BreakoutGameController extends GameController {

	private static final int SHIELD_CHANCE = 24;

	private static final int NUM_BALLS = 1;

	private Paddle paddle;
	private Boundary boundary;

	private List<Ball> balls;

	private String gameType; // breakout or galaga
	private boolean isShieldActive = false;
	private BrickLayout itemLayout;

	public BreakoutGameController(int screenWidth, int screenHeight) {
		super(screenWidth, screenHeight);
	}

	protected void initializeGame() {
		level = STARTING_LEVEL_NUMBER;

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

			if (itemLayout.itemsLeft() == 0) {
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


	public void handleKeyInput(KeyCode code) {
		super.handleKeyInput(code);
	}

	public void breakItem(Collidable brick) {
		myCollidables.remove(brick);
		itemLayout.handleRemoval(brick);
		animationController.removeFromRoot(brick.getView());
	}
	 
	@Override
	protected void gameEnded() {
		if(!isGameLost) {
			playerController.addScoreToHighScores();
		}
		super.gameEnded();
		
		uiController.showGameOverMessage();
		cleanBalls();
	}

	@Override
	public void handleShield() {
		if (!isShieldActive) {
			playerController.subtractLife();
		} else {
			removeShield();
		}
	}

	private void createBall() {
		balls = new ArrayList<>();
		for (int i = 0; i < NUM_BALLS; i++) {
			Ball ball = new Ball(screenWidth / 2, screenHeight - 120);
			balls.add(ball);
			moveables.add(ball);
			animationController.addToMoveables(ball);
			animationController.addToRoot(ball.getView());
		}
	}

	private void cleanBalls() {
		// Remove from scene
		balls.forEach(ball -> {
			animationController.removeFromRoot(ball.getView());
			animationController.removeFromMoveables(ball);
			moveables.remove(ball);
		});

		// List
		int listSize = balls.size();
		for (int i = 0; i < listSize; i++) {
			balls.remove(i);
		}
	}

	@Override
	protected void cleanLevel() {
		cleanBricks();
		cleanBalls();
	}

	/**
	 * builds brick layout of the level
	 */
	protected void constructLevel() {
		cleanLevel();

		createLevel();
		createBall();
	}

	protected void createLevel() {
		itemLayout = new BrickLayout(screenWidth, screenHeight, level);
		List<Collidable> bricks = itemLayout.getCollidables();

		bricks.forEach(block -> myCollidables.add(block));
		bricks.forEach(block -> animationController.addToRoot(block.getView()));
	}

	private void cleanBricks() {
		List<Collidable> bricks = itemLayout.getCollidables();
		int listSize = itemLayout.getCollidables().size();
		for (int i = 0; i < listSize; i++) {
			animationController.removeFromRoot(bricks.get(i).getView());
			myCollidables.remove(bricks.get(i));
		}
	}

	private void createBoundary() {
		boundary = new Boundary(Color.TRANSPARENT, 0, screenHeight - 10, screenWidth, 20);
		animationController.addToRoot(boundary.getView());
		myCollidables.add(boundary);
	}

	protected void createUI() {
		uiController = new UIController(Font.font("Arial"), Color.BLACK);
		ui = uiController.createGroupForUI(screenWidth, screenHeight);
		animation = animationController.createRootForAnimation(screenWidth, screenHeight);
		animationController.addToRoot(ui);
	}

	protected void createPlayer() {
		paddle = new Paddle(screenWidth / 2 - ITEM_SIZE, screenHeight - 100, ITEM_SIZE * 2, ITEM_SIZE / 2, screenWidth);
		playerController = new BreakOutPlayerController(paddle);
		animationController.addToRoot(paddle.getView());
		myCollidables.add(paddle);
	}

}