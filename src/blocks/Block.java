package blocks;

import javafx.scene.Group; 
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Block {
	protected Rectangle rect;   
	protected ImageView sprite;  
	protected Group view; 

	private static final int CORNER_RADIUS = 10;

	/*
	 * Block constructor creating a basic block
	 */
	public Block(int xPosition, int yPosition, int width, int height) {
		rect = new Rectangle(width, height);

		view = new Group(rect);
		view.setLayoutX(xPosition);
		view.setLayoutY(yPosition);
	}

	/*
	 * Block constructor creating a block with a color fill
	 */
	public Block(Color color, int xPosition, int yPosition, int width, int height) {
		rect = new Rectangle(width, height);
		rect.setFill(color);
		// Setting a curve to the corners
		rect.setArcHeight(CORNER_RADIUS);
		rect.setArcWidth(CORNER_RADIUS);
		view = new Group(rect);
		view.setLayoutX(xPosition);
		view.setLayoutY(yPosition);
	}

	/*
	 * Block constructor creating a block with an image an collision box 
	 */
	public Block(int xPosition, int yPosition, int width, int height, Image image) {
		rect = new Rectangle(width, height);
		rect.setFill(Color.TRANSPARENT);

		sprite = new ImageView(image);
		sprite.setFitWidth(width);
		sprite.setFitHeight(height);

		view = new Group(sprite, rect);
		view.setLayoutX(xPosition);
		view.setLayoutY(yPosition);
	}

	/*
	 * Sets the fill color of the block with a black stroke
	 */
	public void setFill(Paint color) {
		rect.setFill(color);
		rect.setStroke(Color.BLACK);
	}

	/*
	 * Provides the view that can be used for things such as collision 
	 */
	public Node getView() {
		return view;
	}

	/*
	 * Provides only the collision block since providing the whole view could cause issues
	 */
	public Shape getCollisionBox() {
		return rect;
	}

	public double getX() {
		return view.getLayoutX();
	}

	public double getY() {
		return view.getLayoutY();
	}

	public void setX(double x) {
		view.setLayoutX(x);
	}

	public void setY(double y) {
		view.setLayoutY(y);
	}
}
