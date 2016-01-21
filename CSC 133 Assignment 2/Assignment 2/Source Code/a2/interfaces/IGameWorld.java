package a2.interfaces;

public interface IGameWorld {

	public void setSound(boolean sound);
	public boolean isSound();
	public int getClock();
	public int getLives();
	public int getScore();
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
	
}
