package fr.alma2017.api.server;

import fr.alma2017.api.composant.IComposant;

public interface IConnectionManager extends IComposant {
	void requestConnection(Object message);
}
