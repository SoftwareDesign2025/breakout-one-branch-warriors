import game.gamecontroller.GameController;
import game.gamecontroller.BreakoutGameController;
import game.gamecontroller.GalagaGameController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

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
    public static final String TITLE = "OBW"; 
    private Paint background = Color.AZURE;

    private Scene myScene;
    private Stage myStage; 
    private	GameController currentGameController;

    private BreakoutGameController breakoutController;
    private GalagaGameController galagaController;

    // Input: stage
    // Output: None
    // Purpose: begins the program by showing the game selection screen
    @Override
    public void start(Stage stage) {
        myStage = stage; // Store stage for scene changes
        myStage.setTitle(TITLE);
        
        myScene = setupSelectionScene(); // Start with the selection screen
        myStage.setScene(myScene);
        myStage.show();
    }

    // Input: None
    // Output: Scene
    // Purpose: creates the initial selection screen with a button.
    private Scene setupSelectionScene() {
        VBox root = new VBox(20); 
        root.setStyle("-fx-alignment: center;"); 

        Button galagaButton = new Button("Play Galaga");
        galagaButton.setOnAction(e -> startGame("Galaga"));

        Button breakoutButton = new Button("Play Breakout");
        breakoutButton.setOnAction(e -> startGame("Breakout"));

        root.getChildren().addAll(galagaButton, breakoutButton);

        Scene scene = new Scene(root, WIDTH, HEIGHT, background);
        return scene;
    }

    // Input: game choice
    // Output: None
    // Purpose: initializes the chosen game and switches the scene.
    private void startGame(String gameChoice) {
        if (gameChoice.equals("Galaga")) {
            galagaController = new GalagaGameController(WIDTH, HEIGHT);
            currentGameController = galagaController;
            background = Color.BLACK;
            myStage.setTitle("Galaga - " + TITLE);
        } else {
            breakoutController = new BreakoutGameController(WIDTH, HEIGHT);
            currentGameController = breakoutController;
            myStage.setTitle("Breakout - " + TITLE);
        }

        // Setup the game scene and start the animation loop
        myScene = setupGameScene(currentGameController);
        myStage.setScene(myScene);
        startAnimationLoop();
    }

    // Input: GameController
    // Output: Scene
    // Purpose: creates the game's Scene including shapes and input handlers
    private Scene setupGameScene(GameController controller) {
        Group root = controller.getAnimationRoot();
        
        Scene scene = new Scene(root, WIDTH, HEIGHT, background);

        scene.setOnKeyReleased(e -> controller.handleKeyRelease(e.getCode()));
        scene.setOnKeyPressed(e -> controller.handleKeyInput(e.getCode()));

        return scene;
    }

    // Input: None
    // Output: None
    // Purpose: sets up the game loop using the Timeline
    private void startAnimationLoop() {
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    // Input: elapsedTime
    // Output: None
    // Purpose: uses the defined step method from the current game controller
    private void step(double elapsedTime) {
        if (currentGameController != null) {
            currentGameController.step(elapsedTime);
        }
    }

    /**
     * Start the program.
     */
    public static void main(String[] args) {
        launch(args);
    }
}