package fr.alma2017.api;

import java.util.List;

public interface IObservable {
	public void addObserver(IObserver observer);
	public List<IObserver> getObserver();
	public void setObserver(IObserver observer);
}
