package a3.gameObjects;

public abstract class Moveable extends GameObject {
	private int heading;
	private int speed = 1;
    private double deltaX, deltaY;
    private float oldX, oldY;
	
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
    public void move(int rate){
        deltaX = (Math.cos(Math.toRadians(90-getHeading()))) * (getSpeed() * (1000/rate));
        deltaY = (Math.sin(Math.toRadians(90-getHeading()))) * (getSpeed() * (1000/rate));

        oldX = getLocation().getX();
        oldY = getLocation().getY();

        getLocation().setX((float) (oldX + deltaX));
        getLocation().setY((float) (oldY + deltaY));
    }
	
}
