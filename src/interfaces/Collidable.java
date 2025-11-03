package interfaces;

import game.GameController;
import projectiles.Ball;
import javafx.scene.Node;

public interface Collidable {
	public boolean checkCollision(Ball ball);
	public void handleCollision(Ball ball, GameController gameController);
	public void manageCollision(GameController gameController);
	public Node getView();
}
