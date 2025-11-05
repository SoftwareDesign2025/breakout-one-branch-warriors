//Author: Benji Altmann

package game;
import entities.blocks.Player;

public class BreakOutPlayerController extends PlayerController {
	
	public static final String HighScoreFileName = "scoreFileBreakout.txt";
	
	public BreakOutPlayerController() {
		super();
	}
	public BreakOutPlayerController(Player paddle) {
		super(paddle, HighScoreFileName);
		
	}
	
	
	
}
