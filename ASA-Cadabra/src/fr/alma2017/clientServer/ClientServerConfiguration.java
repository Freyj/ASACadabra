package fr.alma2017.clientServer;

import java.util.ArrayList;
import java.util.List;

import fr.alma2017.api.IObservable;
import fr.alma2017.api.client.IClient;
import fr.alma2017.api.clientServer.IClientServerConfiguration;
import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.configuration.IInterfaceConfiguration;
import fr.alma2017.api.connecteur.IConnecteur;
import fr.alma2017.api.server.IServer;
import fr.alma2017.client.Client;
import fr.alma2017.configurationClass.AConfiguration;
import fr.alma2017.configurationClass.InterfaceConfiguration;
import fr.alma2017.exception.NotProxiedClassException;
import fr.alma2017.proxy.Proxifieur;
import fr.alma2017.server.Server;

public class ClientServerConfiguration extends AConfiguration implements IClientServerConfiguration {

	public ClientServerConfiguration(IInterfaceConfiguration interfaceConfiguration, List<IComposant> innerComposants,
			List<IConnecteur> connecteurs) {
		this.setInterface(interfaceConfiguration);
		this.setComposantsInternes(innerComposants);
		this.setConnecteurs(connecteurs);
	}
	
	public ClientServerConfiguration() throws NotProxiedClassException {
		
		this.setInterface(new InterfaceConfiguration());
		this.setComposantsInternes(new ArrayList<IComposant>());
		this.setConnecteurs(new ArrayList<IConnecteur>());
		
		//instanciation du serveur
		IServer server = (IServer) Proxifieur.getProxyFor(Server.getServer(), IServer.class);
		composantsInternes.add(server);
		//passage du serveur à la config serveur
		
		//instanciation du client
		IClient client = (IClient) Proxifieur.getProxyFor(new Client(), IClient.class);
		composantsInternes.add(client);

		for(IComposant composant : this.composantsInternes) {
			System.out.println("CSC bind : " + composant.getClass().getName());
			if(composant instanceof IObservable) {
				System.out.println("\t Is IObservable" + composant.getClass().getName());
				this.getInterface().createBinding(this, (IObservable)composant);
			}
		}
		
		//instanciation des connecteurs
		//TODO
		
	}

	@Override
	public void notify(Object source) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IServer getServer() {
		// TODO Auto-generated method stub
		return null;
	}
}
