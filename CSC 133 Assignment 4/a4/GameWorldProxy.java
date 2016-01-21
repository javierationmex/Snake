package a4;

import a4.gameObjects.GameObjectCollection;
import a4.interfaces.IGameWorld;
import a4.interfaces.IObservable;
import a4.interfaces.IObserver;

public class GameWorldProxy implements IObservable, IGameWorld{

	private GameWorld realGameWorld;

	
	public GameWorldProxy(GameWorld gameWorld){
		realGameWorld = gameWorld;
	}
	
	@Override
	public void setSound(boolean sound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initLayout() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void changeSnakeHeading(char direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collisionWithBody() {
		// TODO Auto-generated method stub
        realGameWorld.collisionWithBody();
	}

	@Override
	public void collisionWithBird() {
		// TODO Auto-generated method stub
        realGameWorld.collisionWithBird();
	}

	@Override
	public void collisionWithMoney() {
		// TODO Auto-generated method stub
        realGameWorld.collisionWithMoney();
	}

	@Override
	public void collisionWithFood() {
		// TODO Auto-generated method stub
        realGameWorld.collisionWithFood();
	}

	@Override
	public void collisionWithWall() {
		// TODO Auto-generated method stub
        realGameWorld.collisionWithWall();
	}

	@Override
	public void collisionWithWeasel() {
		// TODO Auto-generated method stub
        realGameWorld.collisionWithWeasel();
	}

	@Override
	public void gameClockTick() {
		// TODO Auto-generated method stub
		realGameWorld.gameClockTick();
	}


	@Override
	public void generateMap() {
		// TODO Auto-generated method stub
		realGameWorld.generateMap();
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restartGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addObserver(IObserver obs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSound() {
		return realGameWorld.isSound();
	}

	@Override
	public int getClock() {
		// TODO Auto-generated method stub
		return realGameWorld.getClock();
	}

	@Override
	public int getLives() {
		// TODO Auto-generated method stub
		return realGameWorld.getLives();
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return realGameWorld.getScore();
	}

    @Override
    public int getRate() {
        // TODO Auto-generated method stub
        return realGameWorld.getRate();
    }

    @Override
    public GameObjectCollection getGameObjectCollection() {
        return realGameWorld.getGameObjectCollection();
    }

    @Override
    public boolean isPaused() {
        return realGameWorld.isPaused();
    }


}
