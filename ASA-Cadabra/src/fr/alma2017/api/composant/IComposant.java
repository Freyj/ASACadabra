package fr.alma2017.api.composant;

import java.util.List;

import fr.alma2017.api.IObserver;

public interface IComposant extends IObserver {
	public IInterfaceComposantFournie getInterfaceFournie();
	public IInterfaceComposantRequise getInterfaceRequise();
	public List<IComposant> getComposantsInternes();
	
}
