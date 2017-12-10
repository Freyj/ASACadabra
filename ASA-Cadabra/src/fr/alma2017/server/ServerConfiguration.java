package fr.alma2017.server;

import java.util.ArrayList;
import java.util.List;
import fr.alma2017.api.IObservable;
import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.configuration.IConfiguration;
import fr.alma2017.api.connecteur.IConnecteur;
import fr.alma2017.api.server.IBaseDonnees;
import fr.alma2017.api.server.IConnectionManager;
import fr.alma2017.api.server.ISecurityManager;
import fr.alma2017.api.server.IServer;
import fr.alma2017.api.server.IServerConfiguration;
import fr.alma2017.clientServer.Main;
import fr.alma2017.configurationClass.AConfiguration;
import fr.alma2017.configurationClass.InterfaceConfiguration;
import fr.alma2017.exception.NotProxiedClassException;
import fr.alma2017.proxy.Proxifieur;

public class ServerConfiguration extends AConfiguration implements IConfiguration, IServerConfiguration {
	

	public ServerConfiguration(IServer serverFromCSConfiguration) throws NotProxiedClassException {
		
		List<Class<?>> portRequis = new ArrayList<Class<?>>();
		List<Class<?>> portFournis = new ArrayList<Class<?>>();
		portRequis.add(ISecurityManager.class);
		portRequis.add(IConnectionManager.class);
		portRequis.add(IBaseDonnees.class);
		portRequis.add(IServer.class);

		portFournis.add(ISecurityManager.class);
		portFournis.add(IConnectionManager.class);
		portFournis.add(IBaseDonnees.class);
		
		this.interfaceConfiguration = new InterfaceConfiguration(portRequis, portFournis);
		
		this.composantsInternes = new ArrayList<IComposant>();
		IServer server = (IServer) Proxifieur.getProxyFor(serverFromCSConfiguration, IServer.class);
		ISecurityManager securityManager = (ISecurityManager) Proxifieur.getProxyFor(new SecurityManager(), ISecurityManager.class);
		IConnectionManager connectionManager = (IConnectionManager) Proxifieur.getProxyFor(new ConnectionManager(), IConnectionManager.class);
		IBaseDonnees baseDonnees = (IBaseDonnees) Proxifieur.getProxyFor(new BaseDonnees(), IBaseDonnees.class);
		
		this.composantsInternes.add(server);
		this.composantsInternes.add(securityManager);
		this.composantsInternes.add(connectionManager);
		this.composantsInternes.add(baseDonnees);
		/*
		for(IComposant composant : this.composantsInternes) {
			if(composant instanceof IObservable) {
				this.getInterface().createBinding(this, (IObservable)composant);
			}
		}*/
		
		this.connecteurs = new ArrayList<IConnecteur>();
	}
	
	@Override
	public void bindComposant() {
		for(IComposant composant : this.composantsInternes) {
			if(composant instanceof IObservable) {
				this.interfaceConfiguration.createBinding(this, (IObservable)composant);
			}
		}
	}
	@Override
	public void notify(Object source) {
		if(Main.Sysout) {
			System.out.println("Notification pour " + this.getClass().getName() + " : " + source.toString());
		}
	}

	
}
