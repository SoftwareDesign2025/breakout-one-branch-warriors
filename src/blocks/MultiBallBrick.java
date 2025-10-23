package blocks;

import javafx.scene.paint.Color;

public class MultiBallBrick extends Brick{
	
	
	public MultiBallBrick (int xPosition, int yPosition, int width, int height) {
		super(xPosition, yPosition, width, height);
		this.rect.setFill(Color.RED);
	}
}
