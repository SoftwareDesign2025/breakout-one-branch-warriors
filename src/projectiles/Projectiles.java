package projectiles;
import entities.*;
import entities.blocks.Paddle.MoveState;
import javafx.scene.shape.Shape;

public abstract class Projectiles extends Entity {
	public Projectiles(int xPosition, int yPosition, int width, int height) {
		super(xPosition,yPosition,width,height);
	}
	public Projectiles(int xPosition, int yPosition, int width, int height, String image) {
		super(xPosition,yPosition,width,height,image);
	}
	public abstract Shape getBall();

	public void bounce(boolean b, MoveState moveState) {
		// TODO Auto-generated method stub
	}
	public void bounce(boolean b, double hitForceMultiplier) {
		// TODO Auto-generated method stub
	}
	
}
