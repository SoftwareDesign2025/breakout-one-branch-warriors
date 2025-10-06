import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Ball {

	private Random random = new Random();
    private Point2D velocity;
    private Point2D position;
    private final Circle myBall; 
    private Point2D previousLocation;


    public Ball() {
        this.velocity = new Point2D(150, 150); 
        this.myBall = new Circle(10.0, Color.BLUE); 
        // TODO: Change to non default cords
        // being used for testing purposes
        this.myBall.setCenterX(200);
        this.myBall.setCenterY(200);
    }

    public Ball(double startX, double startY, Point2D velocity, double ballRadius, Color color) {
        this.velocity = velocity;
        this.myBall = new Circle(ballRadius, color);

        this.myBall.setCenterX(startX);
        this.myBall.setCenterY(startY);
    }

    public Node getView() {
        return myBall;
    }
    
    public Shape getBall() {
    	return myBall;
    }

    public double getRadius() {
        return myBall.getRadius();
    }

    public void setColor(Color color) {
        myBall.setFill(color);
    }

    public void setX(double x) {
        myBall.setCenterX(x);
    }

    public void setY(double y) {
        myBall.setCenterY(y);
    }
    public Point2D getPosition() {
        return position;
    }


    /*
     * This method is the default for bounce, simply changing
     * the sign of the ball's velocity (no variance).
     */
    public void bounce(boolean isReflectingXAxis) {
        bounce(isReflectingXAxis, 1.0);
    }

    /*
     * This method reverses the ball's velocity while also
     * changing it by a factor 'modification'.
     */
    public void bounce(boolean isReflectingXAxis, double modification) {
        if (isReflectingXAxis) {
            // Create a new Point2D object for the new velocity
            velocity = new Point2D(velocity.getX() * -modification, velocity.getY());
        } else {
            velocity = new Point2D(velocity.getX(), velocity.getY() * -modification);
        }
        myBall.setCenterX(previousLocation.getX() - 1);
        myBall.setCenterY(previousLocation.getY() - 1);
    }


    /*
     * This method will change the ball velocity and cause it to bounce off the display wall
     */
    public void bounceOffWall(double screenWidth, double screenHeight) {
    	if (myBall.getCenterX() - myBall.getRadius() < 0 || myBall.getCenterX() + myBall.getRadius() > screenWidth - myBall.getBoundsInLocal().getWidth()) {
    		velocity = new Point2D(-velocity.getX(), velocity.getY());
    	}
    	if (myBall.getCenterY() - myBall.getRadius() < 0 || (myBall.getCenterY()+ myBall.getRadius()) > screenHeight - myBall.getBoundsInLocal().getHeight()) {
    		velocity = new Point2D(velocity.getX(), -velocity.getY());
    	}
    }

    /*
     * Updates the ball's position 
     */
    public void move(double elapsedTime) {
    	// Calculate the new position: newPosition = oldPosition + velocity * time
    	previousLocation = new Point2D(myBall.getCenterX(), myBall.getCenterY());
    	double newX = myBall.getCenterX() + velocity.getX() * elapsedTime;
    	double newY = myBall.getCenterY() + velocity.getY() * elapsedTime;

    	// Update the circle's position
    	myBall.setCenterX(newX);
    	myBall.setCenterY(newY);
    }
    
    private int getRandomInt(int rangeStart, int rangeEnd) {
    	return rangeStart + random.nextInt(rangeEnd - rangeStart) + 1;
    }
}
