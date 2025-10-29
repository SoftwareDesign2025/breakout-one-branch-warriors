/**
 * @author Aidan Jimenez
 */
package entities.blocks;

import Ball.Ball;
import Testing.GameController;
import entities.Entity;
import interfaces.Collidable;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Block extends Entity implements Collidable{
	protected ImageView sprite;  

	// Can be used to make curved corners
	protected static final int CORNER_RADIUS = 10;

	/**
	 * Block constructor creating a basic block
	 * @param xPostition
	 * @param yPosition
	 * @param width
	 * @param height 
	 */
	public Block(int xPosition, int yPosition, int width, int height) {
		super(xPosition,yPosition, width, height);
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
		super(xPosition,yPosition, width, height);
		rect.setFill(color);
	}

	/**
	 * Block constructor creating a block with an image an collision box 
	 * @param xPostition
	 * @param yPosition
	 * @param width
	 * @param height 
	 * @param image 
	 */
	public Block(int xPosition, int yPosition, int width, int height, String image) {
		super(xPosition,yPosition, width, height, image);
		rect.setFill(Color.TRANSPARENT);
	}

	/**
	 * Sets the fill color of the block with a black stroke
	 * @param color
	 */
	public void setFill(Paint color) {
		rect.setFill(color);
		//rect.setStroke(Color.BLACK);
	}

	@Override
	public void handleCollision(Ball ball, GameController gameController) {
		// TODO Auto-generated method stub
		
	}

}
