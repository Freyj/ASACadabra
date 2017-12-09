package fr.alma2017.configurationClass;

import fr.alma2017.api.IObservable;
import fr.alma2017.api.IObserver;
import fr.alma2017.api.configuration.IConfiguration;
import fr.alma2017.api.configuration.IInterfaceConfiguration;

public class InterfaceConfiguration implements IInterfaceConfiguration {

	@Override
	public void createBinding(IConfiguration configuration, IObservable composant) {
		((IObservable) composant).addObserver(configuration);
	}

}
