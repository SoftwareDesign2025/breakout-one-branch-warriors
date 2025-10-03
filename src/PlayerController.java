import javafx.scene.input.KeyCode;

public class PlayerController {
	
	private static final int MAX_LIVES = 3;
	
	private int score;
	//HighScoreController highScoreController;
	//Paddle paddle;
	
	/*
	public PlayerController(HighScoreController highScoreController, Paddle paddle) {
		this.highScoreController = highScoreController;
		this.paddle = paddle;
	}
	*/
	
	public void handleKeyInput(KeyCode keyCode) {
		if (keyCode == KeyCode.RIGHT || keyCode == KeyCode.D) {
			//paddle.movesHorizontally(true)
		} else if (keyCode == KeyCode.LEFT || keyCode == KeyCode.A){
			//paddle.movesHorizontally(false)
		} 
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int newScore) {
		score = newScore;
	}
	
	/*
	private void setNewHighScore() {
		if (score > highScoreController.getHighScore()) {
			highScoreController.setHighScore(score);
		}
	}
	*/
	
}
