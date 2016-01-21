package a1.gameObjects;

import java.awt.Color;
import java.util.Random;

import a1.interfaces.IColorChangeable;

/**
 * @author Javier G
 * Class for a Money object with a Location, value and Color.
 * Extends Fixed, implements IColorChangeable
 */
public class Money extends Fixed implements IColorChangeable{
	private int value;
	
	/**
	 * Constructor to initialize its value, Location x and y, and color
	 * with random values
	 */
	public Money(){
		
		getLocation().setX((float)randInt(7,994));
		getLocation().setY((float)randInt(7,994));
			
		value = randInt(1,5);
		super.setColor(new Color(randInt(0,255),randInt(0,255),randInt(0,255)));
	}

	/**
	 * Getter for value
	 * @return
	 */
	public int getValue() {
		return value;
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
		return "Money: loc=" + getLocation().getX() + "," + getLocation().getY() + 
				" color=[" + getColor().getRed() + "," + getColor().getGreen() + 
				"," + getColor().getBlue() + "]" + " value=" + value + " age=" + getAge();
	}
	
	/**
	 * Method to change the color, implemented from the interface IColorChangeable
	 * @see a1.interfaces.IColorChangeable#changeColor(int, int, int)
	 */
	@Override
	public void changeColor(int red, int green, int blue) {
		// TODO Auto-generated method stub
		if ((red >= 0 && red < 256) && (green >= 0 && green < 256) && (blue >= 0 && blue < 256))
		setColor(new Color(red,green,blue));
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
