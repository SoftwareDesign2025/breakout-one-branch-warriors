package interfaces;

import game.gamecontroller.GameController;
import javafx.scene.Node;

import projectiles.Projectiles;


public interface Collidable {
	public boolean checkCollision(Projectiles projectile);
	public void handleCollision(Projectiles projectile, GameController gameController);
	public void manageCollision(GameController gameController);
	public Node getView();
}
