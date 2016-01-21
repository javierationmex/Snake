package a4.gameObjects;

/**
 * @author Javier G
 * Abstract class for objects that are fixed and won't change location
 */
public abstract class Fixed extends GameObject {
	private int age = 0;

	/**
	 * Getter for age
	 * @return
	 */
	protected int getAge() {
		return age;
	}
	
	/**
	 * Increment age by 1
	 */
	public void incrementAge(){
		age++;
	}
}
