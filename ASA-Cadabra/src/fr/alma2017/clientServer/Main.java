package fr.alma2017.clientServer;

import fr.alma2017.api.IObservable;
import fr.alma2017.api.IObserver;
import fr.alma2017.api.client.IClient;
import fr.alma2017.api.clientServer.IClientServerConfiguration;
import fr.alma2017.api.configuration.IConfiguration;
import fr.alma2017.api.server.IServerConfiguration;
import fr.alma2017.client.Client;
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
		
	public static void main(String[] args) throws NotProxiedClassException {
		IClientServerConfiguration clientServeurConfig = (IClientServerConfiguration) 
				Proxifieur.getProxyFor(new ClientServerConfiguration(), IConfiguration.class);
		IServerConfiguration serverConfiguration = (IServerConfiguration) 
				Proxifieur.getProxyFor(new ServerConfiguration(clientServeurConfig.getServer()), IConfiguration.class);
		
		//test (remember extensible)
		try {
			IClient client = (IClient) Proxifieur.getProxyFor(new Client(), IClient.class);
			client.sendMessage();
			IObserver observer = new IObserver(){
				@Override
				public void notify(Object source) {
					System.out.println(source + " is modified");					
				}
			};
			
			((IObservable) client).addObserver(observer);
			client.setMessage("Piou");
		} catch (NotProxiedClassException e) {
			e.printStackTrace();
		}
		
		
	}

}
