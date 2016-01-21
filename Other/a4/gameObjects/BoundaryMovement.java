package a4.gameObjects;

import a4.interfaces.IStrategy;

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

	
		
		if (client.getHeading() < 90 && client.getHeading() > 0){
			client.translate(5, -5);
		} else if (client.getHeading() < 180 && client.getHeading() > 90){
			client.translate(5, 5);
		} else if (client.getHeading() < 270 && client.getHeading() > 180){
			client.translate(5, -5);
		} else if (client.getHeading() < 360 && client.getHeading() > 270){
			client.translate(-5, -5);
		}
		
		
		
//		client.getLocation().setX(newX);
//		client.getLocation().setY(newY);
	}

}
