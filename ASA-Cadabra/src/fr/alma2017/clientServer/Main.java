package fr.alma2017.clientServer;

import fr.alma2017.api.IObservable;
import fr.alma2017.api.client.IClient;
import fr.alma2017.api.clientServer.IClientServerConfiguration;
import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.configuration.IConfiguration;
import fr.alma2017.api.connecteur.IConnecteur;
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

	public static void bindComposant(IConfiguration configuration) {
		for(IComposant composant : configuration.getComposantsInternes()) {
			if(composant instanceof IObservable) {
				configuration.createBinding(configuration, (IObservable)composant);
			}
		}
	}
	
	public static boolean Sysout = true;	
	
	public static void main(String[] args) throws NotProxiedClassException {
		Main.Sysout = false;
		IClientServerConfiguration clientServeurConfig = (IClientServerConfiguration) 
				Proxifieur.getProxyFor(new ClientServerConfiguration(), IClientServerConfiguration.class);

		Main.bindComposant(clientServeurConfig);
		System.out.println();
		IServerConfiguration serverConfiguration = (IServerConfiguration) 
				Proxifieur.getProxyFor(new ServerConfiguration(clientServeurConfig.getServer()), IServerConfiguration.class);
		Main.bindComposant(serverConfiguration);
		
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

		client.makeMessage();
		client.setMessage("Piou");
		client.sendMessage();
	}

}
