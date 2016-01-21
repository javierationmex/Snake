package a3.gameObjects;

import a3.interfaces.ICollider;
import a3.interfaces.IDrawable;

import java.awt.*;
import java.util.Random;

/**
 * @author Javier G
 * Class for a Food object with a Location, amount, and a Color.
 * Extends Fixed
 * 
 */
public class Food extends Fixed implements IDrawable,ICollider {
	private int amount;
	
	/**
	 * Constructor to initialize its amount, Location x and y, and color
	 * with random values
	 */
	public Food (){
		amount = randInt(1,6);
		
		getLocation().setX((float)randInt(17,984));
		getLocation().setY((float)randInt(17,984));
		
		super.setColor(new Color(randInt(0,255),randInt(0,255),randInt(0,255)));

        setSize(16);

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
	 * @see a3.gameObjects.GameObject#setLocation(a3.gameObjects.Location)
	 */
	@Override
	public void setLocation(Location location){
		//Empty
	}
	
	/**
	 * Overriding setColor to be empty since Walls shouldn't be able
	 * to change their color.
	 * @see a3.gameObjects.GameObject#setColor(java.awt.Color)
	 */
	@Override
	public void setColor(Color c){
		//Empty
	}

    @Override
    public void draw(Graphics g) {
        //g.setColor(getColor());
        g.setColor(Color.RED);
        g.fillOval((int)getLocation().getX() - (getSize()/2), (int)getLocation().getY() - (getSize()/2), getSize(), getSize());
    }

    @Override
    public boolean collidesWith(GameObject otherObject) {
        return false;
    }

    @Override
    public void handleCollision(GameObject otherObject) {

    }
}
