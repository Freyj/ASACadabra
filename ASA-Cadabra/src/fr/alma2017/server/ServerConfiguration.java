package fr.alma2017.server;

import java.util.ArrayList;
import java.util.List;

import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.composant.IInterfaceComposantFournie;
import fr.alma2017.api.composant.IInterfaceComposantRequise;
import fr.alma2017.api.configuration.IConfiguration;
import fr.alma2017.api.configuration.IInterfaceConfiguration;
import fr.alma2017.api.connecteur.IConnecteur;
import fr.alma2017.api.server.IBaseDonnees;
import fr.alma2017.api.server.IConnectionManager;
import fr.alma2017.api.server.ISecurityManager;
import fr.alma2017.api.server.IServer;
import fr.alma2017.api.server.IServerConfiguration;
import fr.alma2017.configurationClass.InterfaceConfiguration;

public class ServerConfiguration implements IConfiguration, IServerConfiguration {
	
	private IInterfaceConfiguration interfaceConfiguration;
	private List<IComposant> innerComposants;
	private List<IConnecteur> connecteurs;

	public ServerConfiguration(List<IConnecteur> connecteurs) {
		super();
		this.interfaceConfiguration = new InterfaceConfiguration();
		this.innerComposants = new ArrayList<IComposant>();
		IServer server = new Server();
		ISecurityManager securityManager = new SecurityManager();
		IConnectionManager connectionManager = new ConnectionManager();
		IBaseDonnees baseDonnees = new BaseDonnees();
		this.innerComposants.add(server);
		this.innerComposants.add(securityManager);
		this.innerComposants.add(connectionManager);
		this.innerComposants.add(baseDonnees);
		this.connecteurs = new ArrayList<IConnecteur>();
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
	public List<IComposant> getInnerComposants() {
		return this.innerComposants;
	}

}
