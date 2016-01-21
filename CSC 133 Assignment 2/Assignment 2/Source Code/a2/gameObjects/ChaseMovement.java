package a2.gameObjects;

import a2.interfaces.IStrategy;

public class ChaseMovement implements IStrategy{
	private Weasel client;
	private Snake dinner;
	private double deltaX, deltaY;
	private double xDiff, yDiff;
	private float oldX, oldY;
	private float newX, newY;
	private float dinnerX, dinnerY;
	private int newHeading;
	
	public ChaseMovement(Weasel cl, Snake chasee){
		client = cl;
		dinner = chasee;
	}
	
	@Override
	public void apply() {
		
		dinnerX = dinner.getLocation().getX();
		dinnerY = dinner.getLocation().getY();
		
		xDiff = oldX - dinnerX;// - oldX; 
		yDiff = oldY - dinnerY;// - oldY; 
		newHeading = (int) Math.toDegrees(Math.atan2(yDiff, xDiff));
		if(newHeading < 0){
	        newHeading = Math.abs(newHeading) + 90;//newHeading += 360;
	    } else {
	    	newHeading = 90 - newHeading;
	    }
		
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
