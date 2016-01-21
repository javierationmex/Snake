package a3.interfaces;

import a3.gameObjects.GameObjectCollection;

public interface IGameWorld {

	public void setSound(boolean sound);
	public boolean isSound();
	public int getClock();
	public int getLives();
	public int getScore();
    public int getRate();
	public void initLayout();
	public void changeSnakeHeading(char direction);
	public void collisionWithBody();
	public void collisionWithBird();
	public void collisionWithMoney();
	public void collisionWithFood();
	public void collisionWithWall();
	public void collisionWithWeasel();
	public void gameClockTick();
	public void generateMap();
	public void quit();
	public void restartGame();
	public void addObserver(IObserver obs);
	public void notifyObservers();
    public GameObjectCollection getGameObjectCollection();
    public boolean isPaused();
	
}
