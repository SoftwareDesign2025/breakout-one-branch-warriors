/**
 * @author Aidan Jimenez
 */
package layouts.levels;

public class BreakoutLevels extends Level{
	private static final String[] LEVEL_1_MAP = {
		    "XXXXXXXXX",
		};

		private static final String[] LEVEL_2_MAP = {
		    "    X    ",
		    "  XDDDX  ",
		    "XXXUUUXXX",
		    "  XXXXX  ",
		    "    X    ",
		};

		private static final String[] LEVEL_3_MAP = {
		    "XXXXDXXXX",
		    " XXXDXXX ",
		    "  XXDXX  ",
		    "    U    ",
		    "  XXDXX  ",
		    " XXXDXXX ",
		    "XXXXXXXXX",
		};

	public BreakoutLevels() {
		super();
	}

	@Override
	protected void initializeLevels() {
		levelMap.put(1, LEVEL_1_MAP);
		levelMap.put(2, LEVEL_2_MAP);
		levelMap.put(3, LEVEL_3_MAP);
	}

}
