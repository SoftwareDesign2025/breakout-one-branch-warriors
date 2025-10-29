package interfaces;

import Ball.Ball;
import Testing.GameController;

public interface Collidable {
	public void handleCollision(Ball ball, GameController gameController);
}
