package fr.alma2017.server;

import java.util.ArrayList;
import java.util.List;

import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.composant.IInterfaceComposantFournie;
import fr.alma2017.api.composant.IInterfaceComposantRequise;
import fr.alma2017.api.server.IConnectionManager;
import fr.alma2017.composantClass.AComposant;

public class ConnectionManager extends AComposant implements IComposant, IConnectionManager {

	public ConnectionManager() {
		super();
	}

}
