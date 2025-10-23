package blocks;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class BrickLayout {

	private static final int BLOCK_SIZE = 50;
	private static final int NUM_BRICKS = 54;
	private static final int UNBREAKABLE_CHANCE = 2;

	private List<Brick> myBlocks = new ArrayList<>();

	int screenHeight;
	int screenWidth;
	int unbreakableBlockCount = 0;

	boolean isPreviousBlocker = false;

	public BrickLayout(int screenHeight, int screenWidth) {
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		myBlocks = createBrickLayout();
	}

	/**
	 * Generates the brick layout for the game
	 * 
	 * @return List<Brick>
	 */
	private List<Brick> createBrickLayout() {
		float currentHue = 180f;
		final float saturation = 1.0f;
		final float brightness = 1.0f;
		int lives = 1;
		double powerFactor = 1.025;
		int points = 5;
		List<Brick> myBlocks = new ArrayList<>();

		int xPos = 0;
		int yPos = screenHeight / 2 - 100;

		for (int i = 0; i < NUM_BRICKS; i++) {
			if (xPos >= screenWidth) {
				xPos = 0;
				yPos -= BLOCK_SIZE / 2;
				currentHue += 15;
				powerFactor += 0.010;
				points += 5;
				lives += 1;
			}
			if (yPos < BLOCK_SIZE) {
				break;
			}
			Color rectColor = Color.hsb(currentHue, saturation, brightness);

			Brick brick = createBrick(xPos, yPos, powerFactor, points, lives, rectColor);

			myBlocks.add(brick);
			xPos += BLOCK_SIZE * 2;
		}

		return myBlocks;
	}

	public List<Brick> getMyBlocks() {
		return myBlocks;
	}

	/**
	 * Generates the brick layout for the game
	 * 
	 * @return List<Brick>
	 */
	private Brick createBrick(int xPos, int yPos, double powerFactor, int points, int lives, Color rectColor) {
		Brick brick;
		int randomNum = (int) (Math.random() * 100);

		if (randomNum <= UNBREAKABLE_CHANCE && !isPreviousBlocker) {
			brick = new UnbreakableBrick(xPos, yPos, BLOCK_SIZE * 2, BLOCK_SIZE / 2, powerFactor);
			isPreviousBlocker = true;
			unbreakableBlockCount++;
		} else {
			brick = new Brick(xPos, yPos, BLOCK_SIZE * 2, BLOCK_SIZE / 2, powerFactor, points, lives, rectColor);
			isPreviousBlocker = false;
		}

		return brick;
	}

	public int blocksLeft() {
		return myBlocks.size() - unbreakableBlockCount;
	}
}
