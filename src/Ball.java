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
    
    private static final double FRICTION_FACTOR = 0.99;
    private static final int MAX_VELOCITY = 500;
    private static final int MIN_VELOCITY = -MAX_VELOCITY;



    public Ball() {
        this.velocity = new Point2D(175, -150); 
        this.myBall = new Circle(10.0, Color.RED); 
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

    public void stop() {
        velocity = new Point2D(0,0);
    }

    /*
     * This method reverses the ball's velocity while also
     * changing it by a factor 'modification'.
     */
    public void bounce(boolean isReflectingXAxis, double modification) {
    	double deltaX = velocity.getX();
    	double deltaY = velocity.getY();
    	if(deltaY > MAX_VELOCITY){
    		deltaY = MAX_VELOCITY;
    	}

    	if(0 > deltaY  && deltaY < MIN_VELOCITY) {
    		deltaY = MIN_VELOCITY;
    	}

        if (isReflectingXAxis) {
            // Create a new Point2D object for the new velocity
            velocity = new Point2D(deltaX * -modification , (deltaY + getRandomDouble(0,1)));
			myBall.setCenterX(previousLocation.getX() - (myBall.getRadius()/3));
			myBall.setCenterY(previousLocation.getY());
        } else {
            velocity = new Point2D(deltaX + getRandomDouble(0,1), deltaY * -modification);
			myBall.setCenterX(previousLocation.getX());
			myBall.setCenterY(previousLocation.getY() - (myBall.getRadius()/3));
        }
    }


    /*
     * This method will change the ball velocity and cause it to bounce off the display wall
     */
    public void bounceOffWall(double screenWidth, double screenHeight) {
        // Get ball properties 
        double radius = myBall.getRadius();
        double x = myBall.getCenterX();
        double y = myBall.getCenterY();

        // Horizontal (X-axis) collision
        if (x - radius < 0) {
            myBall.setCenterX(radius); 
            velocity = new Point2D(-velocity.getX(), velocity.getY());
        } else if (x + radius > screenWidth) {
            velocity = new Point2D(-velocity.getX(), velocity.getY());
        }

        // Vertical (Y-axis) collision
        if (y - radius < 0) {
            velocity = new Point2D(velocity.getX(), -velocity.getY());
        } else if (y + radius > screenHeight) {
            velocity = new Point2D(velocity.getX(), -velocity.getY());
        }
        if (y - radius < 0 && x - radius < 0) {
            myBall.setCenterX(-radius); 
            myBall.setCenterY(-radius); 
            velocity = new Point2D(velocity.getX(), velocity.getY());
        }
    }
    /*
     * Updates the ball's position 
     */
    public void move(double elapsedTime) {
    	// Calculate the new position: newPosition = oldPosition + velocity * time
    	previousLocation = new Point2D(myBall.getCenterX(), myBall.getCenterY());
    	double newX = (myBall.getCenterX() + (velocity.getX() * FRICTION_FACTOR)* elapsedTime);
    	double newY = (myBall.getCenterY() + (velocity.getY() * FRICTION_FACTOR)* elapsedTime);

    	// Update the circle's position
    	myBall.setCenterX(newX);
    	myBall.setCenterY(newY);
    }
    
    private double getRandomDouble(double rangeStart, double rangeEnd) {
    	return rangeStart + random.nextDouble(rangeEnd - rangeStart) + 1;
    }
}
