/**
 * @author Aidan Jimenez
 */
package layouts;

import java.util.List;

import entities.Entity;
import entities.blocks.Brick;

public abstract class Layout {
	protected static final int LAYOUT_TOP_MARGIN = 100;
	protected static final int ITEM_SIZE = 50;

	protected int screenHeight;
	protected int screenWidth;
	protected int level = 1;

	public Layout(int screenHeight, int screenWidth, int level) {
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.level =  level;
	}
	
	protected abstract String[] getLevelTemplate(int level);
	protected abstract List<Brick> createLayout(int level); 

}
