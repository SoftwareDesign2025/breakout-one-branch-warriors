package interfaces;

import game.GameController;
import projectiles.Ball;

public interface Collidable {
	public boolean checkCollision(Ball ball);
	public void handleCollision(Ball ball, GameController gameController);
	public void manageCollision(GameController gameController);
}
