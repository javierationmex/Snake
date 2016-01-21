package a3.gameObjects;

import a3.interfaces.IStrategy;

public class ChaseMovement implements IStrategy{
	private Weasel client;
	private double deltaX, deltaY;
	private double xDiff, yDiff;
	private float oldX, oldY;
	private float newX, newY;
	private float dinnerX, dinnerY;
	private int newHeading;
	
	public ChaseMovement(Weasel cl){
		client = cl;
	}
	
	@Override
	public void apply() {
		
		dinnerX = client.getSnakeHead().getLocation().getX();
		dinnerY = client.getSnakeHead().getLocation().getY();
		
		xDiff = dinnerX - oldX;
		yDiff = dinnerY - oldY;
		newHeading = (int) Math.toDegrees(Math.atan2(yDiff, xDiff));
		deltaX = (Math.cos(Math.toRadians(newHeading))) * client.getSpeed();
		deltaY = (Math.sin(Math.toRadians(newHeading))) * client.getSpeed();
		
		oldX = client.getLocation().getX();
		oldY = client.getLocation().getY();
		
		newX = (float) (oldX + deltaX);
		newY = (float) (oldY + deltaY);
		
		client.getLocation().setX(newX);
		client.getLocation().setY(newY);
		client.setHeading(newHeading);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Chasing Movement";
	}

}
