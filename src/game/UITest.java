//Author: Carter Puckett
package game;

import javafx.application.Application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;


//This Class is strictly used for testing

public class UITest extends Application{
	
	public static final int HEIGHT = 600;
	public static final int WIDTH = 400;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final String TITLE = "OBW Breakout UI Test";
    public static final Paint BACKGROUND = Color.AZURE;
    
    private Scene uiScene;
    private UIController uiController;
    private PlayerController player;

	public void start(Stage stage) {
		uiController = new UIController();
		player = new PlayerController();
		uiScene = setupScene(WIDTH, HEIGHT, BACKGROUND);
		
		stage.setScene(uiScene);
		stage.setTitle(TITLE);
		stage.show();
	}
	
	private Scene setupScene(int width, int height, Paint background) {
		Group root = uiController.createGroupForUI(width, height);
		Scene scene = new Scene(root, width, height, background);
		
		scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		
		return scene;
	}
	
	public void handleKeyInput(KeyCode key) {
		if (key == KeyCode.L) {
			player.subtractLife();
			uiController.updateUI(player.getLives(), player.getScore(), 0, 1);
		} else if (key == KeyCode.S) {
			player.addBrickValueToScore(10);
			uiController.updateUI(player.getLives(), player.getScore(), 0, 1);
		}
	}
	
	public static void main(String[] args) {
		launch(args);	
	}

}
