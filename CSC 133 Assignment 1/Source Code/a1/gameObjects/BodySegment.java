package a1.gameObjects;

import java.awt.Color;
import java.util.Random;

/**
 * @author Javier G
 * Class for a body segment of the snake with heading, location x and y and color
 */
public class BodySegment extends Moveable{
	
	/**
	 * Constructor to initialize location x and y, heading and color
	 * @param heading
	 * @param locationX
	 * @param locationY
	 */
	public BodySegment(int heading, float locationX, float locationY){
		setHeading(heading);
		
		getLocation().setX(locationX);
		getLocation().setY(locationY);
		
		super.setColor(new Color(randInt(0,255),randInt(0,255),randInt(0,255)));
	}

	/**
	 * Method to specify how a body segment moves by taking the heading
	 * locationX and locationY
	 * @param heading
	 * @param locationX
	 * @param locationY
	 */
	public void move(int heading, float locationX, float locationY) {
		// TODO Auto-generated method stub
		setHeading(heading);
		getLocation().setX(locationX);
		getLocation().setY(locationY);
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
	 * Empty move method
	 * @see a1.gameObjects.Moveable#move()
	 */
	@Override
	public void move() {
		// TODO Auto-generated method stub
		//Empty
	}
	
	/**
	 * Format the objects information properly to be able to display
	 * on the screen for the user when they select to display a map.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return "BodySegment: loc=" + getLocation().getX() + "," + getLocation().getY() + 
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
