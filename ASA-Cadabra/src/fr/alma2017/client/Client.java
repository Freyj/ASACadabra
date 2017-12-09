package fr.alma2017.client;

import java.util.List;

import fr.alma2017.api.client.IClient;
import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.composant.IInterfaceComposantFournie;
import fr.alma2017.api.composant.IInterfaceComposantRequise;

public class Client implements IComposant, IClient {
	
	private String message;

	
	public Client() {
		message = "No message";
	}

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
	public List<IComposant> getComposantsInternes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setMessage(String mes) {
		message = mes;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void sendMessage() {
		//this.getInterfaceRequise().sendRequest(message);
	}


}
