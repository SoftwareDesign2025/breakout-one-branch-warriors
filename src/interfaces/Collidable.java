package interfaces;

import game.GameController;
import projectiles.Ball;

public interface Collidable {
	public void handleCollision(Ball ball, GameController gameController);
}
