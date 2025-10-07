package blocks;

public class Brick extends Block{
	private static final int BASE_MULTIPLIER = 2;
	private static final int BASE_POINTS = 10;
	private static final int BASE_DURABILITY = 1;

	private double hitForceMultiplier;
	private int points;
	private int durability;

	public Brick(int xPosition, int yPosition, int width, int height, double hitForceMultiplier, int points, int durability) {
		super(xPosition, yPosition, width, height);
		this.hitForceMultiplier = hitForceMultiplier;
		this.points = points;
		this.durability = durability;
	}

	public Brick(int xPosition, int yPosition, int width, int height) {
		super(xPosition, yPosition, width, height);
		this.hitForceMultiplier = BASE_MULTIPLIER;
		this.points = BASE_POINTS;
		this.durability = BASE_DURABILITY;
	}
	
	public void removeDurability() {
		durability -=1;
	}

	public boolean isBroken() {
		return durability <= 1;
	}
	
	public double getHitForceMultiplier() {
		return hitForceMultiplier;
	}
}
