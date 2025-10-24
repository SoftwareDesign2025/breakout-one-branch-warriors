import javafx.scene.*;
import javafx.scene.text.Text;

public class UIController {
	private final int STARTING_LIVES = 3;
	private final int STARTING_SCORE = 0;
	private final int STARTING_HIGH_SCORE = 0;
	private final int STARTING_LEVEL_NUMBER = 1;
	
	//defines the amount that the height will be multiplied for each vertical level of UI
	private final double TOP_UI_HEIGHT_MULTIPLIER = 0.05;
	private final double MID_UI_HEIGHT_MULTIPLIER = 0.5;
	private final double BOTTOM_UI_HEIGHT_MULTIPLIER = 0.9;
	
	private int width;
	private int height;
	
	private Group UIRoot;
	private Text lives;
	private Text score;
	private Text highScore;
	private Text winMessage;
	private Text gameOverMessage;
	private Text levelNumber;
	
	public UIController() {
		
	}
	
	/**
	 * creates and returns the UI as a Group object
	 * @param windowWidth
	 * @param windowHeight
	 * @return the UI
	 */
	public Group createGroupForUI(int windowWidth, int windowHeight) {
		width = windowWidth;
		height = windowHeight;
		
		UIRoot = new Group();
		
		initializeUI();
		
		UIRoot.getChildren().add(lives);
		UIRoot.getChildren().add(score);
		UIRoot.getChildren().add(highScore);
		UIRoot.getChildren().add(winMessage);
		UIRoot.getChildren().add(gameOverMessage);
		UIRoot.getChildren().add(levelNumber);
		
		return UIRoot;
	}
	
	/**
	 * will be called each time stats displayed in the UI change. updates the UI using the new stats
	 * @param currentLives
	 * @param currentScore
	 * @param currentHighScore
	 * @param currentLevelNumber
	 */
	public void updateUI(int currentLives, int currentScore, int currentHighScore, int currentLevelNumber) {
		lives.setText("Lives: " + currentLives);
		score.setText("Score: " + currentScore);
		highScore.setText("High Score: " + currentHighScore);
		levelNumber.setText("Level: " + currentLevelNumber);
	}
	
	/**
	 * initializes UI with default display stats
	 */
	private void initializeUI() {
		lives = new Text(width * 0.1, height * TOP_UI_HEIGHT_MULTIPLIER, "Lives: " + STARTING_LIVES);
		score = new Text(width * 0.3, height * TOP_UI_HEIGHT_MULTIPLIER, "Score: " + STARTING_SCORE);
		highScore = new Text(width * 0.5, height * TOP_UI_HEIGHT_MULTIPLIER, "High Score: " + STARTING_HIGH_SCORE);
		levelNumber = new Text(width * 0.8, height * TOP_UI_HEIGHT_MULTIPLIER, "Level: " + STARTING_LEVEL_NUMBER);
		winMessage = new Text(width * 0.5, height * MID_UI_HEIGHT_MULTIPLIER, "YOU WIN!");
		gameOverMessage = new Text(width * 0.5, height * MID_UI_HEIGHT_MULTIPLIER, "GAME OVER");
		
		hideWinMessage();
		hideGameOverMessage();
	}
	
	/**
	 * shows the win message when game is won
	 */
	public void showWinMessage() {
		winMessage.setVisible(true);
	}
	
	/**
	 * shows game over message when game is lost
	 */
	public void showGameOverMessage() {
		gameOverMessage.setVisible(true);
	}
	
	/**
	 * hides win message at the beginning of each level
	 */
	public void hideWinMessage() {
		winMessage.setVisible(false);
	}
	
	/**
	 * hides the game over message at the beginning of each level
	 */
	public void hideGameOverMessage() {
		gameOverMessage.setVisible(false);
	}
	
}
