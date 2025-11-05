/**
 * @author Aidan Jimenez
 */
package layouts;

import java.util.ArrayList;
import java.util.List;

import entities.blocks.Player;
import entities.bugs.Bee;
import entities.bugs.Bug;
import entities.bugs.Butterfly;
import entities.bugs.EnemyShip;
import interfaces.Collidable;
import interfaces.IMoveable;
import javafx.scene.paint.Color;
import layouts.levels.GalagaLevels;

public class GalagaLayout extends Layout {

	protected static final int LAYOUT_TOP_MARGIN = 50;
	private static final int PADDING = 2;
	private GalagaLevels levelController = new GalagaLevels();
	private List<IMoveable> moveables = new ArrayList<>();
	private List<Bug> bugs = new ArrayList<>();
	private Player playerShip;

	public GalagaLayout(int screenHeight, int screenWidth, int level, Player playerShip) {
		super(screenHeight, screenWidth, level);
		this.playerShip = playerShip;
		this.levelController = new GalagaLevels();
		this.layoutItems = createLayout(level);
	}

	protected List<Collidable> createLayout(int level) {
		List<Collidable> newItems = new ArrayList<>();

		String[] currentMap = levelController.getLevel(level);

		// Calculating values for layout placement
		int numRows = currentMap.length;
		int maxCols = ITEM_SIZE / screenWidth;
		int layoutStartX = 0;
		int layoutStartY = LAYOUT_TOP_MARGIN;

		// Iterating through rows
		for (int row = 0; row < numRows; row++) {
			String rowString = currentMap[row];

			// Iterating through columns
			for (int col = 0; col < rowString.length(); col++) {
				char brickType = rowString.charAt(col);

				if (brickType == ' ') {
					continue;
				}

				int xPos = layoutStartX + (col * ITEM_SIZE * PADDING);
				int yPos = layoutStartY + (row * ITEM_SIZE);

				Bug bug = createItem(brickType, xPos, yPos);

				newItems.add(bug);
				moveables.add(bug);
				bugs.add(bug);
			}
		}

		return newItems;
	}

	protected Bug createItem(char itemType, int xPos, int yPos) {
		switch (itemType) {
		case 'S':
			return new EnemyShip(xPos, yPos, ITEM_SIZE, this.playerShip);
		case 'B':
			return new Bee(xPos, yPos, ITEM_SIZE, this.playerShip);
		case 'X':
			return new Butterfly(xPos, yPos, ITEM_SIZE);
		default:
			return new Butterfly(xPos, yPos, ITEM_SIZE);
		}
	}

	public List<Collidable> getCollidables() {
		return layoutItems;
	}

	public List<IMoveable> getMoveables() {
		return moveables;
	}

	public List<Bug> getBugs() {
		return bugs;
	}

	@Override
	protected Collidable createItem(char itemType, int xPos, int yPos, int points, int lives, double powerFactor,
			Color rectColor) {
		// TODO Auto-generated method stub
		return null;
	}

}
