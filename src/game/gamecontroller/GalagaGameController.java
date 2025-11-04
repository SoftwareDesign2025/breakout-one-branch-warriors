package game.gamecontroller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import entities.blocks.Paddle;
import entities.blocks.PlayerShip;
import entities.bugs.Bug;
import game.PlayerController;
import game.UIController;
import interfaces.Collidable;
import javafx.scene.input.KeyCode;
import layouts.GalagaLayout;
import projectiles.Bullet;

public class GalagaGameController extends GameController {

	private List<Bullet> bullets = new ArrayList<>();

	// TODO: Fix player ship by creating parent class for paddle and ship
	private Paddle ship;

	private GalagaLayout itemLayout;

	
	private static int BUG_MOVING_LIMIT = 2;
	private int bugsMoving = 0;
	private double totalTime = 0;
	public GalagaGameController(int screenWidth, int screenHeight) {
		super(screenWidth, screenHeight);
	}

	@Override
	public void step(double elapsedTime) {
		if (!paused) {
			this.elapsedTime = elapsedTime;
			totalTime += elapsedTime;
			if (playerController.isPlayerDead()) {
				gameEnded();
			}

			if (itemLayout.itemsLeft() == 0) {
				progressLevel();
			}

			List<Collidable> collided = new ArrayList<>();
			
			// TODO: remove bullet after 1 collision
			// TODO: handle bug/player/boundary collision removing a life
			for (Bullet bullet : bullets) {
				for (Collidable collidable : myCollidables) {
					if (collidable.checkCollision(bullet)) {
						collided.add(collidable);
					}
				}
			}

			for (Bullet bullet : bullets) {
				if (bullet.getY() < 0) {
					animationController.removeFromRoot(bullet.getView());
					animationController.removeFromMoveables(bullet);
				}
				for (int i = 0; i < collided.size(); i++) {
					if (i == 0) {
						collided.get(i).handleCollision(bullet, this);
					} else {
						collided.get(i).manageCollision(this);
					}
				}
			}
			

			animationController.step(elapsedTime);

			uiController.updateUI(playerController.getLives(), playerController.getScore(),
					playerController.getHighScore(), level);

			//TODO: move after X time
			// manage moving numbers
			// if killed remove from moving
			if(BUG_MOVING_LIMIT != bugsMoving  && totalTime > 5) {
				chanceToMoveBugs();
			}

		}
	}
	
	private void chanceToMoveBugs() {
			List<Bug> bugs = itemLayout.getBugs();

			Collections.shuffle(bugs);

			Random random = new Random();

			int limit = BUG_MOVING_LIMIT - bugsMoving;

			for (int i = 0; i < BUG_MOVING_LIMIT; i++) {
			    Bug bug = bugs.get(i);
			    bug.initializeMovement();
			    bugsMoving++;
			}
	}

	@Override
	protected void createUI() {
		uiController = new UIController();
		ui = uiController.createGroupForUI(screenWidth, screenHeight);
		animation = animationController.createRootForAnimation(screenWidth, screenHeight);
		animationController.addToRoot(ui);
	}

	@Override
	protected void createPlayer() {
		ship = new PlayerShip(screenWidth / 2 - ITEM_SIZE, screenHeight - 100, ITEM_SIZE, ITEM_SIZE, screenWidth);
		playerController = new PlayerController(ship);
		animationController.addToRoot(ship.getView());
		myCollidables.add(ship);
	}

	private void createBullet(double x) {
		Bullet bullet = new Bullet((int) x + 10, screenHeight - 120);
		bullets.add(bullet);
		moveables.add(bullet);
		animationController.addToMoveables(bullet);
		animationController.addToRoot(bullet.getView());
	}

	@Override
	public void handleKeyInput(KeyCode code) {
		super.handleKeyInput(code);
		if (code == KeyCode.SPACE) {
			createBullet(ship.getX());
		}

	}

	@Override
	protected void initializeGame() {
		level = STARTING_LEVEL_NUMBER;

		createUI();
		createLevel();
		createPlayer();

	}

	@Override
	protected void createLevel() {
		itemLayout = new GalagaLayout(screenWidth, screenHeight, level);
		List<Collidable> bugs = itemLayout.getCollidables();

		bugs.forEach(bug -> myCollidables.add(bug));
		bugs.forEach(bug -> animationController.addToRoot(bug.getView()));
		itemLayout.getMoveables().forEach(bug -> animationController.addToMoveables(bug));
	}

	@Override
	protected void constructLevel() {
		cleanLevel();

		createLevel();
	}

	@Override
	protected void cleanLevel() {
		cleanBugs();
		cleanBullets();
	}

	private void cleanBugs() {
		List<Collidable> bugs = itemLayout.getCollidables();
		int listSize = itemLayout.getCollidables().size();
		for (int i = 0; i < listSize; i++) {
			animationController.removeFromRoot(bugs.get(i).getView());
			myCollidables.remove(bugs.get(i));
		}
	}

	private void cleanBullets() {
		bullets.forEach(bullet -> {
			animationController.removeFromRoot(bullet.getView());
			animationController.removeFromMoveables(bullet);
			moveables.remove(bullet);
		});

		bullets = new ArrayList<>();
	}

	@Override
	public void breakItem(Collidable bug) {
		myCollidables.remove(bug);
		itemLayout.handleRemoval(bug);
		animationController.removeFromRoot(bug.getView());
	}

}
