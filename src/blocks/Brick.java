package blocks;

public class Brick extends Block{
	private double hitForceMultiplier;
	private int points;

	public Brick(int xPosition, int yPosition, int width, int height, double hitForceMultiplier, int points) {
		super(xPosition, yPosition, width, height);
		this.hitForceMultiplier = hitForceMultiplier;
		this.points = points;
	}

	public Brick(int xPosition, int yPosition, int width, int height) {
		super(xPosition, yPosition, width, height);
		this.hitForceMultiplier = 2;
		this.points = 10;
	}

}
