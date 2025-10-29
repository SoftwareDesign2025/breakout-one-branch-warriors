/**
 * @author Aidan Jimenez
 */
package layouts;

import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import entities.blocks.Brick;
import entities.blocks.DoubleDamageBrick;
import entities.blocks.UnbreakableBrick;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class BrickLayout extends Layout{
	private static final String[] LEVEL_1_MAP = {
		    "XXXXXXXXX",
//		    "XXXXXXXXX",
//		    "XXXXXXXXX",
//		    "XXXXXXXXX",
//		    "XXXXXXXXX",
		};

		private static final String[] LEVEL_2_MAP = {
		    "  XXXXX  ",
		    "  X   X  ",
		    "  X D X  ",
		    "  X X X  ",
		    "  X X X  ",
		    "  X X X  ",
		    "  X X X  ",
		    "  XXX X  ",
		};

		private static final String[] LEVEL_3_MAP = {
		    "XXXXXXXXX",
		    " XXXXXXX ",
		    "  XXXXX  ",
		    "    X    ",
		    "  XXXXX  ",
		    " XXXXXXX ",
		    "XXXXXXXXX",
		};

	private static final int BRICK_WIDTH = ITEM_SIZE * 2;
	private static final int BRICK_HEIGHT = ITEM_SIZE / 2 ;

	private List<Brick> myBlocks = new ArrayList<>();

	int unbreakableBlockCount = 0;

	public BrickLayout(int screenHeight, int screenWidth, int level) {
		super(screenHeight, screenWidth, level);
		myBlocks = createLayout(level);
	}
	
	
	protected List<Brick> createLayout(int level) {
	    List<Brick> newBlocks = new ArrayList<>();

	    String[] currentMap = getLevelTemplate(level);

	    // Calculating values for layout placement
	    int numRows = currentMap.length;
	    int maxCols = BRICK_WIDTH / screenWidth;
	    int layoutStartX = 0;
	    int layoutStartY = LAYOUT_TOP_MARGIN;


	    //Iterating through rows
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
	            Brick brick = createBrick(brickType, xPos, yPos, powerFactor, points, lives, rectColor);
	            
	            newBlocks.add(brick);
	        }
	    }

	    return newBlocks;
	}
	
	// TODO: figure out how we can make maps work
	protected String[] getLevelTemplate(int level) {
	    String[] currentMap;
	    switch (level) {
	        case 1: currentMap = LEVEL_1_MAP; break;
	        case 2: currentMap = LEVEL_2_MAP; break;
	        case 3: currentMap = LEVEL_3_MAP; break;
	        default: currentMap = LEVEL_1_MAP;
	    }
	    return currentMap;
	}
	
	private Brick createBrick(char brickType, int xPos, int yPos, double powerFactor, int points, int lives, Color rectColor) {
	    
	    switch (brickType) {
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

	public List<Brick> getMyBlocks() {
		return myBlocks;
	}


	public int blocksLeft() {
		return myBlocks.size() - unbreakableBlockCount;
	}
	
	private Node checkBrickHealth(Brick brick) {
		if (!brick.isBroken()) {
			brick.removeDurability();
		} else {
			//root.getChildren().remove(brick.getView());
			myBlocks.remove(brick);
		}
		
		return brick.getView();

	}
	
	public void removeBrick(Brick brick) {
		myBlocks.remove(brick);
	}

}
