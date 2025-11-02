package interfaces;

import game.GameController;
import Ball.Ball;

public interface Collidable {
	public boolean checkCollision(Ball ball);
	public void handleCollision(Ball ball, GameController gameController);
	public void manageCollision(GameController gameController);
}
