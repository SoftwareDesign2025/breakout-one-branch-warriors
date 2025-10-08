import javafx.scene.input.KeyCode;

public class PlayerController {
	
	/*
	 * This class contains logic for all player inputs and stats
	 */
	
	private static final int MAX_LIVES = 3;
	
	private int score;
	private int lives;
	private String playerName;
	//HighScoreController highScoreController;
	//Paddle paddle;
	
	/*
	public PlayerController(HighScoreController highScoreController, Paddle paddle) {
		this.highScoreController = highScoreController;
		this.paddle = paddle;
	}
	*/
	
	/**
	 * handles the input for moving the paddle left or right
	 * @param keyCode
	 */
	public void handleKeyInput(KeyCode keyCode) {
		if (keyCode == KeyCode.RIGHT || keyCode == KeyCode.D) {
			//paddle.movesHorizontally(true)
		} else if (keyCode == KeyCode.LEFT || keyCode == KeyCode.A){
			//paddle.movesHorizontally(false)
		} 
	}
	
	/**
	 * returns true if the player has no remaining lives and false if they have at least 1 life
	 * @return
	 */
	public boolean isPlayerDead() {
		if (lives <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * if the current score is higher than the current highscore, sets the new highscore
	 * @return
	 */
	/*
	private void addScoreToHighScores() {
		int[] highScores = highScoreController.getHighScores();
		int numHighScores = highScores.length();
	
	
		if (score > highScores[numHighScores - 1]) {
			highScoreController.addToHighScores(score, playerName);
		}
	}
	*/
	
	public int getLives() {
		return lives;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int newScore) {
		score = newScore;
	}
	
	public void setLives(int newLives) {
		lives = newLives;
	}
	
}
