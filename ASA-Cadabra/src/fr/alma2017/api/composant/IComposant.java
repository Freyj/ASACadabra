package fr.alma2017.api.composant;

import java.util.List;

public interface IComposant {
	public IInterfaceComposantFournie getInterfaceFournie();
	public IInterfaceComposantRequise getInterfaceRequise();
	public List<IComposant> getInnerComposants();
	
}
