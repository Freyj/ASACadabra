package fr.alma2017.server;

import java.util.List;

import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.server.ISecurityManager;
import fr.alma2017.composantClass.AComposant;

public class SecurityManager extends AComposant implements IComposant, ISecurityManager {

	public SecurityManager() {
		super();
	}

	@Override
	public void notify(Object source) {
		
	}

	@Override
	public boolean authentify(List<?> source) {
		// TODO Auto-generated method stub
		return false;
	}
}
