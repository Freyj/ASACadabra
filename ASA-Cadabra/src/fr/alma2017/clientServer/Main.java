package fr.alma2017.clientServer;

import fr.alma2017.api.IObservable;
import fr.alma2017.api.IObserver;
import fr.alma2017.api.client.IClient;
import fr.alma2017.client.Client;
import fr.alma2017.exception.NotProxiedClassException;
import fr.alma2017.proxy.Proxifieur;

/**
 * Lancement d'application
 * 
 *
 */
public class Main {
	
	private static ClientServerConfiguration clientServeurConfig;
	

	
	public static void main(String[] args) {
		clientServeurConfig = new ClientServerConfiguration();
		
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
			
			((IObservable) client).setObserver(observer);
			client.setMessage("Piou");
		} catch (NotProxiedClassException e) {
			e.printStackTrace();
		}
		
		
	}

}
