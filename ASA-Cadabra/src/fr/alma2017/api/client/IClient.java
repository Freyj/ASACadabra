package fr.alma2017.api.client;

import fr.alma2017.api.composant.IComposant;

public interface IClient extends IComposant {

	void sendMessage();

	void setMessage(String string);

}
