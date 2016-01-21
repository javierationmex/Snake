package a2.gameObjects;

import a2.interfaces.IStrategy;

public class BoundaryMovement implements IStrategy{
	private Weasel client;
	private double deltaX, deltaY;
	private float oldX, oldY;
	private float newX, newY;
	
	public BoundaryMovement(Weasel cl){
		this.client = cl;
	}
	
	@Override
	public void apply() {
		deltaX = (Math.cos(Math.toRadians(90-client.getHeading()))) * client.getSpeed();
		deltaY = (Math.sin(Math.toRadians(90-client.getHeading()))) * client.getSpeed();
		
		oldX = client.getLocation().getX();
		oldY = client.getLocation().getY();
		
		newX = (float) (oldX + deltaX);
		newY = (float) (oldY + deltaY);
		
		if (newX > 997){
			client.setHeading(270);
			deltaX = (Math.cos(Math.toRadians(90-client.getHeading()))) * client.getSpeed();
		} else if (newX < 3){
			client.setHeading(0);
			deltaX = (Math.cos(Math.toRadians(90-client.getHeading()))) * client.getSpeed();
		} else if (newY > 997){
			client.setHeading(180);
			deltaY = (Math.sin(Math.toRadians(90-client.getHeading()))) * client.getSpeed();
		} else if (newY < 3){
			client.setHeading(0);
			deltaY = (Math.sin(Math.toRadians(90-client.getHeading()))) * client.getSpeed();
		}
		
		newX = (float) (oldX + deltaX);
		newY = (float) (oldY + deltaY);
		
		client.getLocation().setX(newX);
		client.getLocation().setY(newY);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Boundary Movement";
	}
	
	

}
