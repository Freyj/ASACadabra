package fr.alma2017.clientServer;

import java.util.ArrayList;
import java.util.List;

import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.configuration.IConfiguration;
import fr.alma2017.api.configuration.IInterfaceConfiguration;
import fr.alma2017.api.connecteur.IConnecteur;
import fr.alma2017.client.Client;
import fr.alma2017.server.Server;

/**
 * La configuration connaît tous les composants du système
 * 
 */
public class ClientServerConfiguration implements IConfiguration {

	private IInterfaceConfiguration interfaceConfiguration;
	private List<IComposant> innerComposants;
	private List<IConnecteur> connecteurs;

	@Override
	public IInterfaceConfiguration getInterface() {
		return this.interfaceConfiguration;
	}

	@Override
	public List<IConnecteur> getConnecteurs() {
		return this.connecteurs;
	}

	@Override
	public List<IComposant> getInnerComposants() {
		return this.innerComposants;
	}
	
	public ClientServerConfiguration() {
		innerComposants = new ArrayList<IComposant>();
		connecteurs = new ArrayList<IConnecteur>();
		
		
		Server serveur = new Server();
		innerComposants.add(serveur);
		
		Client client = new Client();
		innerComposants.add(client);
		
		
	}

}
