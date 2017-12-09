package fr.alma2017.clientServer;

import java.util.ArrayList;
import java.util.List;

import fr.alma2017.api.IObservable;
import fr.alma2017.api.client.IClient;
import fr.alma2017.api.clientServer.IClientServerConfiguration;
import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.configuration.IConfiguration;
import fr.alma2017.api.configuration.IInterfaceConfiguration;
import fr.alma2017.api.connecteur.IConnecteur;
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
		
		interfaceConfiguration = new InterfaceConfiguration();
		composantsInternes = new ArrayList<IComposant>();
		connecteurs = new ArrayList<IConnecteur>();
		
		//instanciation du serveur
		IServer server = (IServer) Proxifieur.getProxyFor(Server.getServer(), IServer.class);
		composantsInternes.add(server);
		//passage du serveur � la config serveur
		
		//instanciation du client
		IClient client = (IClient) Proxifieur.getProxyFor(new Client(), IClient.class);
		composantsInternes.add(client);

		for(IComposant composant : this.composantsInternes) {
			System.out.println("CSC bind : " + composant.getClass().getName());
			if(composant instanceof IObservable) {
				System.out.println("\tIs IObservable " + composant.getClass().getName());
				this.interfaceConfiguration.createBinding(this, (IObservable)composant);
			}
		}
		
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
		
	}

	@Override
	public IServer getServer() {
		return Server.getServer();
	}

}
