//Author: Carter Puckett
package game;

import entities.blocks.Paddle;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import projectiles.Bullet;

public class PlayerController {
	
	/*
	 * This class contains logic for all player inputs and stats
	 */
	
	private static final int MAX_LIVES = 3;
	
	private int score;
	private int lives;
	private String playerName;
	HighScoreController highScoreController;
	GameController gameController;
	Paddle paddle;
	
	public PlayerController() {
		this.lives = MAX_LIVES;
		playerName = "Test_Player";
		score = 0;
	}
	
	public PlayerController(Paddle paddle) {
		this();
		this.highScoreController = new HighScoreController();
		this.paddle = paddle;
	
	}
	public PlayerController(Paddle paddle, GameController gameController) {
		this(paddle);
	
	}
	
	
	/**
	 * handles the input for moving the paddle left or right
	 * @param keyCode
	 */
	public boolean handleKeyInput(KeyCode keyCode, double elapsedTime) {
		if (keyCode == KeyCode.RIGHT || keyCode == KeyCode.D) {
			paddle.moveHorizontally(true, elapsedTime);
			
		} else if (keyCode == KeyCode.LEFT || keyCode == KeyCode.A){
			paddle.moveHorizontally(false, elapsedTime);
		} 
		else if(keyCode == KeyCode.SPACE) {
			return true;
			
		}
		return false;
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
	 * called when the ball hits a brick. Adds brick value to the current score
	 * @param brickValue
	 */
	public void addBrickValueToScore(int brickValue) {
		score += brickValue;
	}
	
	/**
	 * called when the ball hits the boundary. player loses one life
	 */
	public void subtractLife() {
		if (lives > 0) {
			lives--;
		}
	}
	
	/**
	 * Stops the paddle
	 */
	public void stopPaddle() {
		paddle.stop();
	}
	
	/**
	 * Will animate the paddle moving across the scene
	 * 
	 * @param goRight
	 */
	public void paddleMovesRight(boolean goRight, double elapsedTime) {
		paddle.moveHorizontally(goRight, elapsedTime);
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
			paddle.stop();
		} else if (keyCode == KeyCode.LEFT || keyCode == KeyCode.A){
			paddle.stop();
		}
	}
}
