package fr.alma2017.composantClass;

import java.util.List;
import fr.alma2017.api.IObservable;
import fr.alma2017.api.IObserver;
import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.composant.IInterfaceComposantRequise;

public class InterfaceComposantRequise implements IInterfaceComposantRequise {
	private List<Class<?>> portRequis;
	private List<Class<?>> serviceRequis;

	public InterfaceComposantRequise(List<Class<?>> portRequis, List<Class<?>> serviceRequis) {
		super();
		this.portRequis = portRequis;
		this.serviceRequis = serviceRequis;
	}

	@Override
	public List<Class<?>> getServiceRequis() {
		return this.serviceRequis;
	}
	
	@Override
	public List<Class<?>> getPortRequis() {
		return this.portRequis;
	}
	
	@Override
	public boolean havePortRequis(IComposant composant) {
		boolean res = true;
		int i = 0;
		List<IObserver> portConnecte = ((IObservable) composant).getObserver();
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
