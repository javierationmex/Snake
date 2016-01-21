package a2.gameObjects;

public abstract class Moveable extends GameObject {
	private int heading;
	private int speed = 1;
	
	public int getHeading() {
		return heading;
	}
	protected int getSpeed() {
		return speed;
	}
	
	protected void setHeading(int heading) {
		this.heading = heading;
	}
	protected void setSpeed(int speed) {
		this.speed = speed;
	}
	public abstract void move ();
	
}
