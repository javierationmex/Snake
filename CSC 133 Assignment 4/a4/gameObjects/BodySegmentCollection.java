package a4.gameObjects;

import java.util.Iterator;
import java.util.Vector;

import a4.interfaces.ICollection;

/**
 * @author Javier G
 * Class to have a collection of body segments
 */
public class BodySegmentCollection implements ICollection{
	
	private Vector<BodySegment> theCollection;
	
	/**
	 * Constructor to instantiate theCollection as a Vector
	 */
	public BodySegmentCollection(){
		theCollection = new Vector<BodySegment>();
	}


    /**
	 * Add element to the Vector
	 * @see a4.interfaces.ICollection#add(java.lang.Object)
	 */
	public void add(Object newObject){
		theCollection.addElement((BodySegment) newObject);
	}
	
	/**
	 * Add element to the Vector in a specific location
	 * @param i
	 * @param newObject
	 */
	public void add(int i, Object newObject){
		theCollection.add(i, (BodySegment) newObject);
	}
	
	/**
	 * Remove an object from the collection
	 * @see a4.interfaces.ICollection#remove(java.lang.Object)
	 */
	@Override
	public void remove(Object newObject) {
        theCollection.removeElement(newObject);
		// Not needed for this collection
	}

    /**
	 * Getter for the BodySegmentIterator
	 * @return
	 */
	public Iterator<Object> getIterator(){
		return new BodySegmentIterator();
	}
	
	/**
	 * @author Javier G
	 * Iterator class to cycle through the objects in the collection
	 */
	private class BodySegmentIterator implements Iterator<Object> {
		private int currElementIndex;

		/**
		 * Constructor to initialize currElementIndex to -1
		 */
		public BodySegmentIterator(){
			currElementIndex = -1;
		}


		/**
		 * Check to see if there is a next element in the collection.
		 * Return true if there is, false if there isn't
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			if (theCollection.size() <= 0) return false;
			if (currElementIndex == theCollection.size() - 1)
				return false;
			return true;
		}

		/**
		 * Return the next index in the collection
		 * @see java.util.Iterator#next()
		 */
		@Override
		public Object next() {
			currElementIndex ++;
			return (theCollection.elementAt(currElementIndex));
		}
	}
}


