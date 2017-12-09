package fr.alma2017.client;

import java.util.List;

import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.configuration.IConfiguration;
import fr.alma2017.api.configuration.IInterfaceConfiguration;
import fr.alma2017.api.connecteur.IConnecteur;

public class ClientConfiguration implements IConfiguration{
	
	
	
	public ClientConfiguration() {
		
	}

	@Override
	public void notify(Object source) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IInterfaceConfiguration getInterface() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IConnecteur> getConnecteurs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IComposant> getComposantsInternes() {
		// TODO Auto-generated method stub
		return null;
	}

}
