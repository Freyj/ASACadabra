package fr.alma2017.server;

import java.util.ArrayList;
import java.util.List;

import fr.alma2017.api.IObservable;
import fr.alma2017.api.IObserver;
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
	private List<IComposant> composantsInternes;
	private List<IConnecteur> connecteurs;

	public ServerConfiguration(List<IConnecteur> connecteurs, IServer server) {
		super();
		this.interfaceConfiguration = new InterfaceConfiguration();
		this.composantsInternes = new ArrayList<IComposant>();
		ISecurityManager securityManager = new SecurityManager();
		IConnectionManager connectionManager = new ConnectionManager();
		IBaseDonnees baseDonnees = new BaseDonnees();
		this.composantsInternes.add(server);
		this.composantsInternes.add(securityManager);
		this.composantsInternes.add(connectionManager);
		this.composantsInternes.add(baseDonnees);
		for(IComposant composant : this.composantsInternes) {
			if(composant instanceof IObservable) {
				this.interfaceConfiguration.createBinding(this, (IObserver)composant);
			}
		}
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
	public List<IComposant> getComposantsInternes() {
		return this.composantsInternes;
	}

	@Override
	public void notify(Object source) {
		
	}
	
}
