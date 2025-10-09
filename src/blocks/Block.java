/**
 * @author Aidan Jimenez
 */
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

	// Can be used to make curved corners
	private static final int CORNER_RADIUS = 10;

	/**
	 * Block constructor creating a basic block
	 * @param xPostition
	 * @param yPosition
	 * @param width
	 * @param height 
	 */
	public Block(int xPosition, int yPosition, int width, int height) {
		rect = new Rectangle(width, height);

		view = new Group(rect);
		view.setLayoutX(xPosition);
		view.setLayoutY(yPosition);
	}

	/**
	 * Block constructor creating a block with a color fill
	 * @param Color
	 * @param xPostition
	 * @param yPosition
	 * @param width
	 * @param height 
	 */
	public Block(Color color, int xPosition, int yPosition, int width, int height) {
		rect = new Rectangle(width, height);
		rect.setFill(color);
		view = new Group(rect);
		view.setLayoutX(xPosition);
		view.setLayoutY(yPosition);
	}

	/**
	 * Block constructor creating a block with an image an collision box 
	 * @param xPostition
	 * @param yPosition
	 * @param width
	 * @param height 
	 * @param image 
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

	/**
	 * Sets the fill color of the block with a black stroke
	 * @param color
	 */
	public void setFill(Paint color) {
		rect.setFill(color);
		rect.setStroke(Color.BLACK);
	}

	/**
	 * Provides the view that can be used for things such as collision 
	 * @return Node
	 */
	public Node getView() {
		return view;
	}

	/**
	 * Provides only the collision block since providing the whole view could cause issues
	 * @return Shape 
	 */
	public Shape getCollisionBox() {
		return rect;
	}

	/**
	 * Getter for X position
	 * @return double
	 */
	public double getX() {
		return view.getLayoutX();
	}

	/**
	 * Getter for Y position
	 * @return double
	 */
	public double getY() {
		return view.getLayoutY();
	}

	/**
	 * Setter for X position
	 * @param x
	 */
	public void setX(double x) {
		view.setLayoutX(x);
	}

	/**
	 * Setter for Y position
	 * @param y
	 */
	public void setY(double y) {
		view.setLayoutY(y);
	}
}
