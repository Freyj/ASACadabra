package fr.alma2017.clientServer;

import java.util.ArrayList;
import java.util.List;

import fr.alma2017.api.clientServer.IClientServerIConfiguration;
import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.configuration.IConfiguration;
import fr.alma2017.api.configuration.IInterfaceConfiguration;
import fr.alma2017.api.connecteur.IConnecteur;
import fr.alma2017.client.Client;
import fr.alma2017.server.Server;

public class ClientServerConfiguration implements IConfiguration, IClientServerIConfiguration {

	private IInterfaceConfiguration interfaceConfiguration;
	private List<IComposant> composantsInternes;
	private List<IConnecteur> connecteurs;

	public ClientServerConfiguration(IInterfaceConfiguration interfaceConfiguration, List<IComposant> innerComposants,
			List<IConnecteur> connecteurs) {
		this.interfaceConfiguration = interfaceConfiguration;
		this.composantsInternes = innerComposants;
		this.connecteurs = connecteurs;
	}
	
	public ClientServerConfiguration() {
		
		interfaceConfiguration = null;
		composantsInternes = new ArrayList<IComposant>();
		connecteurs = new ArrayList<IConnecteur>();
		
		//instanciation du serveur
		Server serv = new Server();
		composantsInternes.add(serv);
		//passage du serveur à la config serveur
		
		//instanciation du client
		Client client = new Client();
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
		
	}
	
	

}
