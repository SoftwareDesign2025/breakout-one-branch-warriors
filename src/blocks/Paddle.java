package blocks;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Paddle extends Block {
    private Point2D velocity;
    private static int MOVE_SPEED = 15;
	private static final double MAX_VELOCITY = 45;
	private static final double MIN_VELOCITY = -MAX_VELOCITY;
	private boolean moved = false;
	private int boardWidth;
	private int paddleWidth;
	public enum MoveState { STOPPED, LEFT, RIGHT } 
	MoveState state;


	/*
	 * Paddle constructor using a color fill
	 */
	public Paddle(Color color, int xPosition, int yPosition, int width, int height, int boardWidth) {
		super(color, xPosition, yPosition, width, height );
		this.boardWidth = boardWidth;
		this.paddleWidth = width;
		this.velocity = new Point2D(0,0);
		state = MoveState.STOPPED;
	}

	/*
	 * Paddle constructor using an image fill
	 */
	public Paddle(int xPosition, int yPosition, int width, int height, int  boardWidth,Image image) {
		super(xPosition, yPosition, width, height, image);
		this.boardWidth = boardWidth;
		this.paddleWidth = width;
		this.velocity = new Point2D(0,0);
		state = MoveState.STOPPED;
	}

	/*
	 * Provides paddle movement which the user can use to control
	 */
	public void moveHorizontally(boolean goRight) {
	    if (!moved) {
	        moved = true;
	    }

	    double currentVelocityX = velocity.getX();
	    
	    if (goRight && this.getView().getLayoutX() + paddleWidth < boardWidth) {
	        double deltaX = checkVelocity(); 
	        velocity = new Point2D(currentVelocityX * deltaX, 0);

	        this.setX(this.getX() + deltaX); 
	        state = MoveState.RIGHT;
	        
	    } else if (!goRight && this.getView().getLayoutX() > 0) {
	        double deltaX = checkVelocity(); 
	        velocity = new Point2D(currentVelocityX * -deltaX , 0);

	        this.setX(this.getX() - deltaX);
	        state = MoveState.LEFT;
	    }
	}	


	private double checkVelocity() {
		double deltaX = velocity.getX();

		if(deltaX == 0) {
			return MOVE_SPEED;
		}
		if(deltaX > MAX_VELOCITY) {
			deltaX = MAX_VELOCITY;
		}
		if (deltaX < MIN_VELOCITY) {
			deltaX = MIN_VELOCITY;
		}
		return deltaX;
	}

	public MoveState getState() {
		return state;
	}

	public boolean hasBeenMoved() {
		return moved;
	}

	public void stop() {
		this.velocity = new Point2D(0,0);
		state = MoveState.STOPPED;
	}

}
