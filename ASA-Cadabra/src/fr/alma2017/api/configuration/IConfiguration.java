package fr.alma2017.api.configuration;

import java.util.List;

import fr.alma2017.api.IObserver;
import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.connecteur.IConnecteur;

public interface IConfiguration extends IObserver {
	public IInterfaceConfiguration getInterface();
	public List<IConnecteur> getConnecteurs();
	public List<IComposant> getComposantsInternes();
}
