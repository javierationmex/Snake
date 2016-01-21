package a1.gameObjects;

import java.awt.Color;
import java.util.Random;

/**
 * @author Javier G
 * Class for a Food object with a Location, amount, and a Color.
 * Extends Fixed
 * 
 */
public class Food extends Fixed {
	private int amount;
	
	/**
	 * Constructor to initialize its amount, Location x and y, and color
	 * with random values
	 */
	public Food (){
		amount = randInt(1,6);
		
		getLocation().setX((float)randInt(7,994));
		getLocation().setY((float)randInt(7,994));
		
		super.setColor(new Color(randInt(0,255),randInt(0,255),randInt(0,255)));
	}

	/**
	 * Getter for amount
	 * @return
	 */
	public int getAmount() {
		return amount;
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
		return "Food: loc=" + getLocation().getX() + "," + getLocation().getY() + 
				" color=[" + getColor().getRed() + "," + getColor().getGreen() + 
				"," + getColor().getBlue() + "]" + " amount=" + amount + " age=" + getAge();
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
