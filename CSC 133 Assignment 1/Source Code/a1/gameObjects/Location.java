package a1.gameObjects;

/**
 * @author Javier G
 * Hold the location of any object with x and y float values
 */
public class Location {
	private float x;
	private float y;
	
	/**
	 * Empty constructor
	 */
	public Location() {
		//Empty
	}
	
	/**
	 * Constructor to initialize location with an x and a y value
	 * @param x
	 * @param y
	 */
	public Location (float x, float y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Setter for x
	 * @param x
	 */
	protected void setX(float x) {
		this.x = x;
	}
	
	/**
	 * Setter for y
	 * @param y
	 */
	protected void setY(float y) {
		this.y = y;
	}
	
	/**
	 * Getter for x
	 * @return
	 */
	protected float getX() {
		return x;
	}
	/**
	 * Getter for y
	 * @return
	 */
	protected float getY() {
		return y;
	}
}
