package interfaces;

import Projectiles.Ball;
import game.GameController;

public interface Collidable {
	public void handleCollision(Ball ball, GameController gameController);
}
