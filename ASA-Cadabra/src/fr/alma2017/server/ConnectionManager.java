package fr.alma2017.server;

import java.util.List;

import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.server.IConnectionManager;
import fr.alma2017.clientServer.Main;
import fr.alma2017.composantClass.AComposant;

public class ConnectionManager extends AComposant implements IComposant, IConnectionManager {

	public ConnectionManager() {

	}

	public void requestConnection(Object message) {
		if (Main.Sysout) {
			System.out.println("A connection has been requested");
		}
	}

	@Override
	public void notify(Object source) {
		if(source instanceof List<?>) {	
			if(Main.Sysout) {
				System.out.println("\t\tNotification pour " + this.getClass().getName() + " : " + 
						((List<Object>) source).get(0) + " : " + ((List<Object>) source).get(2) );
			}
		}
	}
}
