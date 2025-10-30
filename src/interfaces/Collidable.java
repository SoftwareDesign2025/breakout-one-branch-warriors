package interfaces;

import game.GameController;
import Ball.Ball;

public interface Collidable {
	public void handleCollision(Ball ball, GameController gameController);
}
