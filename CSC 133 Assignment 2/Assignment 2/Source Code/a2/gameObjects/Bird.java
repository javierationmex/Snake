package a2.gameObjects;

import java.awt.Color;
import java.util.Random;

/**
 * @author Javier G
 * Class for a Bird object with a Location, size, speed, heading, and Color
 * Extends Moveable
 */
public class Bird extends Moveable{
	private int size;
	private double deltaX, deltaY;
	private float oldX, oldY;
	
	/**
	 * Constructor to initialize its Location x and y, size, heading, speed, and color
	 * with random values
	 */
	public Bird(){
		
		getLocation().setX((float)randInt(7,994));
		getLocation().setY((float)randInt(7,994));

		size = randInt(1,5);
		setHeading(randInt(0,360));
		setSpeed(randInt(1,10));
		
		super.setColor(new Color(randInt(0,255),randInt(0,255),randInt(0,255)));
	}
	
	
	/**
	 * Method to return a random integer which is used for randomizing
	 * initialization of certain values
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	/**
	 * Format the objects information properly to be able to display
	 * on the screen for the user when they select to display a map.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return "Bird: loc=" + getLocation().getX() + "," + getLocation().getY() + 
				" color=[" + getColor().getRed() + "," + getColor().getGreen() + 
				"," + getColor().getBlue() + "]" + " speed=" + getSpeed() + " heading=" + getHeading() + " size=" + size;
	}

	/**
	 * Determine the new location of the object base on the heading,
	 * speed, and current location.
	 * @see a2.gameObjects.Moveable#move()
	 */
	@Override
	public void move() {
		deltaX = (Math.cos(Math.toRadians(90-getHeading()))) * getSpeed();
		deltaY = (Math.sin(Math.toRadians(90-getHeading()))) * getSpeed();
		
		oldX = getLocation().getX();
		oldY = getLocation().getY();
		
		getLocation().setX((float) (oldX + deltaX));
		getLocation().setY((float) (oldY + deltaY));
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
