package fr.alma2017.client;

import java.util.ArrayList;

import fr.alma2017.api.IObservable;
import fr.alma2017.api.client.IClient;
import fr.alma2017.api.composant.IComposant;
import fr.alma2017.composantClass.AComposant;

public class Client extends AComposant implements IComposant, IClient {
	
	private String message;
	private String motDePasse;
	private String utilisateur;

	/**
	 * Constructeur vide
	 */
	public Client() {
		message = "";
		utilisateur = "";
		motDePasse = "";
	}
	
	/**
	 * Constructeur pour un utilisateur complet
	 * @param uti un String avec le nom de l'utilisateur
	 * @param mdp un String avec le mot de passe
	 * @param mess un String avec le message à envoyer
	 */
	public Client(String uti, String mdp, String mess) {
		message = mess;
		utilisateur = uti;
		motDePasse = mdp;
	}
	
	//Getters & Setters
	public String getMessage() {
		return message;
	}	
	
	public void setMessage(String mes) {
		message = mes;
	}
	
	public void setMotDePasse(String mdp) {
		motDePasse = mdp;
	}
	
	public String getMotDePasse() {
		return motDePasse;
	}
	
	public void setNomUtilisateur(String ut) {
		utilisateur = ut;
	}
	
	public String getNomUtilisateur() {
		return utilisateur;
	}

	/**
	 * Méthode qui va servir à envoyer un message via les interfaces pour remonter l'archi
	 */
	public void sendMessage() {
		//format de l'envoi : utilisateur + " " + motdepasse + " " + message
		ArrayList<String> infosEnvoyees = new ArrayList<String>();
		infosEnvoyees.add(getNomUtilisateur());
		infosEnvoyees.add(getMotDePasse());
		infosEnvoyees.add(getMessage());
		//this.getInterfaceRequise().sendRequest(infosEnvoyees);
		
	}


}
