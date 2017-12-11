package fr.alma2017.clientServer;

import java.util.ArrayList;
import java.util.List;
import fr.alma2017.api.client.IClient;
import fr.alma2017.api.clientServer.IClientServerConfiguration;
import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.configuration.IConfiguration;
import fr.alma2017.api.configuration.IInterfaceConfiguration;
import fr.alma2017.api.connecteur.IConnecteur;
import fr.alma2017.api.server.IConnectionManager;
import fr.alma2017.api.server.IServer;
import fr.alma2017.client.Client;
import fr.alma2017.configurationClass.AConfiguration;
import fr.alma2017.configurationClass.InterfaceConfiguration;
import fr.alma2017.exception.NotProxiedClassException;
import fr.alma2017.proxy.Proxifieur;
import fr.alma2017.server.Server;

public class ClientServerConfiguration extends AConfiguration implements IConfiguration, IClientServerConfiguration {

	public ClientServerConfiguration(IInterfaceConfiguration interfaceConfiguration, List<IComposant> innerComposants,
			List<IConnecteur> connecteurs) {
		this.interfaceConfiguration = interfaceConfiguration;
		this.composantsInternes = innerComposants;
		this.connecteurs = connecteurs;
	}
	
	public ClientServerConfiguration() throws NotProxiedClassException {
		List<Class<?>> portRequis = new ArrayList<Class<?>>();
		List<Class<?>> portFournis = new ArrayList<Class<?>>();
		portRequis.add(IClient.class);
		portRequis.add(IServer.class);
		//portFournis.add(IRPC.class);
		
		interfaceConfiguration = new InterfaceConfiguration(portRequis, portFournis);
		composantsInternes = new ArrayList<IComposant>();
		connecteurs = new ArrayList<IConnecteur>();
		
		//instanciation du serveur
		IServer server = (IServer) Proxifieur.getProxyFor(Server.getServer(), IServer.class);
		composantsInternes.add(server);
		
		//instanciation du client
		IClient client = (IClient) Proxifieur.getProxyFor(new Client(), IClient.class);
		composantsInternes.add(client);
    
		//instanciation des connecteurs
		//TODO
		
	}
	
	@Override
	public IInterfaceConfiguration getInterface() {
		return this.interfaceConfiguration;
	}

	@Override
	public List<IConnecteur> getConnecteurs() {
		return this.connecteurs;
	}

	@Override
	public List<IComposant> getComposantsInternes() {
		return this.composantsInternes;
	}

	@Override
	public void notify(Object source) {
		if(source instanceof List<?>) {	
			List<?> listeSource = (List<?>) source;
			if(Main.Sysout) {
				if (listeSource.size() == 3 ) {
				System.out.println("Notification pour la classe " + this.getClass().getName() + " : " + 
						listeSource.get(0) + " : " + listeSource.get(2) );
				}
				else if (listeSource.size() > 3){
					System.out.println("Notification pour " + this.getClass().getName() + " : " + 
							listeSource.get(1) + " : " + listeSource.get(3) );
					//System.out.println("interface qui agit " + listeSource.get(0));
				}
			}
			try{
				System.out.println("\n-----------------\n");
			    Thread.sleep(1000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			System.out.println("listeSource.get(0) instanceof String : " + listeSource.get(0) instanceof String);
			if (listeSource.get(0) instanceof String) {
				this.getServer().sendMessage(listeSource);
			}
			else if (listeSource.get(0).equals(IServer.class)) {
				System.out.println("piooiesoingepnoieg");
				this.getClient().receiveAnswer(listeSource.subList(1, listeSource.size()));
			}
			
			//this.getServer().sendMessage((List<?>) source);
		}
	}
	
	@Override
	public void sendMessage(IServer server, Object source) {
		
	}

	@Override
	public void sendAnswer(List<?> subList) {
		
	}
	
	@Override
	public IServer getServer() {
		IServer res = null;
		for(IComposant composant : this.composantsInternes) {
			if(composant instanceof IServer) {
				res = (IServer) composant;
			}
		}
		return res;
	}

	@Override
	public IClient getClient() {
		IClient res = null;
		for(IComposant composant : this.composantsInternes) {
			if(composant instanceof IClient) {
				res = (IClient) composant;
			}
		}
		return res;
	}
}
