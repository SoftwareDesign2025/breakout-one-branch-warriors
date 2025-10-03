package blocks;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


public class Block {
	protected Rectangle rect;

	public Block(int xPosition, int yPosition, int height, int width){
		rect = new Rectangle(xPosition, yPosition, height, width);
	}

	public void setFill(Paint color) {
		rect.setFill(color);
		rect.setStroke(Color.BLACK);
	}

	public Rectangle getRect() {
		return rect;
	}

	public double getX(){
		return rect.getX();
	}

	public double getY(){
		return rect.getY();
	}

	public void setX(double x){
		rect.setX(x);
	}

	public void setY(double y){
		rect.setY(y);
	}

	//  public void checkCollisionWithBall(ArrayList<Ball> balls){
	//    for (Ball ball : balls) {
	//      if (rect.getBoundsInParent().intersects(object.getView().getBoundsInParent())) {
	//        ball.bounceOffBlock();
	//      }
	//  }
}
