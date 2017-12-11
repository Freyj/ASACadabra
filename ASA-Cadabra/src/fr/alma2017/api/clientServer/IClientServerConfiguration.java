package fr.alma2017.api.clientServer;

import java.util.List;

import fr.alma2017.api.client.IClient;
import fr.alma2017.api.configuration.IConfiguration;
import fr.alma2017.api.server.IServer;

public interface IClientServerConfiguration extends IConfiguration {

	public IServer getServer();

	public IClient getClient();

	public void sendMessage(IServer server, Object source);

	public void sendAnswer(List<?> subList);

}