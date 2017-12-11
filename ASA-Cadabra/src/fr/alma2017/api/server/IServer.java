package fr.alma2017.api.server;

import java.util.List;

import fr.alma2017.api.composant.IComposant;

public interface IServer extends IComposant {

	public void sendMessage(List<?> source);

	public void sendAnswer(List<?> subList);

}
