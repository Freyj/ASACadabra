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
		System.out.println("YOU HAZ REQUESTED A CONNECTION");
	}


	@Override
	public void notify(Object source) {
		if(source instanceof List<?>) {	
			List<Object> sourceList = (List<Object>) source;
			sourceList.add(0,IConnectionManager.class);
			if(Main.Sysout) {
				System.out.println("Notification pour " + this.getClass().getName() + " : " + 
						sourceList.get(0) + " : " + sourceList.get(2) );
			}
		}
	}
}
