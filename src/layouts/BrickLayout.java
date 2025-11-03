/**
 * @author Aidan Jimenez
 */
package layouts;

import java.util.ArrayList;
import java.util.List;

import entities.blocks.Brick;
import entities.blocks.DoubleDamageBrick;
import entities.blocks.UnbreakableBrick;
import interfaces.Collidable;
import javafx.scene.paint.Color;
import layouts.levels.BreakoutLevels;

public class BrickLayout extends Layout {

	private static final int BRICK_WIDTH = ITEM_SIZE * 2;
	private static final int BRICK_HEIGHT = ITEM_SIZE / 2;

	private int unbreakableBlockCount = 0;
	
	private BreakoutLevels levelController = new BreakoutLevels();

	public BrickLayout(int screenHeight, int screenWidth, int level) {
		super(screenHeight, screenWidth, level);
		this.levelController = new BreakoutLevels();
		this.layoutItems = createLayout(level);
	}

	protected List<Collidable> createLayout(int level) {
		List<Collidable> newItems = new ArrayList<>();

		String[] currentMap = levelController.getLevel(level);

		// Calculating values for layout placement
		int numRows = currentMap.length;
		int maxCols = BRICK_WIDTH / screenWidth;
		int layoutStartX = 0;
		int layoutStartY = LAYOUT_TOP_MARGIN;

		// Iterating through rows
		for (int row = 0; row < numRows; row++) {
			String rowString = currentMap[row];

			// Allows for calculation of the bottom row
			int reversedRow = numRows - 1 - row;

			float currentHue = 180f + (level * 10f) + (reversedRow * 15f);
			int lives = 1 + reversedRow;
			int points = 5 * (reversedRow + 1);
			double powerFactor = 1.025 + (reversedRow * 0.010);

			// Iterating through columns
			for (int col = 0; col < rowString.length(); col++) {
				char brickType = rowString.charAt(col);

				if (brickType == ' ') {
					continue;
				}

				int xPos = layoutStartX + (col * BRICK_WIDTH);
				int yPos = layoutStartY + (row * BRICK_HEIGHT);

				Color rectColor = Color.hsb(currentHue, 1.0f, 1.0f);
				Collidable brick = createItem(brickType, xPos, yPos, points, lives, powerFactor, rectColor);

				newItems.add(brick);
			}
		}

		return newItems;
	}

	protected Collidable createItem(char itemType, int xPos, int yPos, int points, int lives, double powerFactor,
			Color rectColor) {
		switch (itemType) {
		case 'U':
			unbreakableBlockCount++;
			return new UnbreakableBrick(xPos, yPos, BRICK_WIDTH, BRICK_HEIGHT, powerFactor);
		case 'D':
			return new DoubleDamageBrick(xPos, yPos, BRICK_WIDTH, BRICK_HEIGHT, powerFactor, points, lives, rectColor);
		case 'X':
			return new Brick(xPos, yPos, BRICK_WIDTH, BRICK_HEIGHT, powerFactor, points, lives, rectColor);
		default:
			return new Brick(xPos, yPos, BRICK_WIDTH, BRICK_HEIGHT, powerFactor, points, lives, rectColor);
		}
	}

	public List<Collidable> getCollidables() {
		return layoutItems;
	}

	public int itemsLeft() {
		return layoutItems.size() - unbreakableBlockCount;

	}

	public void removeBrick(Collidable brick) {
		layoutItems.remove(brick);
	}
}
