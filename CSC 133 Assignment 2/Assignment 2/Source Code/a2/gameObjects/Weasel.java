package a2.gameObjects;

import java.awt.Color;
import java.util.Random;

import a2.interfaces.IStrategy;

public class Weasel extends Moveable{

	protected IStrategy currStrategy;
	
	public Weasel(){
		getLocation().setX((float)randInt(7,994));
		getLocation().setY((float)randInt(7,994));

		setHeading(randInt(0,360));
		setSpeed(randInt(1,10));
		
		super.setColor(new Color(randInt(0,255),randInt(0,255),randInt(0,255)));
		
		currStrategy = new BoundaryMovement(this);
	}
	
	public void setStrategy(Snake victim){
		if (currStrategy instanceof BoundaryMovement){
			currStrategy = new ChaseMovement(this, victim);
		} else 
			currStrategy = new BoundaryMovement(this);
	}
	
	
	public void invokeStrategy(){
		currStrategy.apply();
	}
	
	@Override
	public void move() {
		invokeStrategy();
	}

	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	@Override
	public String toString(){
		return "Weasel: loc=" + getLocation().getX() + "," + getLocation().getY() + 
				" color=[" + getColor().getRed() + "," + getColor().getGreen() + 
				"," + getColor().getBlue() + "]" + " speed=" + getSpeed() + " heading=" + getHeading() + " strategy=" + currStrategy.toString();
	}
	
	/**
	 * Overriding setLocation to be empty since Walls shouldn't be able
	 * to change their location.
	 * @see a2.gameObjects.GameObject#setLocation(a2.gameObjects.Location)
	 */
	@Override
	public void setLocation(Location location){
		//Empty
	}
	
	/**
	 * Overriding setColor to be empty since Walls shouldn't be able
	 * to change their color.
	 * @see a2.gameObjects.GameObject#setColor(java.awt.Color)
	 */
	@Override
	public void setColor(Color c){
		//Empty
	}
	
	
}
