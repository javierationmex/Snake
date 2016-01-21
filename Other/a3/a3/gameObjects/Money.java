package a3.gameObjects;

import java.awt.*;
import java.util.Random;

import a3.interfaces.ICollider;
import a3.interfaces.IColorChangeable;
import a3.interfaces.IDrawable;
import a3.interfaces.ISelectable;

/**
 * @author Javier G
 * Class for a Money object with a Location, value and Color.
 * Extends Fixed, implements IColorChangeable
 */
public class Money extends Fixed implements IColorChangeable, IDrawable, ICollider, ISelectable{
	private int value;
    private boolean collidedWith;
    private boolean isSelected;

	
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

        collidedWith = false;

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
	 * @see a3.interfaces.IColorChangeable#changeColor(int, int, int)
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

    @Override
    public void draw(Graphics g) {
        //g.setColor(getColor());
//        g.setColor(Color.YELLOW);
//        g.drawOval((int)getLocation().getX() - (getSize()/2), (int)getLocation().getY() - (getSize()/2), getSize(), getSize());
//        g.drawString( "" + value, (int)getLocation().getX() - 4, (int)getLocation().getY() + 4);

        if (isSelected()){
            Color c = new Color((255-getColor().getRed()), (255-getColor().getGreen()), (255-getColor().getBlue()));
            g.setColor(c);
            g.drawOval((int)getLocation().getX() - (getSize()/2), (int)getLocation().getY() - (getSize()/2), getSize(), getSize());
            g.drawString("" + value, (int) getLocation().getX() - 4, (int) getLocation().getY() + 4);
        } else {
            g.setColor(getColor());
            g.drawOval((int)getLocation().getX() - (getSize()/2), (int)getLocation().getY() - (getSize()/2), getSize(), getSize());
            g.drawString("" + value, (int) getLocation().getX() - 4, (int) getLocation().getY() + 4);
        }

    }

    @Override
    public boolean collidesWith(GameObject otherObject) {
        return false;
    }

    @Override
    public void handleCollision(GameObject otherObject) {

    }
}
