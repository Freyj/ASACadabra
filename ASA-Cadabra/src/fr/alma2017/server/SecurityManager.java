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
		if(source instanceof List<?>) {	
			if(Main.Sysout) {
				System.out.println("I'm in your security manager, stealing your data");
			}
		}
	}

	@Override
	public void authentify(List<?> source) {
		if (Main.Sysout) {
			System.out.println("An authentification attempt has been made");
		}
		
	}
}
