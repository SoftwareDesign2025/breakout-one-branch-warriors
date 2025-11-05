//Author: Benji Altmann

package game;

import entities.blocks.Player;
import entities.blocks.PlayerShip;

public class GalagaPlayerController extends PlayerController {
	
	public static final String HighScoreFileName = "Galaga";
	
	public GalagaPlayerController() {
		super();
	}
	public GalagaPlayerController(PlayerShip playerShip) {
		super(playerShip, HighScoreFileName);
	}
}
