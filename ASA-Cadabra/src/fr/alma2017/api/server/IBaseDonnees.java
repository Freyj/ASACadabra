package fr.alma2017.api.server;

import java.util.List;

import fr.alma2017.api.composant.IComposant;

public interface IBaseDonnees extends IComposant {
	String getMotDePasseFromUtilisateur(String utilisateur);
	void addUtilisateur(String utilisateur, String motDePasse);
	void getInfo(List<?> source);
}