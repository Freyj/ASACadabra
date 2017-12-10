package fr.alma2017.configurationClass;

import java.util.List;
import java.util.Set;

import fr.alma2017.api.IObservable;
import fr.alma2017.api.IObserver;
import fr.alma2017.api.configuration.IConfiguration;
import fr.alma2017.api.configuration.IInterfaceConfiguration;

public class InterfaceConfiguration implements IInterfaceConfiguration {
	private List<Class<?>> portRequis;
	private List<Class<?>> portFournis;
	
	public InterfaceConfiguration(List<Class<?>> portRequis, List<Class<?>> portFournis) {
		super();
		this.portRequis = portRequis;
		this.portFournis = portFournis;
	}

	@Override
	public void createBinding(IConfiguration configuration, IObservable composant) {
		((IObservable) composant).addObserver(configuration);
	}

	@Override
	public List<Class<?>> getPortFournis() {
		return this.portFournis;
	}

	@Override
	public List<Class<?>> getPortRequis() {
		return this.portRequis;
	}

	@Override
	public boolean havePortRequis(IConfiguration configuration) {
		boolean res = true;
		int i = 0;
		List<IObserver> portConnecte = ((IObservable) configuration).getObserver();
		while(res && i < this.portRequis.size()) {
			int j = 0;
			boolean resTemp = false;
			while(resTemp && i < this.portRequis.size()) {
				if( portConnecte.get(j).getClass().equals( this.portRequis.get(i) ) ) {
					resTemp = true;
				}
				j = j + 1;
			}
			res = resTemp;
			i = i + 1;
		}
		return res;
	}
}
