package fr.alma2017.server;

import java.util.ArrayList;
import java.util.List;
import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.server.IBaseDonnees;
import fr.alma2017.composantClass.AComposant;
import fr.alma2017.composantClass.InterfaceComposantFournie;

public class BaseDonnees extends AComposant implements IComposant, IBaseDonnees {

	public BaseDonnees() {
		super();
		List<Class<?>> portFournis = new ArrayList<Class<?>>();
		List<Class<?>> serviceFournis = new ArrayList<Class<?>>();
		portFournis.add(IBaseDonnees.class);
		serviceFournis.add(IBaseDonnees.class);
		this.interfaceFournie = new InterfaceComposantFournie(portFournis, serviceFournis);
	}
	
	
}
