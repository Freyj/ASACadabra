package fr.alma2017.clientServer;

import java.util.List;

import fr.alma2017.api.clientServer.IClientServerIConfiguration;
import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.configuration.IConfiguration;
import fr.alma2017.api.configuration.IInterfaceConfiguration;
import fr.alma2017.api.connecteur.IConnecteur;

public class ClientServerConfiguration implements IConfiguration, IClientServerIConfiguration {

	private IInterfaceConfiguration interfaceConfiguration;
	private List<IComposant> innerComposants;
	private List<IConnecteur> connecteurs;

	public ClientServerConfiguration(IInterfaceConfiguration interfaceConfiguration, List<IComposant> innerComposants,
			List<IConnecteur> connecteurs) {
		super();
		this.interfaceConfiguration = interfaceConfiguration;
		this.innerComposants = innerComposants;
		this.connecteurs = connecteurs;
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
