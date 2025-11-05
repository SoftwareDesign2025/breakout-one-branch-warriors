/**
 * @author Aidan Jimenez
 */
package layouts.levels;

public class GalagaLevels extends Level{

	private static final String[] LEVEL_1_MAP = {
		    "          ",
		    "          ",
		    " XXXXXXX  ",
		};

		private static final String[] LEVEL_2_MAP = {
		    " BBBBBBB  ",
		    " BBBBBBB  ",
		    " XXXXXXX  ",
		    " XXXXXXX  ",
		};

		private static final String[] LEVEL_3_MAP = {
		    " SSSSSSS  ",
		    " BBBBBBB  ",
		    " BBBBBBB  ",
		    " XXXXXXX  ",
		    " XXXXXXX  ",
		};
	public GalagaLevels() {
		super();
	}

	@Override
	protected void initializeLevels() {
		levelMap.put(1, LEVEL_1_MAP);
		levelMap.put(2, LEVEL_2_MAP);
		levelMap.put(3, LEVEL_3_MAP);
	}

}
