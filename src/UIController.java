import javafx.scene.*;
import javafx.scene.text.Text;

public class UIController {
	private final int STARTING_LIVES = 3;
	private final int STARTING_SCORE = 0;
	private final int STARTING_HIGH_SCORE = 0;
	private final int STARTING_LEVEL_NUMBER = 1;
	
	//defines the amount that the height will be multiplied for each vertical level of UI
	private final double TOP_UI_HEIGHT_MULTIPLIER = 0.1;
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
	
	public void updateUI(int currentLives, int currentScore, int currentHighScore, int currentLevelNumber) {
		lives.setText("Lives: " + currentLives);
		score.setText("Score: " + currentScore);
		highScore.setText("High Score: " + currentHighScore);
		levelNumber.setText("Level: " + currentLevelNumber);
	}
	
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
	
	public void showWinMessage() {
		winMessage.setVisible(true);
	}
	
	public void showGameOverMessage() {
		gameOverMessage.setVisible(true);
	}
	
	public void hideWinMessage() {
		winMessage.setVisible(false);
	}
	
	public void hideGameOverMessage() {
		gameOverMessage.setVisible(false);
	}
	
}
