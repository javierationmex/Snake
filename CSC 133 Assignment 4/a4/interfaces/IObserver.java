package a4.interfaces;

import a4.GameWorldProxy;

public interface IObserver {
	public void update (GameWorldProxy proxy, Object arg);
}
