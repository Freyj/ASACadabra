package fr.alma2017.api.configuration;

import fr.alma2017.api.IObservable;
import fr.alma2017.api.IObserver;
import fr.alma2017.api.composant.IComposant;
import fr.alma2017.server.ServerConfiguration;

public interface IInterfaceConfiguration {

	public void createBinding(IConfiguration configuration, IObserver composant);

}
