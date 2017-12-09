package fr.alma2017.server;

import java.util.ArrayList;
import java.util.List;

import fr.alma2017.api.IObservable;
import fr.alma2017.api.IObserver;
import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.configuration.IConfiguration;
import fr.alma2017.api.configuration.IInterfaceConfiguration;
import fr.alma2017.api.connecteur.IConnecteur;
import fr.alma2017.api.server.IBaseDonnees;
import fr.alma2017.api.server.IConnectionManager;
import fr.alma2017.api.server.ISecurityManager;
import fr.alma2017.api.server.IServer;
import fr.alma2017.api.server.IServerConfiguration;
import fr.alma2017.configurationClass.InterfaceConfiguration;
import fr.alma2017.exception.NotProxiedClassException;
import fr.alma2017.proxy.Proxifieur;

public class ServerConfiguration implements IConfiguration, IServerConfiguration {
	
	private IInterfaceConfiguration interfaceConfiguration;
	private List<IComposant> composantsInternes;
	private List<IConnecteur> connecteurs;

	public ServerConfiguration(IServer serverFromCSConfiguration) throws NotProxiedClassException {
		super();
		this.interfaceConfiguration = new InterfaceConfiguration();
		this.composantsInternes = new ArrayList<IComposant>();
		ISecurityManager securityManager = (ISecurityManager) Proxifieur.getProxyFor(new SecurityManager(), ISecurityManager.class);
		IConnectionManager connectionManager = (IConnectionManager) Proxifieur.getProxyFor(new ConnectionManager(), IConnectionManager.class);
		IBaseDonnees baseDonnees = (IBaseDonnees) Proxifieur.getProxyFor(new BaseDonnees(), IBaseDonnees.class);
		IServer server = (IServer) Proxifieur.getProxyFor(serverFromCSConfiguration, IServer.class);
		this.composantsInternes.add(server);
		this.composantsInternes.add(securityManager);
		this.composantsInternes.add(connectionManager);
		this.composantsInternes.add(baseDonnees);
		for(IComposant composant : this.composantsInternes) {
			System.out.println("SC bind : " + composant.getClass().getName());
			if(composant instanceof IObservable) {
				System.out.println("\tIs IObservable" + composant.getClass().getName());
				this.interfaceConfiguration.createBinding(this, (IObservable)composant);
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
