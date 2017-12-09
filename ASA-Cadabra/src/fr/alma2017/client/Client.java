package fr.alma2017.client;

import java.util.List;

import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.composant.IInterfaceComposantFournie;
import fr.alma2017.api.composant.IInterfaceComposantRequise;

public class Client implements IComposant {
	
	private String message;

	@Override
	public IInterfaceComposantFournie getInterfaceFournie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IInterfaceComposantRequise getInterfaceRequise() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IComposant> getInnerComposants() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Client(String message) {
		this.message = message;		
	}
	
	public void sendMessage(String message) {
		//this.getInterfaceRequise().sendRequest(message);
	}
	

}
