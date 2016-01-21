package a4.gameObjects;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

import a4.interfaces.ICollider;
import a4.interfaces.IColorChangeable;
import a4.interfaces.IDrawable;
import a4.interfaces.ISelectable;

/**
 * @author Javier G
 * Class for a Money object with a Location, value and Color.
 * Extends Fixed, implements IColorChangeable
 */
public class Money extends Fixed implements IColorChangeable, IDrawable, ICollider, ISelectable{
	private int value;
    private int myRadius;
    private boolean collidedWith;
    private boolean isSelected;
    private AffineTransform myTranslation;
    private AffineTransform myRotation;
    private AffineTransform myScale;

	/**
	 * Constructor to initialize its value, Location x and y, and color
	 * with random values
	 */
	public Money(){
		
		getLocation().setX((float)randInt(17,984));
		getLocation().setY((float)randInt(17,984));
			
		value = randInt(1,5);
		super.setColor(Color.YELLOW);

        setSize(16);
        myRadius = 4;

        collidedWith = false;

        myTranslation = new AffineTransform();
        myRotation = new AffineTransform();
        myScale = new AffineTransform();

        translate(getLocation().getX(), getLocation().getY());
        scale(2,2);
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

    public boolean isCollidedWith() {
        return collidedWith;
    }

    public void setCollidedWith(boolean collidedWith) {
        this.collidedWith = collidedWith;
    }

    /**
	 * Method to change the color, implemented from the interface IColorChangeable
	 * @see a4.interfaces.IColorChangeable#changeColor(int, int, int)
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
    public void setSelected(boolean yesNo) {
        isSelected = yesNo;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public boolean contains(Point p) {
        int px = (int) p.getX();
        int py = (int) p.getY();
        int xLoc = (int) getLocation().getX() - getSize()/2;
        int yLoc = (int) getLocation().getY() - getSize()/2;
        if ( (px >= xLoc) && (px <= xLoc + getSize())
                && (py >= yLoc) && (py <= yLoc + getSize())){
            return true;
        } else {
            return false;
        }
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


        if (isSelected()){
            Color c = new Color((255-getColor().getRed()), (255-getColor().getGreen()), (255-getColor().getBlue()));
            g2d.setColor(c);
            g2d.drawOval(boxCorner.x, boxCorner.y, myRadius*2, myRadius*2);
        } else {
            g2d.setColor(getColor());
            g2d.drawOval(boxCorner.x, boxCorner.y, myRadius*2, myRadius*2);
        }
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
