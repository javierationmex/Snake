package a2.gameObjects;

import java.awt.Color;
import java.util.Random;

/**
 * @author Javier G
 * Class for a wall object with a Location, width, height, and Color
 * Extends Fixed
 */
public class Wall extends Fixed {
	private int width;
	private int height;

	/**
	 * Constructor to initialize its Location x and y, width, height, and color
	 * with random values
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Wall(float x, float y, int width, int height){
		getLocation().setX(x);
		getLocation().setY(y);
		this.width = width;
		this.height = height;
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
		return "Wall: loc=" + getLocation().getX() + "," + getLocation().getY() + 
				" color=[" + getColor().getRed() + "," + getColor().getGreen() +
				"," + getColor().getBlue() + "]" + " width=" + width + 
				" height=" + height + " age=" + getAge();
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
