package a2.gameObjects;

import java.util.Iterator;

/**
 * @author Javier G
 * Class to hold the parts of a snake: the snakeHead and the collection of body segments
 * Extends Moveable
 */
public class Snake extends Moveable{
	private SnakeHead snakeHead;
	private BodySegmentCollection bodySegmentCollection = new BodySegmentCollection();;
	private BodySegment bs1, bs2, bs3;
	private float prevPartLocationX, prevPartLocationY, tempPrevPartLocationX, tempPrevPartLocationY;
	private int prevPartHeading, tempPrevPartHeading;
	private int newSegCount;
	
	/**
	 * Constructor to initialize newSegCount to 0, snakeHead, body segment 1, body segment 2 and body segment 3.
	 * Adds the body segments to the body segment collection
	 */
	public Snake(){
		newSegCount = 0;
		
		snakeHead = new SnakeHead();
		
		bs1 = new BodySegment(snakeHead.getHeading(), snakeHead.getLocation().getX(), snakeHead.getLocation().getY()-1);
		bs2 = new BodySegment(bs1.getHeading(), bs1.getLocation().getX(), bs1.getLocation().getY()-1);
		bs3 = new BodySegment(bs2.getHeading(), bs2.getLocation().getX(),bs2.getLocation().getY()-1);
		
		bodySegmentCollection.add(bs1);
		bodySegmentCollection.add(bs2);
		bodySegmentCollection.add(bs3);
		
	}

	/**
	 * Getter for snakeHead
	 * @return
	 */
	public SnakeHead getSnakeHead() {
		return snakeHead;
	}

	/**
	 * Moves the snake components by calling the corresponding
	 * move method from each component as well as keeping track
	 * of important information necessary for the moves.
	 * Set prevPartHeading, prevPartLocationX, and prePartLocationY 
	 * to the current snakeHead values.
	 * Check if newSegCount is greater than 0 to see if new body segments
	 * need to be added. If so, create a new body segment with the
	 * prevPartHeading, prefPartLocationX and prevPartLocationY. Add
	 * the new body segment to the bodySegmentCollection at the beginning.
	 * Subtract one from newSegCount to account for the fact that we created
	 * the new segment already. Call snakeHead's move() method. If newSegCount
	 * is not greater than 0, call snakeHead's move() method. Iterate through the 
	 * body segment collection, save each's heading , locationX and locationY into 
	 * tempPrevPartHeading, tempPrevPartLocationX, and tempPrevPartLocationY before
	 * calling move on it with the values prevPartHeading, prevPartLocationX,
	 * and prevPartLocationY. Assign the temp values to the prev values for the next
	 * segment.
	 * 
	 * @see a2.gameObjects.Moveable#move()
	 */
	public void move(){
		prevPartHeading = snakeHead.getHeading();
		prevPartLocationX = snakeHead.getLocation().getX();
		prevPartLocationY = snakeHead.getLocation().getY();
		int currentSpeed = snakeHead.getSpeed();
	
		if (newSegCount > 0){
			BodySegment bs4;
			switch (prevPartHeading){
			case 0: //Do nothing
				bs4 = new BodySegment(prevPartHeading, prevPartLocationX, prevPartLocationY + (currentSpeed - 1));
				break;
			case 90: //Pretend snake head has collided with body segment
				bs4 = new BodySegment(prevPartHeading, prevPartLocationX + (currentSpeed - 1), prevPartLocationY);
				break;
			case 180: //Pretend snake head has collided with a bird
				bs4 = new BodySegment(prevPartHeading, prevPartLocationX, prevPartLocationY - (currentSpeed - 1));
				break;
			case 270: //Pretend snake head has collided with money
				bs4 = new BodySegment(prevPartHeading, prevPartLocationX - (currentSpeed - 1), prevPartLocationY);
				break;
			default:
				bs4 = new BodySegment(0, 0, 0);
				break;
			}
			bodySegmentCollection.add(0, bs4);
			newSegCount--;
			snakeHead.move();
		} else {
			snakeHead.move();
			Iterator theBodySegments = bodySegmentCollection.getIterator();
//			int currentSpeed = snakeHead.getSpeed();
			
//			if (snakeHead.getSpeed() > 1){
//				int currentSpeed = snakeHead.getSpeed();
//				while (currentSpeed > 1){
					while (theBodySegments.hasNext()){
						BodySegment bs = (BodySegment)theBodySegments.next();
						
						tempPrevPartHeading = bs.getHeading();
						tempPrevPartLocationX = bs.getLocation().getX();
						tempPrevPartLocationY = bs.getLocation().getY();
						
						bs.move(prevPartHeading, prevPartLocationX, prevPartLocationY);
						
//						switch (prevPartHeading){
//						case 0: //Do nothing
//							bs.move(prevPartHeading, prevPartLocationX, prevPartLocationY + (currentSpeed - 1));
//							break;
//						case 90: //Pretend snake head has collided with body segment
//							bs.move(prevPartHeading, prevPartLocationX + (currentSpeed - 1), prevPartLocationY);
//							break;
//						case 180: //Pretend snake head has collided with a bird
//							bs.move(prevPartHeading, prevPartLocationX, prevPartLocationY - (currentSpeed - 1));
//							break;
//						case 270: //Pretend snake head has collided with money
//							bs.move(prevPartHeading, prevPartLocationX - (currentSpeed - 1), prevPartLocationY);
//							break;
//						}
						
						prevPartLocationX = tempPrevPartLocationX;
						prevPartLocationY = tempPrevPartLocationY;
						prevPartHeading = tempPrevPartHeading;
					}
//					currentSpeed--;
//					theBodySegments = bodySegmentCollection.getIterator();
					
//				}
//			}
				
//			while (theBodySegments.hasNext()){
//				BodySegment bs = (BodySegment)theBodySegments.next();
//				
//				tempPrevPartHeading = bs.getHeading();
//				tempPrevPartLocationX = bs.getLocation().getX();
//				tempPrevPartLocationY = bs.getLocation().getY();
//				
//				bs.move(prevPartHeading, prevPartLocationX, prevPartLocationY);
//				
//				prevPartLocationX = tempPrevPartLocationX;
//				prevPartLocationY = tempPrevPartLocationY;
//				prevPartHeading = tempPrevPartHeading;
			
		}
	}
	
	/**
	 * Getter for newSegCount
	 * @return
	 */
	public int getNewSegCount() {
		return newSegCount;
	}

	/**
	 * Setter for newSegCount
	 * @param newSegCount
	 */
	public void setNewSegCount(int newSegCount) {
		this.newSegCount = newSegCount;
	}

	/**
	 * 
	 * Format the snakeHead and the body objects information properly
	 * to be able to display on the screen for the user when they
	 * select to display a map.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		String headString;
		StringBuilder bodyString = new StringBuilder();
		
		headString = snakeHead.toString();
		bodyString = bodyStringMaker();
		
		return headString + "\n" +  bodyString;
		}
	
	/**
	 * Build a String by iterating through the bodySegmentCollection and
	 * calling .toString on each object and appending it to the string
	 * @return
	 */
	public StringBuilder bodyStringMaker(){
		StringBuilder bodyString = new StringBuilder();
		Iterator theBodySegments = bodySegmentCollection.getIterator();
		while (theBodySegments.hasNext()){
			BodySegment bs = (BodySegment)theBodySegments.next();
			bodyString.append(bs.toString());
			bodyString.append("\n");
		}
		return bodyString;
	}
}
