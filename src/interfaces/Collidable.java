package interfaces;

import Projectiles.Ball;
import game.GameController;

public interface Collidable {
	public boolean checkCollision(Ball ball);
	public void handleCollision(Ball ball, GameController gameController);
	public void manageCollision(GameController gameController);
}
