package fr.alma2017.server;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.server.IBaseDonnees;
import fr.alma2017.composantClass.AComposant;
import fr.alma2017.composantClass.InterfaceComposantFournie;

public class BaseDonnees extends AComposant implements IComposant, IBaseDonnees {

	private HashMap<String, String> valuesUsers;
	
	public BaseDonnees() {
		List<Class<?>> portFournis = new ArrayList<Class<?>>();
		List<Class<?>> serviceFournis = new ArrayList<Class<?>>();
		portFournis.add(IBaseDonnees.class);
		serviceFournis.add(IBaseDonnees.class);
		this.interfaceFournie = new InterfaceComposantFournie(portFournis, serviceFournis);
		valuesUsers = new HashMap<String, String>();
		//ajout d'un user pour l'example
		//tout en clair, vive la s�curit� \o/
		addUtilisateur("bob", "example");
	}
	
	/**
	 * Renvoie le mdp de l'utilisateur demand�
	 * @param utilisateur
	 * @return Si il n'existe pas, renvoie un String vide
	 */
	@Override
	public String getMotDePasseFromUtilisateur(String utilisateur) {
		if (valuesUsers.containsKey(utilisateur)) {
			return valuesUsers.get(utilisateur);
		}
		else {
			return "";
		}
	}
	
	/**
	 * Ajout d'un utilisateur dans la "base de donn�es"
	 */
	@Override
	public void addUtilisateur(String nom, String mdp) {
		if (!valuesUsers.containsKey(nom)) {
			valuesUsers.put(nom, mdp);
		}
		else {
			//change with an exception some day
			System.out.println("Utilisateur d�j� existant");
		}
	}
	
	
	
	
}
