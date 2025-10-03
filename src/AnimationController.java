import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * 
 * @author Shannon Duvall
 * 
 *         This program animates two squares. The top is the "mover" and
 *         the bottom is the "grower".
 * 
 */

public class AnimationController {

  public static final String BOUNCER_IMAGE = "resources/ball.gif";
  public static final Paint MOVER_COLOR = Color.PLUM;
  public static final Paint HIGHLIGHT = Color.OLIVEDRAB;
  public static final int MOVER_SIZE = 50;
  public static final int MOVER_SPEED = 15;
  public static final int NUM_BOUNCERS = 1;

  private List<Bouncer> myBouncers;
  private Rectangle myMover;
  private int width;
  private int height;

  public Group createRootForAnimation(int windowWidth, int windowHeight) {
    width = windowWidth;
    height = windowHeight;

    // create one top level collection to organize the things in the scene
    Group root = new Group();
    // make some shapes and set their properties
    try {
      Image image = new Image(new FileInputStream(BOUNCER_IMAGE));
      myBouncers = new ArrayList<>();
      for (int k = 0; k < NUM_BOUNCERS; k++) {
        Bouncer b = new Bouncer(image, width, height);
        myBouncers.add(b);
        root.getChildren().add(b.getView());
      }
    } catch (FileNotFoundException e) {
    }

    myMover = new Rectangle(width / 2 - MOVER_SIZE / 2, height / 2 - 100, MOVER_SIZE * 2, MOVER_SIZE / 2);
    myMover.setFill(MOVER_COLOR);

    root.getChildren().add(myMover);
    return root;
  }

  public void step(double elapsedTime) {
    // update "actors" attributes
    for (Bouncer b : myBouncers) {
      b.move(elapsedTime);
    }
    // myMover.setRotate(myMover.getRotate() + 1);

    // check for collisions
    // with shapes, can check precisely
    // NOTE: Could be best to use a sphere to measure when something is hit since
    // its more accurate.
//     Shape intersection = Shape.intersect(myMover, myGrower);
//     if (intersection.getBoundsInLocal().getWidth() != -1) {
//     myMover.setFill(HIGHLIGHT);
//     } else {
//     myMover.setFill(MOVER_COLOR);
//     }

    // with images can only check bounding box
    boolean hit = false;
    for (Bouncer b : myBouncers) {
      if (myMover.getBoundsInParent().intersects(b.getView().getBoundsInParent())) {
        myMover.setFill(HIGHLIGHT);
        b.bounceOffBlock();
        hit = true;
      }else {
     myMover.setFill(MOVER_COLOR);
     }

    }
    // if (!hit) {
    // myGrower.setFill(GROWER_COLOR);
    // }

    // bounce off all the walls
    for (Bouncer b : myBouncers) {
      b.bounce(width, height);
    }
  }

  // public void moverMovesVertically(boolean goUp) {
  // if (goUp)
  // myMover.setY(myMover.getY() - MOVER_SPEED);
  //
  // else {
  // myMover.setY(myMover.getY() + MOVER_SPEED);
  // }
  // }

  public void moverMovesHorizontally(boolean goRight) {
    if (goRight)
      myMover.setX(myMover.getX() - MOVER_SPEED);

    else {
      myMover.setX(myMover.getX() + MOVER_SPEED);
    }
  }

//  public void handleMouseInput(double x, double y) {
//    if (myGrower.contains(x, y)) {
//      myGrower.setScaleX(myGrower.getScaleX() * GROWER_RATE);
//      myGrower.setScaleY(myGrower.getScaleY() * GROWER_RATE);
//
//    }
//  }
}
