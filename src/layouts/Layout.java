/**
 * @author Aidan Jimenez
 */
package layouts;

import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import interfaces.Collidable;
import javafx.scene.paint.Color;
import layouts.levels.Level;

public abstract class Layout {
	protected static final int LAYOUT_TOP_MARGIN = 100;
	protected static final int ITEM_SIZE = 50;

	protected int screenHeight;
	protected int screenWidth;
	protected int level = 1;
	protected List<Collidable> layoutItems = new ArrayList<>();
	protected Level levelController;

	public Layout(int screenHeight, int screenWidth, int level) {
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.level =  level;
	}
	
	protected abstract List<Collidable> createLayout(int level); 

	// TODO: Find a way to decouple the power factor and rectColor
	protected Collidable createItem(char itemType, int xPos, int yPos,int points, int lives, double powerFactor, Color rectColor) {
		return null;
	}

	public void handleRemoval(Collidable collidable) {
		layoutItems.remove(collidable);
	}

	public List<Collidable> getCollidables() {
		return layoutItems;
	}

	public int itemsLeft() {
		return layoutItems.size();
	}
	

}
