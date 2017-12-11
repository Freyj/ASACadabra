package fr.alma2017.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import fr.alma2017.api.IObserver;
import fr.alma2017.api.server.IBaseDonnees;
import fr.alma2017.api.server.ISecurityManager;
import fr.alma2017.clientServer.Main;

public class ProxyBDD implements InvocationHandler{

	private Object target;
	private List<IObserver> observer;

	public ProxyBDD(Object target) {
		this.target = target;
		this.observer = new ArrayList<IObserver>();	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object ret;
		if(method.getName().equals("addObserver")){
			ret = Void.TYPE;
			this.observer.add( (IObserver) args[0] );
		}
		else if (method.getName().equals("getInfo") && this.observer != null) {
			ret = method.invoke(this.target, args);
			if(Main.Sysout) {
				System.out.println("\tProxy BDD : " + this.target.getClass().getName() + " est observe par " + this.observer.size() + " objets.");
			}
			for(IObserver observer : this.observer) {
				if (args[0] instanceof List<?>) {		
					List<Object> sourceList = (List<Object>) args[0];
					sourceList.add(0, IBaseDonnees.class);
					observer.notify(sourceList);
				}
			}
		}		
		else if(method.getName().substring(0, 3).equals("set") && this.observer != null){
			ret = method.invoke(this.target, args);
			System.out.println(target.getClass().getName() + " ["+ method.getName().substring(3) + "=" + args[0] + "] is modified");
		}else{
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
