import java.util.List;

import Ball.Ball;
import layouts.BrickLayout;

import java.util.List;

public class GameController {
	
	private PlayerController playerController;
	private HighScoreController highScoreController;
	//private BrickLayout brickLayout
	private List<Ball> balls;
	private AnimationController animationController;
	
	private boolean gameLost;
	private int level;
	
	public void run() {
		
	}
	
	private boolean checkPlayerLived() {
		return true;
	}
	
	public void nextLevel(int screenWidth, int screenHeight, AnimationController animation) {
		if(level != 4) {
			level++;
			BrickLayout brickLayout = new BrickLayout(screenHeight, screenWidth, level++ );
			
		}
	}
	
	
}
