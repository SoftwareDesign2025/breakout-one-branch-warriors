//Author: Carter Puckett and Benji Altmann
package game;

import entities.blocks.Paddle;
import entities.blocks.Player;
import game.gamecontroller.GameController;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import projectiles.Bullet;

public class PlayerController {
	
	/*
	 * This class contains logic for all player inputs and stats
	 */
	
	protected static final int MAX_LIVES = 3;
	
	protected int score;
	protected int lives;
	protected String playerName;
	HighScoreController highScoreController;
	GameController gameController;
	Player player;
	
	
	public PlayerController() {
		this.lives = MAX_LIVES;
		playerName = "Test_Player";
		score = 0;
	}
	
	public PlayerController(Player player) {
		this();
		this.highScoreController = new HighScoreController();
		this.player = player;
	
	}
	
	/*
	 * IDK the point of this one
	 */
	public PlayerController(Player player, GameController gameController) {
		this(player);
	}
	
	
	/**
	 * handles the input for moving the paddle left or right
	 * @param keyCode
	 */
	public boolean handleKeyInput(KeyCode keyCode, double elapsedTime) {
		if (keyCode == KeyCode.RIGHT || keyCode == KeyCode.D) {
			player.moveHorizontally(true, elapsedTime);
			
		} else if (keyCode == KeyCode.LEFT || keyCode == KeyCode.A){
			player.moveHorizontally(false, elapsedTime);
		} 
		else if(keyCode == KeyCode.SPACE) {
			return true;
			
		}
		return false;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * returns true if the player has no remaining lives and false if they have at least 1 life
	 * @return
	 */
	public boolean isPlayerDead() {
		return lives <= 0;
	}
	
	/**
	 * if the current score is higher than the current highscore, sets the new highscore
	 * @return
	 */
	public void addScoreToHighScores() {
		highScoreController.addToHighScores(score, playerName);	
	}
	
	/**
	 * called when something updates the score. Adds value to the current score
	 * @param value
	 */
	public void addValueToScore(int value) {
		score += value;
	}
	
	/**
	 * called when something happens to cause the player to lose a life.
	 */
	public void subtractLife() {
		if (lives > 0) {
			lives--;
		}
	}
	
	/**
	 * Stops the paddle
	 */
	public void stopPlayer() {
		player.stop();
	}
	
	/**
	 * Will animate the paddle moving across the scene
	 * 
	 * @param goRight
	 */
	public void playerMovesRight(boolean goRight, double elapsedTime) {
		player.moveHorizontally(goRight, elapsedTime);
	}
	
	public int getLives() {
		return lives;
	}
	
	public Integer getScore() {
		return score;
	}
	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getHighScore() {
		int score = highScoreController.splitScore(highScoreController.getHighScores()[0]);
		return score;
	}

	public void handleKeyRelease(KeyCode keyCode) {
		if (keyCode == KeyCode.RIGHT || keyCode == KeyCode.D) {
			player.stop();
		} else if (keyCode == KeyCode.LEFT || keyCode == KeyCode.A){
			player.stop();
		}
	}
}
