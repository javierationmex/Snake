package a3.gameObjects;

import java.awt.*;
import java.util.Random;

import a3.interfaces.ICollider;
import a3.interfaces.IDrawable;
import a3.interfaces.ISelectable;
import a3.interfaces.IStrategy;

public class Weasel extends Moveable implements IDrawable, ICollider, ISelectable{

	protected IStrategy currStrategy;
    private SnakeHead snakeHead;
    private int i;
    private boolean isSelected;
	
	public Weasel(SnakeHead snakeHead) {
		getLocation().setX((float)randInt(7,994));
		getLocation().setY((float)randInt(7,994));

		setHeading(randInt(0,360));
		setSpeed(10);//randInt(1,10));
        setSize(16);

        this.snakeHead = snakeHead;
		
		super.setColor(Color.GREEN);

		currStrategy = new BoundaryMovement(this);

	}
	
	public void setStrategy(){
		if (currStrategy instanceof BoundaryMovement){
			currStrategy = new ChaseMovement(this);
		} else if (currStrategy instanceof ChaseMovement){
            setHeading(randInt(0,360));
            currStrategy = new BoundaryMovement(this);
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

        if (isSelected()){
            Color c = new Color((255-getColor().getRed()), (255-getColor().getGreen()), (255-getColor().getBlue()));
            g.setColor(c);
            g.drawRect((int) getLocation().getX() - (getSize() / 2), (int) getLocation().getY() - (getSize() / 2), getSize(), getSize());
        } else {
            g.setColor(getColor());
            g.drawRect((int) getLocation().getX() - (getSize() / 2), (int) getLocation().getY() - (getSize() / 2), getSize(), getSize());
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
