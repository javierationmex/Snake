package a4.interfaces;

public interface IObservable {
	public void addObserver (IObserver obs);
	public void notifyObservers();

}
