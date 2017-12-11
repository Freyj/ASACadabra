package fr.alma2017.server;

import java.util.List;

import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.server.ISecurityManager;
import fr.alma2017.clientServer.Main;
import fr.alma2017.composantClass.AComposant;

public class SecurityManager extends AComposant implements IComposant, ISecurityManager {

	public SecurityManager() {
		
	}

	@Override
	public void notify(Object source) {
		System.out.println("I'm in your security manager, stealing your data");
		if (source instanceof List<?>) {
			List<Object> sourceList = (List<Object>) source;
			sourceList.add(0,ISecurityManager.class);
		}
	}

	@Override
	public void authentify(List<?> source) {
		if (Main.Sysout) {
			System.out.println("An authentification attempt is being made");
		}
		
	}
}
