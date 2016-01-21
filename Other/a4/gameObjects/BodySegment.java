package a4.gameObjects;

import a4.GameWorldProxy;
import a4.interfaces.ICollider;
import a4.interfaces.IDrawable;

import java.awt.*;
import java.util.Random;

/**
 * @author Javier G
 * Class for a body segment of the snake with heading, location x and y and color
 */
public class BodySegment extends Moveable implements IDrawable, ICollider{
    private double deltaX, deltaY;
    private float oldX, oldY;
    private GameWorldProxy gWP;
	/**
	 * Constructor to initialize location x and y, heading and color
	 * @param heading
	 * @param locationX
	 * @param locationY
	 */
	public BodySegment(int heading, float locationX, float locationY, GameWorldProxy gwp){
		setHeading(heading);
		
		getLocation().setX(locationX);
		getLocation().setY(locationY);
		
		super.setColor(new Color(randInt(0,255),randInt(0,255),randInt(0,255)));

        this.gWP = gwp;

        setSize(14);
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
	 * @see a4.gameObjects.Moveable#move()
	 */
//	@Override
//	public void move() {
//		// TODO Auto-generated method stub
//		//Empty
//	}

    public void move(int heading){
        this.setHeading(heading);
        deltaX = (Math.cos(Math.toRadians(90-getHeading()))) * (getSpeed() * (50));
        deltaY = (Math.sin(Math.toRadians(90-getHeading()))) * (getSpeed() * (50));

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
		return "BodySegment: loc=" + getLocation().getX() + "," + getLocation().getY() + 
				" color=[" + getColor().getRed() + "," + getColor().getGreen() + 
				"," + getColor().getBlue() + "]" + " speed=" + getSpeed() + " heading=" + getHeading();
	}
	
	/**
	 * Overriding setLocation to be empty since Walls shouldn't be able
	 * to change their location.
	 * @see a4.gameObjects.GameObject#setLocation(a4.gameObjects.Location)
	 */
	@Override
	public void setLocation(Location location){
		//Empty
	}
	
	/**
	 * Overriding setColor to be empty since Walls shouldn't be able
	 * to change their color.
	 * @see a4.gameObjects.GameObject#setColor(java.awt.Color)
	 */
	@Override
	public void setColor(Color c){
		//Empty
	}

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect((int)(getLocation().getX() - getSize()/2), (int)(getLocation().getY() - getSize()/2), getSize(), getSize());
    }

    @Override
    public boolean collidesWith(GameObject otherObject) {
        boolean result = false;
        int thisCenterX = (int)this.getLocation().getX();
        int thisCenterY = (int)this.getLocation().getY();
        int otherCenterX = (int)otherObject.getLocation().getX();
        int otherCenterY = (int)otherObject.getLocation().getY();

        int dx = thisCenterX - otherCenterX;
        int dy = thisCenterY - otherCenterY;
        int distanceBetweenCentersSqr = (dx * dx + dy * dy);

        int thisRadius = getSize()/2;
        int otherRadius = otherObject.getSize()/2;
        int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);

        if (distanceBetweenCentersSqr <= radiiSqr) { result = true; }

        return result;
    }

    @Override
    public void handleCollision(GameObject otherObject) {
        gWP.collisionWithBody();
    }
}
