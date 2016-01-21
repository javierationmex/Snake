package a4.gameObjects;

public abstract class GameObject {
	private Location location;
	private GameObjectCollection gameObjectCollection;
	private java.awt.Color color;
    private int size;
	
	public GameObject(){
		gameObjectCollection = new GameObjectCollection();
		location = new Location();
	}

	protected Location getLocation() {
		return location;
	}

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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
