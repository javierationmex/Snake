package a3.interfaces;

import a3.GameWorldProxy;

public interface IObserver {
	public void update (GameWorldProxy proxy, Object arg);
}
