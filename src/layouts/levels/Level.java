/**
 * @author Aidan Jimenez
 */
package layouts.levels;

import java.util.HashMap;
import java.util.Map;

public abstract class Level {
	
	protected Map<Integer, String[]> levelMap = new HashMap<>();
	
	public Level() {
		initializeLevels();
	}
	
	protected abstract void initializeLevels();
	
	public String[]  getLevel(int level) {
		if(levelMap.containsKey(level)) {
			return levelMap.get(level);
		}
		return levelMap.get(1);
	}
}
