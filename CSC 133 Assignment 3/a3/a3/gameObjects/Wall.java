package a3.gameObjects;

import a3.GameWorldProxy;
import a3.interfaces.ICollider;
import a3.interfaces.IDrawable;

import java.awt.*;
import java.util.Random;

/**
 * @author Javier G
 * Class for a wall object with a Location, width, height, and Color
 * Extends Fixed
 */
public class Wall extends Fixed implements IDrawable, ICollider{
	private int width;
	private int height;
    private GameWorldProxy gWP;

	/**
	 * Constructor to initialize its Location x and y, width, height, and color
	 * with random values
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Wall(float x, float y, int width, int height, GameWorldProxy gwp){
		getLocation().setX(x);
		getLocation().setY(y);
		this.width = width;
		this.height = height;
		super.setColor(new Color(randInt(0,255),randInt(0,255),randInt(0,255)));

        this.gWP = gwp;
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
		return "Wall: loc=" + getLocation().getX() + "," + getLocation().getY() + 
				" color=[" + getColor().getRed() + "," + getColor().getGreen() +
				"," + getColor().getBlue() + "]" + " width=" + width + 
				" height=" + height + " age=" + getAge();
	}

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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
        g.setColor(Color.DARK_GRAY);
        g.fillRect((int)(getLocation().getX() - (width / 2)), (int)(getLocation().getY() - (height / 2)), width, height);
    }

    @Override
    public boolean collidesWith(GameObject otherObject) {
        boolean result = false;
        if (otherObject instanceof Snake){

            int L2 = (int)this.getLocation().getX() - width/2;
            int R2 = (int)this.getLocation().getX() + width/2;
            int T2 = (int)this.getLocation().getY() - height/2;
            int B2 = (int)this.getLocation().getY() + height/2;

            int L1 = (int)((Snake) otherObject).getSnakeHead().getLocation().getX() - ((Snake) otherObject).getSnakeHead().getSize()/2;
            int R1 = (int)((Snake) otherObject).getSnakeHead().getLocation().getX() + ((Snake) otherObject).getSnakeHead().getSize()/2;
            int T1 = (int)((Snake) otherObject).getSnakeHead().getLocation().getY() - ((Snake) otherObject).getSnakeHead().getSize()/2;
            int B1 = (int)((Snake) otherObject).getSnakeHead().getLocation().getY() + ((Snake) otherObject).getSnakeHead().getSize()/2;


            if (!((R1 < L2) || (L1 > R2))){
                if (!((B1 < T2) || (T1 > B2))){
                    result = true;
                }
            }
        }


        return result;
    }

    @Override
    public void handleCollision(GameObject otherObject) {
        gWP.collisionWithWall();
    }
}
