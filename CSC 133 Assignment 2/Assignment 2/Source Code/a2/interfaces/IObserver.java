package a2.interfaces;

import a2.GameWorld;
import a2.GameWorldProxy;

public interface IObserver {
	public void update (GameWorldProxy proxy, Object arg);
}
