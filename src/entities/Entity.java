/**
 * @author Aidan Jimenez
 */
package entities;


import interfaces.Collidable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class Entity implements Collidable {
	protected Group view; 
	protected Rectangle rect;   
	protected ImageView sprite;  

	public Entity(int xPosition, int yPosition, int width, int height) {
		rect = new Rectangle(width, height);

		view = new Group(rect);
		view.setLayoutX(xPosition);
		view.setLayoutY(yPosition);
	}
	
	public Entity(int xPosition, int yPosition, int width, int height, String image) {

		this(xPosition, yPosition, width, height);

		try {
			Image image_file = new Image(new FileInputStream(image));
			sprite = new ImageView(image_file);
			sprite.setFitWidth(width);
			sprite.setFitHeight(height);
			
			view.getChildren().add(sprite);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

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
