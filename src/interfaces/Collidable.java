package interfaces;

import game.GameController;
import projectiles.Ball;
import projectiles.Projectiles;

public interface Collidable {
	public boolean checkCollision(Projectiles ball);
	public void handleCollision(Projectiles ball, GameController gameController);
	public void manageCollision(GameController gameController);
}
