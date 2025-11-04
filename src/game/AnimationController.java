/**
 * @author Aidan Jimenez, Carter Puckett, Benji Altman
 */
package game;

import interfaces.IMoveable;

import java.util.List;
import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Node;

public class AnimationController {

	public static final int MOVER_SPEED = 15;
	public static final int NUM_BALLS = 1;
	public static final int BLOCK_SIZE = 50;

	private List<IMoveable> moveables = new ArrayList<>();

	private Group root;

	private int width;
	private int height;


	/**
	 * Creates the scene required for the game to start adding all assets
	 * 
	 * @return Group
	 */
	public Group createRootForAnimation(int windowWidth, int windowHeight) {

		width = windowWidth;
		height = windowHeight;

		root = new Group();

		return root;
	}

	public void addToRoot(Node node) {
		root.getChildren().add(node);
	}

	public void removeFromRoot(Node node) {
		root.getChildren().remove(node);
	}

	public void addToMoveables(IMoveable moveable) {
		moveables.add(moveable);
	}

	public void removeFromMoveables(IMoveable moveable) {
		moveables.remove(moveable);
	}

	/**
	 * Makes a new step in the animation checking for collisions as objects in the
	 * scene move around
	 * 
	 * @param elapsedTime
	 */
	public void step(double elapsedTime) {
		for (IMoveable moveable : moveables) {
			moveable.move(elapsedTime);
		}
	}
}
