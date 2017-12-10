package fr.alma2017.api.composant;

import java.util.List;

public interface IInterfaceComposantRequise {

	public List<Class<?>> getServiceRequis();
	public List<Class<?>> getPortRequis();
	public boolean havePortRequis(IComposant composant);
}
