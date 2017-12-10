package fr.alma2017.clientServer;

import fr.alma2017.api.client.IClient;
import fr.alma2017.api.clientServer.IClientServerConfiguration;
import fr.alma2017.api.configuration.IConfiguration;
import fr.alma2017.api.server.IServerConfiguration;
import fr.alma2017.exception.NotProxiedClassException;
import fr.alma2017.proxy.Proxifieur;
import fr.alma2017.server.ServerConfiguration;

/**
 * Lancement d'application
 * 
 *
 */
public class Main {
	
	//private static IClientServerConfiguration clientServeurConfig;
	public static boolean Sysout = true;	
	
	public static void main(String[] args) throws NotProxiedClassException {
		Main.Sysout = false;
		IClientServerConfiguration clientServeurConfig = (IClientServerConfiguration) 
				Proxifieur.getProxyFor(new ClientServerConfiguration(), IConfiguration.class);
		IServerConfiguration serverConfiguration = (IServerConfiguration) 
				Proxifieur.getProxyFor(new ServerConfiguration(clientServeurConfig.getServer()), IConfiguration.class);
		
		Main.Sysout = true;
		System.out.println();
		IClient client = clientServeurConfig.getClient();
		
		/*
		client.sendMessage();
		IObserver observer = new IObserver(){
			@Override
			public void notify(Object source) {
				System.out.println(this.getClass().getName() + " " + source);					
			}
		};
		((IObservable) client).addObserver(observer);
		*/
		client.setMessage("Piou");
		client.sendMessage();
		
		
	}

}
