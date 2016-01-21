package a4.gameObjects;

import a4.interfaces.ICollider;
import a4.interfaces.IDrawable;
import a4.interfaces.ISelectable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

/**
 * @author Javier G
 * Class for a Bird object with a Location, size, speed, heading, and Color
 * Extends Moveable
 */
public class Bird extends Moveable implements IDrawable, ICollider, ISelectable{
    private boolean isSelected;
    private int myRadius;
    private AffineTransform myTranslation;
    private AffineTransform myRotation;
    private AffineTransform myScale;
	
	/**
	 * Constructor to initialize its Location x and y, size, heading, speed, and color
	 * with random values
	 */
	public Bird(){
		
		getLocation().setX((float)randInt(7,994));
		getLocation().setY((float)randInt(7,994));

		setSize(16);
        myRadius = 4;

		setHeading(randInt(0, 360));
        isSelected = false;
		
		super.setColor(Color.PINK);

        myTranslation = new AffineTransform();
        myRotation = new AffineTransform();
        myScale = new AffineTransform();

        translate(getLocation().getX(), getLocation().getY());
        scale(2,2);
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
        if ( (px >= xLoc) && (px <= xLoc + (getSize() + getSize()/2))
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

        //Draw itself here
        if (isSelected()){
            Color c = new Color((255-getColor().getRed()), (255-getColor().getGreen()), (255-getColor().getBlue()));
            g.setColor(c);
            g2d.fillOval(boxCorner.x, boxCorner.y, myRadius*2, myRadius*2);
            //g.fillOval((int)getLocation().getX() - (getSize()/2), (int)getLocation().getY() - (getSize()/2), getSize() + (getSize()/2), getSize());
        } else {
            g.setColor(getColor());
            g2d.fillOval(boxCorner.x, boxCorner.y, myRadius*2, myRadius*2);
            //g.fillOval((int)getLocation().getX() - (getSize()/2), (int)getLocation().getY() - (getSize()/2), getSize() + (getSize()/2), getSize());
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
