package game.gamecontroller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import entities.blocks.PlayerBlock;
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


	private List<Bug> movingBugs= new ArrayList<>();

	// TODO: Fix player ship by creating parent class for paddle and ship
	private PlayerBlock ship;

	private GalagaLayout itemLayout;

	
	private static int BUG_MOVING_LIMIT = 3;
	private static int BUG_MOVING_TIME = 5;
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
			List<Bullet> bulletsForRemoval = new ArrayList<>();
			
			// TODO: handle bug/player/boundary collision removing a life
			for (Bullet bullet : bullets) {

				if (bullet.getY() < 0) {
					animationController.removeFromRoot(bullet.getView());
					animationController.removeFromMoveables(bullet);
				}

				for (Collidable collidable : myCollidables) {
					if (collidable.checkCollision(bullet)) {
						collided.add(collidable);
						bulletsForRemoval.add(bullet);
						if(movingBugs.contains(collidable)) {
							movingBugs.remove(collidable);
						}
					}
				}

				for (int i = 0; i < collided.size(); i++) {
					if (i == 0) {
						collided.get(i).handleCollision(bullet, this);
					} else {
						collided.get(i).manageCollision(this);
					}
				}

			}


			bulletsForRemoval.forEach(bullet -> removeBullet(bullet));
			

			animationController.step(elapsedTime);

			uiController.updateUI(playerController.getLives(), playerController.getScore(),
					playerController.getHighScore(), level);

			if(BUG_MOVING_LIMIT >= movingBugs.size() && totalTime > BUG_MOVING_TIME) {
				chanceToMoveBugs();
			}

		}
	}
	
	private void chanceToMoveBugs() {
			resetTimer();
			List<Bug> bugs = itemLayout.getBugs();

			Collections.shuffle(bugs);

			for (int i = 0; i < BUG_MOVING_LIMIT; i++) {
			    Bug bug = bugs.get(i);
			    movingBugs.add(bugs.get(i));
			    bug.initializeMovement();
			    bugsMoving++;
			}
	}
	
	private void resetTimer() {
		totalTime = 0;
		
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
	}

	private void createBullet(double x) {
		Bullet bullet = new Bullet((int) x + 10, screenHeight - 125);
		bullets.add(bullet);
		moveables.add(bullet);
		animationController.addToMoveables(bullet);
		animationController.addToRoot(bullet.getView());
	}
	
	private void removeBullet(Bullet bullet) {
		bullets.remove(bullet);
		moveables.remove(bullet);
		animationController.removeFromMoveables(bullet);
		animationController.removeFromRoot(bullet.getView());
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
		resetTimer();
	}

	private void cleanBugs() {
		movingBugs = new ArrayList<>();
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
