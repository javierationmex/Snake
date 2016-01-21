package a2.gameObjects;

import java.util.Iterator;
import java.util.Vector;

import a2.interfaces.ICollection;
import a2.interfaces.IObserver;

public class ObserversCollection implements ICollection{

	
private Vector<IObserver> theCollection;
	
	//Constructor
	/**
	 * Constructor to instantiate theCollection as a Vector
	 */
	public ObserversCollection(){
		theCollection = new Vector<IObserver>();
	}
	
	/**
	 * Add an element to the Vector
	 * @see a2.interfaces.ICollection#add(java.lang.Object)
	 */
	@Override
	public void add(Object newObject) {
		theCollection.addElement((IObserver) newObject);
	}

	/**
	 * Remove an element from the Vector
	 * @see a2.interfaces.ICollection#remove(java.lang.Object)
	 */
	@Override
	public void remove(Object newObject) {
		// TODO Auto-generated method stub
		theCollection.removeElement(newObject);
	}
	
	/**
	 * Return the iterator
	 * @return
	 */
	public Iterator<Object> getIterator(){
		return new ObserverIterator();
	}

	/**
	 * @author Javier G
	 * Iterator class to cycle through the objects in the collection
	 */
	private class ObserverIterator implements Iterator<Object> {
		private int currElementIndex;

		/**
		 * Constructor to initialize currElementIndex to -1
		 */
		public ObserverIterator(){
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

