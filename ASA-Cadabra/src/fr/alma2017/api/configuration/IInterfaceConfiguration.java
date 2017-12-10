package fr.alma2017.api.configuration;

import java.util.List;

import fr.alma2017.api.IObservable;

public interface IInterfaceConfiguration {

	public void createBinding(IConfiguration configuration, IObservable composant);

	public List<Class<?>> getPortRequis();

	public List<Class<?>> getPortFournis();

	public boolean havePortRequis(IConfiguration configuration);

}
