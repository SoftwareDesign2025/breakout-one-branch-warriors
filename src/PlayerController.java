import blocks.Paddle;
import javafx.scene.input.KeyCode;

public class PlayerController {
	
	/*
	 * This class contains logic for all player inputs and stats
	 */
	
	private static final int MAX_LIVES = 3;
	
	private int score;
	private int lives;
	private String playerName;
	HighScoreController highScoreController;
	Paddle paddle;
	
	
	public PlayerController(HighScoreController highScoreController, Paddle paddle) {
		this.highScoreController = highScoreController;
		this.paddle = paddle;
		lives = MAX_LIVES;
		playerName = "Player_1";
	}
	
	
	/**
	 * handles the input for moving the paddle left or right
	 * @param keyCode
	 */
	public void handleKeyInput(KeyCode keyCode) {
		if (keyCode == KeyCode.RIGHT || keyCode == KeyCode.D) {
			paddle.moveHorizontally(true);
		} else if (keyCode == KeyCode.LEFT || keyCode == KeyCode.A){
			paddle.moveHorizontally(false);
		} 
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
		lives--;
	}
	
	public int getLives() {
		return lives;
	}
	
	public int getScore() {
		return score;
	}
}
