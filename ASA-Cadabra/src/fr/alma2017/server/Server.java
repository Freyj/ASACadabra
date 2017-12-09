package fr.alma2017.server;

import java.util.List;

import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.composant.IInterfaceComposantFournie;
import fr.alma2017.api.composant.IInterfaceComposantRequise;

public class Server implements IComposant {

	private IInterfaceComposantRequise interfaceRequise;
	private IInterfaceComposantFournie interfaceFournie;
	private List<IComposant> innerComposants;

	@Override
	public IInterfaceComposantFournie getInterfaceFournie() {
		return this.interfaceFournie;
	}

	@Override
	public IInterfaceComposantRequise getInterfaceRequise() {
		return this.interfaceRequise;
	}

	@Override
	public List<IComposant> getInnerComposants() {
		return this.innerComposants;
	}

}
