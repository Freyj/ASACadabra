package fr.alma2017.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.server.IBaseDonnees;
import fr.alma2017.clientServer.Main;
import fr.alma2017.composantClass.AComposant;
import fr.alma2017.composantClass.InterfaceComposantFournie;

public class BaseDonnees extends AComposant implements IComposant, IBaseDonnees {

	private HashMap<String, String> mdpUsers;
	private HashMap<String, String> messagesUsers;

	public BaseDonnees() {
		List<Class<?>> portFournis = new ArrayList<Class<?>>();
		List<Class<?>> serviceFournis = new ArrayList<Class<?>>();
		portFournis.add(IBaseDonnees.class);
		serviceFournis.add(IBaseDonnees.class);
		this.interfaceFournie = new InterfaceComposantFournie(portFournis, serviceFournis);
		mdpUsers = new HashMap<String, String>();
		messagesUsers = new HashMap<String, String>();
		//ajout d'un user pour l'example
		//tout en clair, vive la securite \o/
		addUtilisateur("bob", "example");
	}

	/**
	 * Renvoie le mdp de l'utilisateur demande
	 * @param utilisateur
	 * @return Si il n'existe pas, renvoie un String vide
	 */
	@Override
	public String getMotDePasseFromUtilisateur(String utilisateur) {
		if (mdpUsers.containsKey(utilisateur)) {
			return mdpUsers.get(utilisateur);
		}
		else {
			return "";
		}
	}

	/**
	 * Ajout d'un utilisateur dans la "base de donnees"
	 */
	@Override
	public void addUtilisateur(String nom, String mdp) {
		if (!mdpUsers.containsKey(nom)) {
			mdpUsers.put(nom, mdp);
			messagesUsers.put(nom,  "");
		}
		else {
			//change with an exception some day
			System.out.println("Utilisateur deja existant");
		}
	}


	/**
	 * Changement du message de la bdd
	 */
	public void setMessage(String nom, String mess) {
		if (messagesUsers.containsKey(nom)) {
			messagesUsers.put(nom, mess);
		}
		else {
			//change with an exception some day
			System.out.println("Utilisateur non existant, message non modifie");
		}
	}

	/**
	 * Récupération du message en bdd
	 */
	public String getMessage(String nom) {
		if (messagesUsers.containsKey(nom)) {
			return messagesUsers.get(nom);
		}
		else {
			return "";
		}
	}

	@Override
	public void notify(Object source) {
		if(source instanceof List<?>) {	
			List<Object> sourceList = (List<Object>) source;
			sourceList.add(0,IBaseDonnees.class);
			sourceList.add((Object) getMessage((String)sourceList.get(0)));
			if(Main.Sysout) {
				System.out.println("Notification pour " + this.getClass().getName() + " : " + 
						((List<?>)source).get(0) + " : " + ((List<?>)source).get(2) );
			}
		}
	}

	@Override
	public void getInfo(List<?> source) {
		if (source.size() == 3) {
			if (source.get(0) instanceof String) {
				List<String> sourceList = (List<String>) source;
				setMessage(sourceList.get(0), sourceList.get(2));
			}
		}

	}

}
