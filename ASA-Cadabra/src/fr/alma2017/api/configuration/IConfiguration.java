package fr.alma2017.api.configuration;

import java.util.List;

import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.connecteur.IConnecteur;

public interface IConfiguration {
	public IInterfaceConfiguration getInterface();
	public List<IConnecteur> getConnecteurs();
	public List<IComposant> getComposantsInternes();

}
