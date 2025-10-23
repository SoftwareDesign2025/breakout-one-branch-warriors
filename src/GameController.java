import java.util.List;
import blocks.BrickLayout;

import java.util.List;

public class GameController {
	
	private PlayerController playerController;
	private HighScoreController highScoreController;
	//private BrickLayout brickLayout
	private List<Ball> balls;
	private AnimationController animationController;
	//private UI uiController;
	private boolean gameLost;
	private int level = 1;
	
	public void run() {
		
	}
	
	private boolean checkPlayerLived() {
		return true;
	}
	
	public void nextLevel(int screenWidth, int screenHeight) {
		if(level != 4) {
			level++;
			BrickLayout brickLayout = new BrickLayout(screenHeight, screenWidth/*, int level++ */);
			
		}
	}
	public void setLevel(int screenWidth, int screenHeight, int level) {
		BrickLayout brickLayout = new BrickLayout(screenHeight,screenWidth);
	}
}
