package interfaces;

import Ball.Ball;
import BreakOutDefault.GameController;

public interface Collidable {
	public void handleCollision(Ball ball, GameController gameController);
}
