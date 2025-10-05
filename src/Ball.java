import java.awt.geom.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;

public class Ball {
	
	private Point2D.Double velocity;
	private Point2D.Double position;
	private Double ballRadius;
	private Color color;
	private Sphere myBall;
	
	public Double getRadius() {
		return ballRadius;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Ball() {
		this.velocity.setLocation(10,10);
		this.ballRadius = 5.0;
	}
	
	public Ball(Point2D.Double velocity, Double ballRadius, Color color) {
		this.velocity = velocity;
		this.ballRadius = ballRadius;
		myBall.setRadius(ballRadius);
		this.color = color;
	}
	
	/*
	 * This method is the default for bounce, simply changing 
	 * the sign of the ball's velocity (no variance)
	 */
	public void bounce(boolean isReflectingXAxis) {
		bounce(isReflectingXAxis, 1.0);
	}
	
	/*
	 * This method reverses the balls velocity while also
	 * changing it by a factor 'modification'.
	 */
	public void bounce(boolean isReflectingXAxis, Double modification) {
		if(isReflectingXAxis) {
		velocity.setLocation(this.velocity.getX() * -modification, this.velocity.getY());
		}else {
			velocity.setLocation(this.velocity.getX(), this.velocity.getY() * -modification);
		}
	}
	
	public void move() {
		position.setLocation(position.getX() + velocity.getX(), position.getY() + velocity.getY());
	}
	

}
