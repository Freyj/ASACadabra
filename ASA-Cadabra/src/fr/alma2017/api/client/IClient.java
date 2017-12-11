package fr.alma2017.api.client;

import java.util.List;

import fr.alma2017.api.composant.IComposant;

public interface IClient extends IComposant {

	void sendMessage();

	void setMessage(String string);

	public String getMessage();

	public void setMotDePasse(String mdp);

	public String getMotDePasse();

	public void setNomUtilisateur(String ut);

	public String getNomUtilisateur();

	public List<String> makeMessage();

	public void receiveAnswer(List<?> subList);

}
