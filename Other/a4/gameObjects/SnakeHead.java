package a4.gameObjects;

import java.awt.*;
import java.util.Random;

import a4.GameWorldProxy;
import a4.interfaces.IDrawable;
import a4.interfaces.ISteerable;

/**
 * @author Javier G
 * Class for the object SnakeHead with deltaX, deltaY, oldX, oldY, Location, heading, speeed
 * and color
 * Extends Moveable and implements ISteerable
 */
public class SnakeHead extends Moveable implements ISteerable, IDrawable{

    private GameWorldProxy gWP;
	/**
	 * Constructor to initialize Location, heading, and color
	 */
	public SnakeHead(GameWorldProxy gwp){
		
		getLocation().setX((float)500);
		getLocation().setY((float)500);
		
		setHeading(0);
        setSize(14);
		
		super.setColor(new Color(randInt(0,255),randInt(0,255),randInt(0,255)));

        this.gWP = gwp;
	}
	
	/**
	 * Method to switch the heading of the snakeHead based on user input
	 * @see a4.interfaces.ISteerable#turn(char)
	 */
	public void turn(char direction) {
		// TODO Auto-generated method stub
		switch (direction){
		case 'n':
            if (getHeading() != 180)
			    setHeading(0);
		break;
		case 'e':
            if (getHeading() != 270)
			    setHeading(90);
		break;
		case 's':
            if (getHeading() != 0)
			    setHeading(180);
		break;
		case 'w':
            if (getHeading() != 90)
			    setHeading(270);
		break;
		
		}
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
		return "SnakeHead: loc=" + getLocation().getX() + "," + getLocation().getY() + 
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
        Polygon p = new Polygon();
        g.setColor(Color.WHITE);

        switch (getHeading()){
            case 0 :
                p.addPoint((int)getLocation().getX(), (int)getLocation().getY() + (getSize()/2));
                p.addPoint((int)getLocation().getX() - (getSize()/2), (int)getLocation().getY() - (getSize()/2));
                p.addPoint((int)getLocation().getX() + (getSize()/2), (int)getLocation().getY() - (getSize()/2));
                g.fillPolygon(p);
                break;
            case 90 :
                p.addPoint((int)getLocation().getX() + (getSize()/2), (int)getLocation().getY());
                p.addPoint((int)getLocation().getX() - (getSize()/2), (int)getLocation().getY() - (getSize()/2));
                p.addPoint((int)getLocation().getX() - (getSize()/2), (int)getLocation().getY() + (getSize()/2));
                g.fillPolygon(p);
                break;
            case 180 :
                p.addPoint((int)getLocation().getX(), (int)getLocation().getY() - 7);
                p.addPoint((int)getLocation().getX() - (getSize()/2), (int)getLocation().getY() + (getSize()/2));
                p.addPoint((int)getLocation().getX() + (getSize()/2), (int)getLocation().getY() + (getSize()/2));
                g.fillPolygon(p);
                break;
            case 270 :
                p.addPoint((int)getLocation().getX() - (getSize()/2), (int)getLocation().getY());
                p.addPoint((int)getLocation().getX() + (getSize()/2), (int)getLocation().getY() - (getSize()/2));
                p.addPoint((int)getLocation().getX() + (getSize()/2), (int)getLocation().getY() + (getSize()/2));
                g.fillPolygon(p);
                break;
        }
    }


    public boolean collidesWith(GameObject otherObject) {
        boolean result = false;

            int thisCenterX = (int) this.getLocation().getX();
            int thisCenterY = (int) this.getLocation().getY();
            int otherCenterX = (int) otherObject.getLocation().getX();
            int otherCenterY = (int) otherObject.getLocation().getY();

            int dx = thisCenterX - otherCenterX;
            int dy = thisCenterY - otherCenterY;
            int distanceBetweenCentersSqr = (dx * dx + dy * dy);

            int thisRadius = getSize() / 2;
            int otherRadius = otherObject.getSize() / 2;
            int radiiSqr = (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);

            if (distanceBetweenCentersSqr <= radiiSqr) {
                result = true;
            }
        return result;
    }


    public void handleCollision(GameObject otherObject) {
        if (otherObject instanceof Bird){
            if ((otherObject.getLocation().getX() > 1500) || (otherObject.getLocation().getX() < -500) || (otherObject.getLocation().getY() > 1500) || (otherObject.getLocation().getY() <500)){

            } else {
                gWP.collisionWithBird();
            }
        } else if (otherObject instanceof Weasel){
        	System.out.println("Collided with a weasel");
            gWP.collisionWithWeasel();
        } else if (otherObject instanceof Food){
            gWP.collisionWithFood();
        } else if (otherObject instanceof Money){
            ((Money) otherObject).setCollidedWith(true);
            gWP.collisionWithMoney();

        }
    }
}
