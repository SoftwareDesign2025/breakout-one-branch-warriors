//Author: Benji Altmann

package game;

import entities.blocks.Player;
import entities.blocks.PlayerShip;

public class GalagaPlayerController extends PlayerController {
	
	public static final String HighScoreFileName = "scoreFileGalaga.txt";
	
	public GalagaPlayerController() {
		super();
	}
	public GalagaPlayerController(Player playerShip) {
		super(playerShip, HighScoreFileName);
	}
}
