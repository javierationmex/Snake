package a1.gameObjects;

public abstract class GameObject {
	private Location location;
	private GameObjectCollection gameObjectCollection;
	private java.awt.Color color;
	
	public GameObject(){
		gameObjectCollection = new GameObjectCollection();
		location = new Location();
	}

	public Location getLocation() {
		return location;
	}

	protected void setLocation(Location location) {
		this.location = location;
	}

	public java.awt.Color getColor() {
		return color;
	}
	
	public void setColor(java.awt.Color color) {
		this.color = color;
	}

	public void move(){
		
	}

}
