/**
 * @author Aidan Jimenez
 */
package game;

import entities.blocks.Paddle;

import interfaces.Collidable;
import entities.Entity;
import entities.blocks.Boundary;
import entities.blocks.Brick;
import interfaces.IMoveable;

import java.util.List;

import Projectiles.Ball;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Node;
import layouts.BrickLayout;

public class AnimationController {

	public static final int MOVER_SPEED = 15;
	public static final int NUM_BALLS = 1;
	public static final int BLOCK_SIZE = 50;

	private List<IMoveable> moveables = new ArrayList<>();
	private List<Ball> myBalls = new ArrayList<>();
	private List<Brick> myBlocks = new ArrayList<>();

	private Paddle paddle;
	private PlayerController playerController;
	private BrickLayout brickLayout;
	private Group root;

	private int width;
	private int height;

	private boolean gameEnded = false;

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



	public void stopAnimation() {
		stopBalls();
		stopPaddle();
	}

	private void stopBalls() {
//		for (Ball ball : myBalls) {
//			ball.stop();
//		}
	}

	public void stopPaddle() {
		paddle.stop();
	}
}
