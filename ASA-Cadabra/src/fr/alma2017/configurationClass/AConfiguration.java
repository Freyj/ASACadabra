package fr.alma2017.configurationClass;

import java.util.List;

import fr.alma2017.api.IObservable;
import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.configuration.IConfiguration;
import fr.alma2017.api.configuration.IInterfaceConfiguration;
import fr.alma2017.api.connecteur.IConnecteur;

public abstract class AConfiguration implements IConfiguration{

	protected IInterfaceConfiguration interfaceConfiguration;
	protected List<IConnecteur> connecteurs;
	protected List<IComposant> composantsInternes;

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
	public void createBinding(IConfiguration configuration, IObservable composant) {
		this.interfaceConfiguration.createBinding(configuration, composant);
	}
}
