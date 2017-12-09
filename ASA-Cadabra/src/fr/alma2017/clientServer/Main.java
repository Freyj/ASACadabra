package fr.alma2017.clientServer;

import fr.alma2017.client.Client;

public class Main {
	
	private static ClientServerConfiguration clientServeurConfig;
	private static Client client;
	
	public static void main(String[] args) {
		clientServeurConfig = new ClientServerConfiguration();
		client = new Client();
	}

}
