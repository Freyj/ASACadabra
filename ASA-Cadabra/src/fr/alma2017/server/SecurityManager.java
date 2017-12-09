package fr.alma2017.server;

import java.util.ArrayList;
import java.util.List;

import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.composant.IInterfaceComposantFournie;
import fr.alma2017.api.composant.IInterfaceComposantRequise;
import fr.alma2017.api.server.ISecurityManager;
import fr.alma2017.composantClass.AComposant;

public class SecurityManager extends AComposant implements IComposant, ISecurityManager {

	public SecurityManager() {
		super();
	}

}
