package fr.alma2017.server;

import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.server.IServer;
import fr.alma2017.composantClass.AComposant;

public class Server extends AComposant implements IComposant, IServer {
	private static Server server;
	
	private Server() {
		
	}

	public static IServer getServer() {
		if(server == null) {
			server = new Server();
		}
		return server;
	}
}
