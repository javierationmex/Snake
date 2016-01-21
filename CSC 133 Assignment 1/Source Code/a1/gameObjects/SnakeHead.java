package a1.gameObjects;

import java.awt.Color;
import java.util.Random;

import a1.interfaces.ISteerable;

/**
 * @author Javier G
 * Class for the object SnakeHead with deltaX, deltaY, oldX, oldY, Location, heading, speeed
 * and color
 * Extends Moveable and implements ISteerable
 */
public class SnakeHead extends Moveable implements ISteerable{
	private double deltaX, deltaY;
	private float oldX, oldY;
	
	/**
	 * Constructor to initialize Location, heading, and color
	 */
	public SnakeHead(){
		
		getLocation().setX((float)randInt(7,994));
		getLocation().setY((float)randInt(7, 994));
		
		setHeading(0);
		
		super.setColor(new Color(randInt(0,255),randInt(0,255),randInt(0,255)));
	}
	
	/**
	 * Method to switch the heading of the snakeHead based on user input
	 * @see a1.interfaces.ISteerable#turn(char)
	 */
	public void turn(char direction) {
		// TODO Auto-generated method stub
		switch (direction){
		case 'n':
			setHeading(0);
		break;
		case 'e':
			setHeading(90);
		break;
		case 's':
			setHeading(180);
		break;
		case 'w':
			setHeading(270);
		break;
		
		}
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
	 * Determine the new location of the object base on the heading,
	 * speed, and current location.
	 * @see a1.gameObjects.Moveable#move()
	 */
	@Override
	public void move(){
		deltaX = (Math.cos(Math.toRadians(90-getHeading()))) * getSpeed();
		deltaY = (Math.sin(Math.toRadians(90-getHeading()))) * getSpeed();
		
		oldX = getLocation().getX();
		oldY = getLocation().getY();
		
		getLocation().setX((float) (oldX + deltaX));
		getLocation().setY((float) (oldY + deltaY));
	}

	/**
	 * Format the objects information properly to be able to display
	 * on the screen for the user when they select to display a map.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return "SnakeHead: loc=" + getLocation().getX() + "," + getLocation().getY() + 
				" color=[" + getColor().getRed() + "," + getColor().getGreen() + 
				"," + getColor().getBlue() + "]" + " speed=" + getSpeed() + " heading=" + getHeading();
	}
	
	/**
	 * Overriding setLocation to be empty since Walls shouldn't be able
	 * to change their location.
	 * @see a1.gameObjects.GameObject#setLocation(a1.gameObjects.Location)
	 */
	@Override
	public void setLocation(Location location){
		//Empty
	}
	
	/**
	 * Overriding setColor to be empty since Walls shouldn't be able
	 * to change their color.
	 * @see a1.gameObjects.GameObject#setColor(java.awt.Color)
	 */
	@Override
	public void setColor(Color c){
		//Empty
	}
	
	
	
}
