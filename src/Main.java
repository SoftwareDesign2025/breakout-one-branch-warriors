import BreakOutDefault.AnimationController;
import BreakOutDefault.GameController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

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
	private AnimationController myAnimation;
	
	private GameController gameController = new GameController();

	
	// Input: stage
	// Output: None
	// Purpose: begins the program using predefined constants such as screen
	// dimensions, background color, and time delay for each frame

	@Override
	public void start(Stage stage) {
		myAnimation = new AnimationController();
		// attach scene to the stage and display it
		myScene = setupScene(WIDTH, HEIGHT, BACKGROUND);
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
	private Scene setupScene(int WIDTH, int HEIGHT, Paint background) {
		Group root = myAnimation.createRootForAnimation(WIDTH, HEIGHT);
		
		// create a place to see the shapes
		Scene scene = new Scene(root, WIDTH, HEIGHT, background);
		// respond to input
		scene.setOnKeyReleased(e -> stopPaddle());
		scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));

		return scene;
	}

	// Input: elapsedTime
	// Output: None
	// Purpose: uses the defined step method from the animation controller to
	// determine the necessary time delay for each frame

	private void step(double elapsedTime) {
		myAnimation.step(elapsedTime);
	}

	// Input: KeyCode
	// Output: none
	// Purpose: determines what animation needs to be done depending on the key
	// pressed
	private void handleKeyInput(KeyCode code) {

		if (code == KeyCode.LEFT) {
			myAnimation.paddleMovesRight(false);
		} else if (code == KeyCode.RIGHT) {
			myAnimation.paddleMovesRight(true);

		}
		else if(code == KeyCode.F1) {
			
			myAnimation.setLevel(WIDTH,HEIGHT,1);
			
		}
		else if(code == KeyCode.F2) {
			myAnimation.setLevel(WIDTH,HEIGHT,2);
			
		}
		else if(code == KeyCode.F3) {
			myAnimation.setLevel(WIDTH,HEIGHT,3);
			
		}
	}

	// Input: none
	// Output: none
	// Purpose: stops the paddle from moving
	private void stopPaddle() {
		myAnimation.stopPaddle();
	}

	/**
	 * Start the program.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
