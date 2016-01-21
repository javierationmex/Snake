package a4.gameObjects;

import a4.GameWorldProxy;
import a4.interfaces.ICollider;
import a4.interfaces.IDrawable;

import java.awt.*;
import java.awt.geom.AffineTransform;
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
    private AffineTransform myTranslation;
    private AffineTransform myRotation;
    private AffineTransform myScale;

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
        
        myTranslation = new AffineTransform();
        myRotation = new AffineTransform();
        myScale = new AffineTransform();
        
        translate(getLocation().getX(), getLocation().getY());
        
	}

    public Wall(){
        getLocation().setX((float)randInt(17,984));
        getLocation().setY((float)randInt(17,984));

        width = randInt(6,984);
        if (width < 10){
            height = randInt(200,984);
        } else {
            
            height = randInt(6,25);
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
	
	

    public AffineTransform getMyTranslation() {
		return myTranslation;
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
        Point boxCorner = new Point(-getWidth()/2, -getHeight()/2);
    	
        g.setColor(Color.DARK_GRAY);
        g.fillRect(boxCorner.x, boxCorner.y, width, height);
        g2d.setTransform(saveAt);
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
        } else if (otherObject instanceof Weasel){
        	
        	int L2 = (int)this.getLocation().getX() - width/2;
            int R2 = (int)this.getLocation().getX() + width/2;
            int T2 = (int)this.getLocation().getY() + height/2;
            int B2 = (int)this.getLocation().getY() - height/2;
            
            int L1 = (int)((Weasel) otherObject).getMyTranslation().getTranslateX() - ((Weasel) otherObject).getSize()/2;
            int R1 = (int)((Weasel) otherObject).getMyTranslation().getTranslateY() + ((Weasel) otherObject).getSize()/2;
            int T1 = (int)((Weasel) otherObject).getMyTranslation().getTranslateY() + ((Weasel) otherObject).getSize()/2;
            int B1 = (int)((Weasel) otherObject).getMyTranslation().getTranslateY() - ((Weasel) otherObject).getSize()/2;
        	
            if (!((R1 < L2) || (L1 > R2))){
                if (!((B1 > T2) || (T1 < B2))){
                    result = true;
                }
            }
        	
        }


        return result;
    }

    @Override
    public void handleCollision(GameObject otherObject) {
    	if (otherObject instanceof Snake){
    		gWP.collisionWithWall();
    	} else if (otherObject instanceof Weasel){
    		System.out.println("Weasel hit wall");
    		((Weasel)otherObject).weaselBounceOffWall((Weasel)otherObject, this);
    	}
        
    }
}
