package layouts.levels;

public class BreakoutLevels extends Level{
	private static final String[] LEVEL_1_MAP = {
		    "XXXXXXXXX",
		};

		private static final String[] LEVEL_2_MAP = {
		    "  XXXXX  ",
		    "  X   X  ",
		    "  XXXXX  ",
		};

		private static final String[] LEVEL_3_MAP = {
		    "XXXXXXXXX",
		    " XXXXXXX ",
		    "  XXXXX  ",
		    "    X    ",
		    "  XXXXX  ",
		    " XXXXXXX ",
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
