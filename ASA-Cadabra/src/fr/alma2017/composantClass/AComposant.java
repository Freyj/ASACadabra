package fr.alma2017.composantClass;

import java.util.ArrayList;
import java.util.List;

import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.composant.IInterfaceComposantFournie;
import fr.alma2017.api.composant.IInterfaceComposantRequise;

public class AComposant implements IComposant {

	protected IInterfaceComposantRequise interfaceRequise;
	protected IInterfaceComposantFournie interfaceFournie;
	protected List<IComposant> innerComposants;

	public AComposant() {
		super();
		this.interfaceRequise = new InterfaceComposantRequise();
		this.interfaceFournie = new InterfaceComposantFournie();
		this.innerComposants = new ArrayList<IComposant>();
	}

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