package a4.gameObjects;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

import a4.interfaces.ICollider;
import a4.interfaces.IDrawable;
import a4.interfaces.ISelectable;
import a4.interfaces.IStrategy;

public class Weasel extends Moveable implements IDrawable, ICollider, ISelectable{

	protected IStrategy currStrategy;
    private SnakeHead snakeHead;
    private int i;
    private boolean isSelected;
    private AffineTransform myTranslation;
    private AffineTransform myRotation;
    private AffineTransform myScale;
	
	public Weasel(SnakeHead snakeHead) {
		getLocation().setX((float)randInt(7,994));
		getLocation().setY((float)randInt(7,994));

		setHeading(randInt(0,360));
		
		setSpeed(10);//randInt(1,10));
        setSize(16);

        this.snakeHead = snakeHead;
		
		super.setColor(Color.GREEN);

		currStrategy = new BoundaryMovement(this);

		myTranslation = new AffineTransform();
        myRotation = new AffineTransform();
        myScale = new AffineTransform();
        
        
        translate(getLocation().getX(), getLocation().getY());
//        rotate(getHeading());
		
	}
	
	public void setStrategy(){
		if (currStrategy instanceof BoundaryMovement){
			currStrategy = new ChaseMovement(this);
		} else if (currStrategy instanceof ChaseMovement){
            setHeading(randInt(0,360));
            currStrategy = new BoundaryMovement(this);
        }

	}
	
	public void weaselBounceOffWall(Weasel weas, Wall wal){
		int angle = weas.getHeading();
		if (angle > 0 && angle < 90){
			if (weas.getMyTranslation().getTranslateX() > wal.getMyTranslation().getTranslateX()){
				weas.setHeading((-1)* angle + 360);
			} else if (weas.getMyTranslation().getTranslateY() < wal.getMyTranslation().getTranslateY()){
				weas.setHeading(angle + 90);
			}
		}
		
		
	}
	
	public void invokeStrategy(){
		currStrategy.apply();
	}


    public SnakeHead getSnakeHead() {
        return snakeHead;
    }

    @Override
	public void move(int rate) {

        i++;
        if (i == (10 * (1000/ rate))){
            setStrategy();
            i = 0;
        }

		invokeStrategy();
	}

	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	@Override
	public String toString(){
		return "Weasel: loc=" + getLocation().getX() + "," + getLocation().getY() + 
				" color=[" + getColor().getRed() + "," + getColor().getGreen() + 
				"," + getColor().getBlue() + "]" + " speed=" + getSpeed() + " heading=" + getHeading() + " strategy=" + currStrategy.toString();
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
    
    public void rotate(double radians){
		myRotation.rotate(radians);
	}

    public AffineTransform getMyTranslation() {
		return myTranslation;
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
        Point boxCorner = new Point(-getSize()/2, -getSize()/2);
    	
    	
        if (isSelected()){
            Color c = new Color((255-getColor().getRed()), (255-getColor().getGreen()), (255-getColor().getBlue()));
            g.setColor(c);
            g.drawRect(boxCorner.x, boxCorner.y, getSize(), getSize());
        } else {
            g.setColor(getColor());
            g.drawRect(boxCorner.x, boxCorner.y, getSize(), getSize());
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
