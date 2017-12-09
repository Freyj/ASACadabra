package fr.alma2017.clientServer;

import fr.alma2017.api.IObservable;
import fr.alma2017.api.composant.IComposant;
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
			IComposant pers = (IComposant) Proxifieur.getProxyFor(new Client(), Client.class);
			((Client) pers).sendMessage();
			IObservable observer = new IObservable(){
				public void notify(Object source){
					System.out.println(source + " is modified");
				}

				@Override
				public void setObserver(IObservable observer) {
					// TODO Auto-generated method stub
					
				}
			};
			
			((IObservable) pers).setObserver(observer);
			((Client) pers).setMessage("Piou");
		} catch (NotProxiedClassException e) {
			e.printStackTrace();
		}
		
		
	}

}
