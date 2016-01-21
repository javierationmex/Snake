package a4.gameObjects;

import a4.interfaces.ICollider;
import a4.interfaces.IDrawable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

/**
 * @author Javier G
 * Class for a Food object with a Location, amount, and a Color.
 * Extends Fixed
 * 
 */
public class Food extends Fixed implements IDrawable,ICollider {
	private int amount;
    private int myRadius;
    private AffineTransform myTranslation;
    private AffineTransform myRotation;
    private AffineTransform myScale;
	
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
        myRadius = 4;

        myTranslation = new AffineTransform();
        myRotation = new AffineTransform();
        myScale = new AffineTransform();

        translate(getLocation().getX(), getLocation().getY());
        scale(2,2);

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

    public void translate(double dx,double dy){
        myTranslation.translate(dx,dy);
    }

    public void scale(double x, double y){
        myScale.scale(x, y);
    }

    public void resetTransform(){
        myRotation.setToIdentity();
        myTranslation.setToIdentity();
        myScale.setToIdentity();
    }

    @Override
    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        AffineTransform saveAt = g2d.getTransform();
        g2d.transform(myTranslation);
        g2d.transform(myRotation);
        g2d.transform(myScale);
        Point boxCorner = new Point(-myRadius, -myRadius);

        //g.setColor(getColor());
        g2d.setColor(Color.RED);
        g2d.fillOval(boxCorner.x, boxCorner.y, myRadius*2, myRadius*2);
//        g.fillOval((int)getLocation().getX() - (getSize()/2), (int)getLocation().getY() - (getSize()/2), getSize(), getSize());
//        g.setColor(Color.white);
//        g.drawString("" + amount, (int) getLocation().getX() - 4, (int) getLocation().getY() + 4);
        g2d.setTransform(saveAt);
    }

    @Override
    public boolean collidesWith(GameObject otherObject) {
        return false;
    }

    @Override
    public void handleCollision(GameObject otherObject) {

    }
}
