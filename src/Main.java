import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import game.GameController;
import javafx.scene.input.KeyCode;

/**
 * @Author OneBranchWarriors
 * 
 * 
 *         This class contains the code to begin the program and define the
 *         Scene
 * 
 */
public class Main extends Application {

	public static final int HEIGHT = 600;
	public static final int WIDTH = 900;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final String TITLE = "OBW Breakout";
	public static final Paint BACKGROUND = Color.AZURE;

	private Scene myScene;

	// This class will handle the logic that is specific to this application.
//	private AnimationController myAnimation;
	
	private GameController gameController; 

	
	// Input: stage
	// Output: None
	// Purpose: begins the program using predefined constants such as screen
	// dimensions, background color, and time delay for each frame

	@Override
	public void start(Stage stage) {
		gameController = new GameController(WIDTH, HEIGHT);
//		myAnimation = new AnimationController();
		// attach scene to the stage and display it
		myScene = setupScene();
		stage.setScene(myScene);
		stage.setTitle(TITLE);
		stage.show();
		// attach "game loop" to timeline to play it (basically just calling step()
		// method repeatedly forever)
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
		
	}

	// Input: screen width, screen height, background color
	// Output: Scene
	// Purpose: creates the "Scene" including what shapes will be drawn and their
	// starting properties
	private Scene setupScene() {
		Group root = gameController.getAnimationRoot();
		
		// create a place to see the shapes
		Scene scene = new Scene(root, WIDTH, HEIGHT, BACKGROUND);
		// respond to input
		scene.setOnKeyReleased(e -> gameController.handleKeyRelease(e.getCode()));
		scene.setOnKeyPressed(e -> gameController.handleKeyInput(e.getCode()));

		return scene;
	}

	// Input: elapsedTime
	// Output: None
	// Purpose: uses the defined step method from the animation controller to
	// determine the necessary time delay for each frame

	private void step(double elapsedTime) {
		gameController.step(elapsedTime);
	}

	/**
	 * Start the program.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
