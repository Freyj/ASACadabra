package fr.alma2017.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import fr.alma2017.api.IObserver;
import fr.alma2017.api.clientServer.IClientServerConfiguration;
import fr.alma2017.api.server.IBaseDonnees;
import fr.alma2017.api.server.IServer;
import fr.alma2017.api.server.IServerConfiguration;
import fr.alma2017.clientServer.Main;

public class ProxyServer implements InvocationHandler {
	
	private Object target;
	private List<IObserver> observer;

	public ProxyServer(Object target) {
		this.target = target;
		this.observer = new ArrayList<IObserver>();
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object ret;
		if(method.getName().equals("addObserver")){
			ret = Void.TYPE;
			this.observer.add( (IObserver) args[0] );
		}else if(method.getName().substring(0, 3).equals("set") && this.observer != null){
			ret = method.invoke(this.target, args);
			if(Main.Sysout) {
				System.out.println(target.getClass().getName() + " ["+ method.getName().substring(3) + "=" + args[0] + "] is modified");
			}
		}else if(method.getName().equals("sendMessage") && this.observer != null){
			ret = method.invoke(this.target, args);
			if(Main.Sysout) {
				System.out.println("\tProxy Server : " + this.target.getClass().getName() + " est observee par " + this.observer.size() + " objets.");
			}
			for(IObserver observer : this.observer) {
				if(observer instanceof IServerConfiguration) {
					if(args[0] instanceof List<?>) {
						observer.notify( args[0] );
					}
				}
			}
		}else if(method.getName().equals("sendAnswer") && this.observer != null){
			ret = method.invoke(this.target, args);
			if(Main.Sysout) {
				System.out.println("\tProxy Server : " + this.target.getClass().getName() + " est observee par " + this.observer.size() + " objets.");

			}
			for(IObserver observer : this.observer) {
				if(observer instanceof IClientServerConfiguration) {
					if(args[0] instanceof List<?>) {		
						List<Object> sourceList = (List<Object>) args[0];
						sourceList.add(0, IServer.class);
						observer.notify(sourceList);
					}
				}
			}
		}else{
			if(Main.Sysout) {
				System.out.println("\tProxy Server :  call " + method.getName());
			}
			ret = method.invoke(this.target, args);
		}
		return ret;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public List<IObserver> getObserver() {
		return observer;
	}

}
