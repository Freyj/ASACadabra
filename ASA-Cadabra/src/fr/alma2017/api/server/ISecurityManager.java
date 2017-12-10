package fr.alma2017.api.server;

import java.util.List;

import fr.alma2017.api.composant.IComposant;

public interface ISecurityManager extends IComposant {
	void authentify(List<?> source);
}
