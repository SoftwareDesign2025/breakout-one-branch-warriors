package game.gamecontroller;

import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import entities.blocks.Player;
import game.AnimationController;
import game.PlayerController;
import game.UIController;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import layouts.Layout;
import interfaces.Collidable;
import interfaces.IMoveable;

public abstract class GameController {

	protected static final int ITEM_SIZE = 50;
	protected final int FINAL_LEVEL_NUMBER = 3;
	protected final int STARTING_LEVEL_NUMBER = 1;
	protected final int PLAYER_LIVES_AT_LEVEL_START = 3;

	protected int level;
	protected int screenWidth;
	protected int screenHeight;
	protected double elapsedTime = 0;
	protected Group ui;
	protected Group animation;
	protected Player player;

	protected PlayerController playerController;
	protected AnimationController animationController;
	protected UIController uiController;


	protected List<Collidable> myCollidables = new ArrayList<>();
	protected List<IMoveable> moveables = new ArrayList<>();


	protected boolean paused = false;
	protected boolean isGameLost = false;

	public GameController(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;

		animationController = new AnimationController();

		initializeGame();
	}
	
	public abstract void step(double elapsedTime);
	
	protected abstract void initializeGame();
	protected abstract void createLevel();
	protected abstract void constructLevel();
	protected abstract void createUI();
	protected abstract void createPlayer();
	protected abstract void cleanLevel();
	public abstract void breakItem(Collidable collidable);

	/**
	 * progresses the game to the next level
	 */
	protected void progressLevel() {
		if (level >= FINAL_LEVEL_NUMBER) {
			level = STARTING_LEVEL_NUMBER;
		} else {
			level++;
		}

		playerController.setLives(PLAYER_LIVES_AT_LEVEL_START);
		constructLevel();
	}

	/**
	 * Sets the game to an ended state
	 */
	protected void gameEnded() {
		isGameLost = true;
	}
	
	protected void handlePause() {
		if (paused) {
			uiController.showPauseMessage();
		} else {
			uiController.hidePauseMessage();
		}
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
	
	public Group getAnimationRoot() {
		return animation;
	}

	public AnimationController getAnimationController() {
		return animationController;
	}
	
	public void addToScore(int points) {
		playerController.addValueToScore(points);
	}

	public PlayerController getPlayerController() {
		return playerController;
	}

	public void handleShield() {
	}

}
