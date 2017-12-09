package fr.alma2017.api;

import fr.alma2017.api.configuration.IConfiguration;

public interface IObservable {
	void addObserver(IObserver observer);

	void setObserver(IObserver observer);
}
