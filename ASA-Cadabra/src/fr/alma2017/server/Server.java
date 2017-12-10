package fr.alma2017.server;

import java.util.ArrayList;
import java.util.List;

import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.server.IServer;
import fr.alma2017.clientServer.Main;
import fr.alma2017.composantClass.AComposant;
import fr.alma2017.composantClass.InterfaceComposantFournie;

public class Server extends AComposant implements IComposant, IServer {
	private static Server server;
	
	private Server() {
		List<Class<?>> portFournis = new ArrayList<Class<?>>();
		List<Class<?>> serviceFournis = new ArrayList<Class<?>>();
		this.interfaceFournie = new InterfaceComposantFournie(portFournis, serviceFournis);
	}

	public static IServer getServer() {
		if(server == null) {
			server = new Server();
		}
		return server;
	}
	
	@Override
	public void notify(Object source) {
		if(source instanceof List<?>) {	
			if(Main.Sysout) {
				System.out.println("Notification pour " + this.getClass().getName() + " : " + 
						((List<?>)source).get(0) + " : " + ((List<?>)source).get(2) );
			}
		}
	}

	@Override
	public void sendMessage(List<?> source) {
		
	}
}
