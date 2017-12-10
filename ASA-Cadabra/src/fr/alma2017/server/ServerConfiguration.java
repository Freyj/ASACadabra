package fr.alma2017.server;

import java.util.ArrayList;
import java.util.List;
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
		ISecurityManager securityManager = (ISecurityManager) Proxifieur.getProxyFor(new SecurityManager(), ISecurityManager.class);
		IConnectionManager connectionManager = (IConnectionManager) Proxifieur.getProxyFor(new ConnectionManager(), IConnectionManager.class);
		IBaseDonnees baseDonnees = (IBaseDonnees) Proxifieur.getProxyFor(new BaseDonnees(), IBaseDonnees.class);

		this.composantsInternes.add(serverFromCSConfiguration);
		this.composantsInternes.add(securityManager);
		this.composantsInternes.add(connectionManager);
		this.composantsInternes.add(baseDonnees);

		this.connecteurs = new ArrayList<IConnecteur>();
	}
	

	@Override
	public void notify(Object source) {
		if(source instanceof List<?>) {	
			List<?> listeSource = (List<?>) source;
			//System.out.println("Size");
			//System.out.println(listeSource.size());
			//System.out.println(listeSource.toString());
			if (listeSource.get(0) instanceof String) {
				System.out.println("JE SUIS UN STRING");
				this.getConnectionManager().requestConnection(listeSource);
			}
			else if (listeSource.get(0) instanceof IConnectionManager) {
				System.out.println("CONNEXIIIIOON");
				this.getSecurityManager().authentify(listeSource.subList(1, listeSource.size()));
			}
			else if (listeSource.get(0) instanceof ISecurityManager) {
				System.out.println("THIS IS GRAVE SECURE");
				this.getBaseDonnees().getInfo(listeSource.subList(1, listeSource.size()));
			}
			else if (listeSource.get(0) instanceof IBaseDonnees) {
				System.out.println("GIMME ALL DA DATA");
				
			}
			if(Main.Sysout) {
				if (listeSource.size() == 3 ) {
				System.out.println("Notification pour la classe " + this.getClass().getName() + " : " + 
						listeSource.get(0) + " : " + listeSource.get(2) );
				}
				else if (listeSource.size() > 3){
					System.out.println("Notification pour " + this.getClass().getName() + " : " + 
							listeSource.get(1) + " : " + listeSource.get(3) );
					System.out.println("interface qui lance le machin " + listeSource.get(0));
				}
			}
			
			
		} else if(Main.Sysout) {
			System.out.println("Notification pour " + this.getClass().getName() + " : " + source.toString());
		}
	}

	private IBaseDonnees getBaseDonnees() {
		IBaseDonnees res = null;
		for(IComposant composant : this.composantsInternes) {
			if(composant instanceof IBaseDonnees) {
				res = (IBaseDonnees) composant;
			}
		}
		return res;
	} 
	
	private IConnectionManager getConnectionManager() {
		IConnectionManager res = null;
		for(IComposant composant : this.composantsInternes) {
			if(composant instanceof IConnectionManager) {
				res = (IConnectionManager) composant;
			}
		}
		return res;
	} 
	
	private ISecurityManager getSecurityManager() {
		ISecurityManager res = null;
		for(IComposant composant : this.composantsInternes) {
			if(composant instanceof ISecurityManager) {
				res = (ISecurityManager) composant;
			}
		}
		return res;
	}
	
	private IServer getServer() {
		IServer res = null;
		for(IComposant composant : this.composantsInternes) {
			if(composant instanceof IServer) {
				res = (IServer) composant;
			}
		}
		return res;
	}

}
